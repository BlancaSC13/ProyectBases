package DAO;

import Conexion.Conexion;
import Objects.Hospital;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class HospitalDAO {
    public List<Hospital> obtenerHospitales() {
        List<Hospital> hospitales = new ArrayList<>();

        try (Connection conexion = Conexion.obtenerConexion();
             Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Hospital")) {

            while (resultSet.next()) {
                Hospital hospital = new Hospital();
                hospital.setId(resultSet.getInt("Id_Hospital"));
                hospital.setNombre(resultSet.getString("Nombre"));
                hospital.setDireccion(resultSet.getString("Direccion"));
                hospitales.add(hospital);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hospitales;
    }

    public void agregarHospital(Hospital hospital) {
        try {
            // Obtener la conexión a la base de datos
            Connection conexion = Conexion.obtenerConexion();

            // Verificar que la conexión no sea null antes de realizar operaciones
            if (conexion != null) {
                // Cargar el controlador JDBC de SQL Server (Asegúrate de tener el archivo .jar en tu proyecto)
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                // Aquí puedes realizar las operaciones con la base de datos
                String INSERT_SQL = "INSERT INTO Hospital (nombre, direccion) VALUES (?, ?)";
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

}
