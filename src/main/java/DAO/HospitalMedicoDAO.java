package DAO;

import Conexion.Conexion;
import Objects.HospitalMedico;
import Objects.TelefonoPaciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalMedicoDAO {

    public void agregarHospitalMedico (HospitalMedico hospitalMedico) {
        try {
            // Obtener la conexión a la base de datos
            Connection conexion = Conexion.obtenerConexion();

            // Verificar que la conexión no sea null antes de realizar operaciones
            if (conexion != null) {
                // Cargar el controlador JDBC de SQL Server (Asegúrate de tener el archivo .jar en tu proyecto)
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                // Aquí puedes realizar las operaciones con la base de datos
                String INSERT_SQL = "INSERT INTO Medico_Hospital (Id_Hospital, Cedula) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = conexion.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setInt(1, hospitalMedico.getId_Hospital());
                    preparedStatement.setInt(2, hospitalMedico.getCedula());
                    preparedStatement.executeUpdate();                }
            } else {
                System.err.println("Error: La conexión es nula");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void borrarHospitalMedico(HospitalMedico hospitalMedico) {
        try {
            // Obtener la conexión a la base de datos
            Connection conexion = Conexion.obtenerConexion();

            // Verificar que la conexión no sea null antes de realizar operaciones
            if (conexion != null) {
                // Aquí puedes realizar las operaciones con la base de datos
                String Delete_SQL = "DELETE FROM Medico_Hospital WHERE Id_Hospital=? and Cedula=?";
                try (PreparedStatement preparedStatement = conexion.prepareStatement(Delete_SQL)) {
                    preparedStatement.setInt(1, hospitalMedico.getId_Hospital());
                    preparedStatement.setInt(2, hospitalMedico.getCedula());
                    preparedStatement.executeUpdate();
                }
            } else {
                System.err.println("Error: La conexión es nula");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean actualizarIDHospitalMedico(int id_HospitalAntiguo, int nuevoId_Hospital, int cedulaMedico) {
        boolean estado = false;
        try {
            Connection conexion = Conexion.obtenerConexion();

            if (conexion != null) {
                String UPDATE_SQL = "UPDATE Medico_Hospital SET Id_Hospital = ? WHERE Id_Hospital = ? AND Cedula = ?";
                try (PreparedStatement preparedStatement = conexion.prepareStatement(UPDATE_SQL)) {
                    preparedStatement.setInt(1, nuevoId_Hospital);
                    preparedStatement.setInt(2, id_HospitalAntiguo);
                    preparedStatement.setInt(3, cedulaMedico);

                    int filasAfectadas = preparedStatement.executeUpdate();

                    if (filasAfectadas > 0) {
                        estado = true;
                    }
                }
            } else {
                System.out.println("Error: la conexión es nula");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estado;
    }



    public List<HospitalMedico> obtenerHospitalMedico(int idHospital) {
        List<HospitalMedico> medicos = new ArrayList<>();

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM Medico_Hospital WHERE Id_Hospital = ?")) {

            preparedStatement.setInt(1, idHospital);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    HospitalMedico hospitalMedico = new HospitalMedico();
                    hospitalMedico.setId_Hospital(resultSet.getInt("Id_Hospital"));
                    hospitalMedico.setCedula(resultSet.getInt("Cedula"));
                    medicos.add(hospitalMedico);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicos;
    }
    public List<HospitalMedico> obtenerHospitalMedico2(int cedula) {
        List<HospitalMedico> hospitales = new ArrayList<>();

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM Medico_Hospital WHERE Cedula = ?")) {

            preparedStatement.setInt(1, cedula);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    HospitalMedico hospitalMedico = new HospitalMedico();
                    hospitalMedico.setId_Hospital(resultSet.getInt("Id_Hospital"));
                    hospitalMedico.setCedula(resultSet.getInt("Cedula"));
                    hospitales.add(hospitalMedico);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hospitales;
    }

}









