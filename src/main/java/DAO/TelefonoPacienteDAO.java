package DAO;

import Objects.Hospital;
import Objects.Paciente;
import Objects.TelefonoPaciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Conexion.Conexion;

public class TelefonoPacienteDAO {
    public List<TelefonoPaciente> obtenerTelefonos(int cedula) {
        List<TelefonoPaciente> telefonos = new ArrayList<>();

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM Telefonos_Paciente WHERE Cedula = ?")) {

            preparedStatement.setInt(1, cedula);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    TelefonoPaciente telefonoPaciente = new TelefonoPaciente();
                    telefonoPaciente.setCedula(resultSet.getInt("Cedula"));
                    telefonoPaciente.setTelefono(resultSet.getInt("Telefono"));

                    telefonos.add(telefonoPaciente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return telefonos;
    }


    public void borrarTelefonoPaciente(TelefonoPaciente telefonoPaciente) {
        try {
            // Obtener la conexión a la base de datos
            Connection conexion = Conexion.obtenerConexion();

            // Verificar que la conexión no sea null antes de realizar operaciones
            if (conexion != null) {
                // Aquí puedes realizar las operaciones con la base de datos
                String Delete_SQL = "DELETE FROM Telefonos_Paciente WHERE Cedula=? and Telefono=?";
                try (PreparedStatement preparedStatement = conexion.prepareStatement(Delete_SQL)) {
                    preparedStatement.setInt(1, telefonoPaciente.getCedula());
                    preparedStatement.setInt(2, telefonoPaciente.getTelefono());
                    preparedStatement.executeUpdate();
                }
            } else {
                System.err.println("Error: La conexión es nula");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        public void agregarTelefonoPaciente(TelefonoPaciente telefonoPaciente) {
            try {
                // Obtener la conexión a la base de datos
                Connection conexion = Conexion.obtenerConexion();

                // Verificar que la conexión no sea null antes de realizar operaciones
                if (conexion != null) {
                    // Cargar el controlador JDBC de SQL Server (Asegúrate de tener el archivo .jar en tu proyecto)
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                    // Aquí puedes realizar las operaciones con la base de datos
                    String INSERT_SQL = "INSERT INTO Telefonos_Paciente (Cedula, Telefono) VALUES (?, ?)";
                    try (PreparedStatement preparedStatement = conexion.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
                        preparedStatement.setInt(1, telefonoPaciente.getCedula());
                        preparedStatement.setInt(2, telefonoPaciente.getTelefono());
                        preparedStatement.executeUpdate();                }
                } else {
                    System.err.println("Error: La conexión es nula");
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    public boolean actualizarTelefonoPaciente(TelefonoPaciente telefonoPaciente, int telefonoCambiar) {
        boolean estado = true;
        try {
            // Obtener la conexión a la base de datos
            Connection conexion = Conexion.obtenerConexion();

            // Verificar que la conexión no sea null antes de realizar operaciones
            if (conexion != null) {
                // Cargar el controlador JDBC de SQL Server (Asegúrate de tener el archivo .jar en tu proyecto)
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                // Aquí puedes realizar las operaciones con la base de datos
                String UPDATE_SQL = "UPDATE Telefonos_Paciente SET Cedula = ?, Telefono=? WHERE Cedula = ? and Telefono= ?";
                try (PreparedStatement preparedStatement = conexion.prepareStatement(UPDATE_SQL)) {
                    preparedStatement.setInt(1, telefonoPaciente.getCedula());
                    preparedStatement.setInt(2, telefonoPaciente.getTelefono());
                    preparedStatement.setInt(3, telefonoPaciente.getCedula());
                    preparedStatement.setInt(4, telefonoCambiar);


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
