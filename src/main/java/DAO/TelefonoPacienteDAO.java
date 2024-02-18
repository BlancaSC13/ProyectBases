package DAO;

import Objects.Hospital;
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

    public void borrarTelefonoPaciente(TelefonoPaciente telefonoPaciente){
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
}
