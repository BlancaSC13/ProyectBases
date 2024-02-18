package DAO;

import Conexion.Conexion;
import Objects.Paciente;

import java.sql.*;

public class PacienteDAO {
    public void agregarPaciente(Paciente paciente) {
        try {
            // Obtener la conexión a la base de datos
            Connection conexion = Conexion.obtenerConexion();

            // Verificar que la conexión no sea null antes de realizar operaciones
            if (conexion != null) {
                // Cargar el controlador JDBC de SQL Server (Asegúrate de tener el archivo .jar en tu proyecto)
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                // Aquí puedes realizar las operaciones con la base de datos
                String INSERT_SQL = "INSERT INTO Paciente (Cedula, Nombre, Apellido_1, Apellido_2, Fec_Nacimiento) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = conexion.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setInt(1, paciente.getCedula());
                    preparedStatement.setString(2, paciente.getNombre());
                    preparedStatement.setString(3, paciente.getApellido1());
                    preparedStatement.setString(4, paciente.getApellido2());
                    preparedStatement.setString(5, paciente.getFec_Nacimiento());
                    preparedStatement.executeUpdate();                }
            } else {
                System.err.println("Error: La conexión es nula");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Paciente obtenerPaciente(int Cedula) {
        Paciente paciente = new Paciente();
        try {
            // Obtener la conexión a la base de datos
            Connection conexion = Conexion.obtenerConexion();

            // Verificar que la conexión no sea null antes de realizar operaciones
            if (conexion != null) {
                // Aquí puedes realizar las operaciones con la base de datos
                String SELECT_SQL = "SELECT Cedula, Nombre, Apellido_1, Apellido_2, Fec_Nacimiento," +
                        "DATEDIFF(YEAR, Fec_Nacimiento, GETDATE()) as Edad FROM Paciente WHERE Cedula=?";
                try (PreparedStatement preparedStatement = conexion.prepareStatement(SELECT_SQL)) {
                    preparedStatement.setInt(1, Cedula);

                    // Ejecutar la consulta
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        // Verificar si hay resultados
                        if (resultSet.next()) {
                            // Crear un objeto Hospital y establecer sus propiedades
                            paciente.setCedula(resultSet.getInt("Cedula"));
                            paciente.setNombre(resultSet.getString("Nombre"));
                            paciente.setApellido1(resultSet.getString("Apellido_1"));
                            paciente.setApellido2(resultSet.getString("Apellido_2"));
                            paciente.setFec_Nacimiento(resultSet.getString("Fec_Nacimiento"));
                            double edad = resultSet.getInt("Edad");
                            paciente.setEdad(edad);
                        }
                    }
                }
            } else {
                System.err.println("Error: La conexión es nula");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paciente;
    }
    public void borrarpaciente(int cedula){
        try {
            // Obtener la conexión a la base de datos
            Connection conexion = Conexion.obtenerConexion();

            // Verificar que la conexión no sea null antes de realizar operaciones
            if (conexion != null) {
                // Aquí puedes realizar las operaciones con la base de datos
                String Delete_SQL = "DELETE FROM Paciente WHERE Cedula=?";
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

    public boolean actualizarPaciente(Paciente paciente) {
        boolean estado = true;
        try {
            // Obtener la conexión a la base de datos
            Connection conexion = Conexion.obtenerConexion();

            // Verificar que la conexión no sea null antes de realizar operaciones
            if (conexion != null) {
                // Cargar el controlador JDBC de SQL Server (Asegúrate de tener el archivo .jar en tu proyecto)
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                // Aquí puedes realizar las operaciones con la base de datos
                String UPDATE_SQL = "UPDATE Paciente SET Cedula = ?, Nombre = ?, Apellido_1 = ?, Apellido_2 = ?, Fec_Nacimiento = ? WHERE Cedula = ?";
                try (PreparedStatement preparedStatement = conexion.prepareStatement(UPDATE_SQL)) {
                    preparedStatement.setInt(1, paciente.getCedula());
                    preparedStatement.setString(2, paciente.getNombre());
                    preparedStatement.setString(3, paciente.getApellido1());
                    preparedStatement.setString(4, paciente.getApellido2());
                    preparedStatement.setString(5, paciente.getFec_Nacimiento());
                    preparedStatement.setInt(6, paciente.getCedula());


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
