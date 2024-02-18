package DAO;

import Conexion.Conexion;
import Objects.Hospital;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class HospitalDAO {
        public void agregarHospital(Hospital hospital) {
        try {
            // Obtener la conexión a la base de datos
            Connection conexion = Conexion.obtenerConexion();

            // Verificar que la conexión no sea null antes de realizar operaciones
            if (conexion != null) {
                // Cargar el controlador JDBC de SQL Server (Asegúrate de tener el archivo .jar en tu proyecto)
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                // Aquí puedes realizar las operaciones con la base de datos
                String INSERT_SQL = "INSERT INTO Hospital (Nombre, Direccion) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = conexion.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setString(1, hospital.getNombre());
                    preparedStatement.setString(2, hospital.getDireccion());
                    preparedStatement.executeUpdate();

                    // Obtener el ID generado automáticamente
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int idGenerado = generatedKeys.getInt(1);
                            hospital.setId(idGenerado);
                        } else {
                            throw new SQLException("No se generó un ID para el hospital");
                        }
                    }
                }
            } else {
                System.err.println("Error: La conexión es nula");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Hospital obtenerHospital(int id_Hospital) {
        Hospital hospital = null;
        try {
            // Obtener la conexión a la base de datos
            Connection conexion = Conexion.obtenerConexion();

            // Verificar que la conexión no sea null antes de realizar operaciones
            if (conexion != null) {
                // Aquí puedes realizar las operaciones con la base de datos
                String SELECT_SQL = "SELECT * FROM Hospital WHERE Id_Hospital=?";
                try (PreparedStatement preparedStatement = conexion.prepareStatement(SELECT_SQL)) {
                    preparedStatement.setInt(1, id_Hospital);

                    // Ejecutar la consulta
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        // Verificar si hay resultados
                        if (resultSet.next()) {
                            // Crear un objeto Hospital y establecer sus propiedades
                            hospital = new Hospital();
                            hospital.setId(resultSet.getInt("Id_Hospital"));
                            hospital.setNombre(resultSet.getString("Nombre"));
                            hospital.setDireccion(resultSet.getString("Direccion"));
                                                   }
                    }
                }
            } else {
                System.err.println("Error: La conexión es nula");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hospital;
    }
    public void borrarHospital(int id_Hospital){
        try {
            // Obtener la conexión a la base de datos
            Connection conexion = Conexion.obtenerConexion();

            // Verificar que la conexión no sea null antes de realizar operaciones
            if (conexion != null) {
                // Aquí puedes realizar las operaciones con la base de datos
                String Delete_SQL = "DELETE FROM Hospital WHERE Id_Hospital=?";
                try (PreparedStatement preparedStatement = conexion.prepareStatement(Delete_SQL)) {
                    preparedStatement.setInt(1, id_Hospital);
                    preparedStatement.executeUpdate();
                }
            } else {
                System.err.println("Error: La conexión es nula");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean actualizarHospital(Hospital hospital) {
        boolean estado = true;
        try {
            // Obtener la conexión a la base de datos
            Connection conexion = Conexion.obtenerConexion();

            // Verificar que la conexión no sea null antes de realizar operaciones
            if (conexion != null) {
                // Cargar el controlador JDBC de SQL Server (Asegúrate de tener el archivo .jar en tu proyecto)
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                // Aquí puedes realizar las operaciones con la base de datos
                String UPDATE_SQL = "UPDATE Hospital SET Nombre = ?, Direccion = ? WHERE Id_Hospital = ?";
                try (PreparedStatement preparedStatement = conexion.prepareStatement(UPDATE_SQL)) {
                    preparedStatement.setString(1, hospital.getNombre());
                    preparedStatement.setString(2, hospital.getDireccion());
                    preparedStatement.setInt(3, hospital.getId());

                    int filasAfectadas = preparedStatement.executeUpdate();

                    if (filasAfectadas > 0) {
                        estado = true;
                    } else {
                        estado = false;
                    }
                }
            } else {
                System.out.println("Error: la conexión es nula");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return estado;
    }

}

