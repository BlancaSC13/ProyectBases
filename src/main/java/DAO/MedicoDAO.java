package DAO;

import Conexion.Conexion;
import Objects.Medico;

import java.sql.*;

public class MedicoDAO
{
    public void agregarMedico(Medico medico) {
        try {
            // Obtener la conexión a la base de datos
            Connection conexion = Conexion.obtenerConexion();

            // Verificar que la conexión no sea null antes de realizar operaciones
            if (conexion != null) {
                // Cargar el controlador JDBC de SQL Server (Asegúrate de tener el archivo .jar en tu proyecto)
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                // Aquí puedes realizar las operaciones con la base de datos
                String INSERT_SQL = "INSERT INTO Medico (Cedula, Nombre, Apellido_1, Apellido_2, Fec_Nacimiento) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = conexion.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setInt(1, medico.getCedula());
                    preparedStatement.setString(2, medico.getNombre());
                    preparedStatement.setString(3, medico.getApellido1());
                    preparedStatement.setString(4, medico.getApellido2());
                    preparedStatement.setString(5, medico.getFec_Nacimiento());
                    preparedStatement.executeUpdate();                }
            } else {
                System.err.println("Error: La conexión es nula");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Medico obtenerMedico(int Cedula) {
        Medico medico = new Medico();
        try {
            // Obtener la conexión a la base de datos
            Connection conexion = Conexion.obtenerConexion();

            // Verificar que la conexión no sea null antes de realizar operaciones
            if (conexion != null) {
                // Aquí puedes realizar las operaciones con la base de datos
                String SELECT_SQL = "SELECT Cedula, Nombre, Apellido_1, Apellido_2, Fec_Nacimiento," +
                        "DATEDIFF(YEAR, Fec_Nacimiento, GETDATE()) as Edad FROM Medico WHERE Cedula=?";
                try (PreparedStatement preparedStatement = conexion.prepareStatement(SELECT_SQL)) {
                    preparedStatement.setInt(1, Cedula);

                    // Ejecutar la consulta
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        // Verificar si hay resultados
                        if (resultSet.next()) {
                            // Crear un objeto Hospital y establecer sus propiedades
                            medico.setCedula(resultSet.getInt("Cedula"));
                            medico.setNombre(resultSet.getString("Nombre"));
                            medico.setApellido1(resultSet.getString("Apellido_1"));
                            medico.setApellido2(resultSet.getString("Apellido_2"));
                            medico.setFec_Nacimiento(resultSet.getString("Fec_Nacimiento"));
                            double edad = resultSet.getInt("Edad");
                            medico.setEdad(edad);
                        }
                    }
                }
            } else {
                System.err.println("Error: La conexión es nula");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medico;
    }
    public void borrarMedico(int cedula){
        try {
            // Obtener la conexión a la base de datos
            Connection conexion = Conexion.obtenerConexion();

            // Verificar que la conexión no sea null antes de realizar operaciones
            if (conexion != null) {
                // Aquí puedes realizar las operaciones con la base de datos
                String Delete_SQL = "DELETE FROM Medico WHERE Cedula=?";
                try (PreparedStatement preparedStatement = conexion.prepareStatement(Delete_SQL)) {
                    preparedStatement.setInt(1, cedula);
                    preparedStatement.executeUpdate();
                }
            } else {
                System.err.println("Error: La conexión es nula");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean actualizarMedico(Medico medico) {
        boolean estado = true;
        try {
            // Obtener la conexión a la base de datos
            Connection conexion = Conexion.obtenerConexion();

            // Verificar que la conexión no sea null antes de realizar operaciones
            if (conexion != null) {
                // Cargar el controlador JDBC de SQL Server (Asegúrate de tener el archivo .jar en tu proyecto)
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                // Aquí puedes realizar las operaciones con la base de datos
                String UPDATE_SQL = "UPDATE Medico SET Cedula = ?, Nombre = ?, Apellido_1 = ?, Apellido_2 = ?, Fec_Nacimiento = ? WHERE Cedula = ?";
                try (PreparedStatement preparedStatement = conexion.prepareStatement(UPDATE_SQL)) {
                    preparedStatement.setInt(1, medico.getCedula());
                    preparedStatement.setString(2, medico.getNombre());
                    preparedStatement.setString(3, medico.getApellido1());
                    preparedStatement.setString(4, medico.getApellido2());
                    preparedStatement.setString(5, medico.getFec_Nacimiento());
                    preparedStatement.setInt(6, medico.getCedula());


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
