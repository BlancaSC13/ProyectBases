package Controller.PacienteTelefonos;

import Conexion.Conexion;
import DAO.TelefonoPacienteDAO;
import Objects.TelefonoPaciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BorrarTelefonoPacienteController
{
    @javafx.fxml.FXML
    private TextField TextFieldCedula;
    @javafx.fxml.FXML
    private TextField TextFieldTelefono;
    private TelefonoPaciente telefonoPaciente = new TelefonoPaciente();
    private TelefonoPacienteDAO telefonoPacienteDAO = new TelefonoPacienteDAO();

    @javafx.fxml.FXML
    public void initialize() {
    }
    @FXML
    void BtnBorrarOnAction(ActionEvent event) {
        telefonoPaciente.setCedula(Integer.parseInt(TextFieldCedula.getText()));
        telefonoPaciente.setTelefono(Integer.parseInt(TextFieldTelefono.getText()));
        telefonoPacienteDAO.borrarTelefonoPaciente(telefonoPaciente);
        mostrarMensaje("El número de teléfono se borró correctamente");
        limpiarCampos();
    }

    @FXML
    void BtnCancelarOnAction(ActionEvent event) {
        // Obtener la referencia del Stage actual
        Stage stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
        // Cerrar el Stage
        stage.close();
    }
    private void limpiarCampos() {
        TextFieldCedula.clear();
        TextFieldTelefono.clear();
    }

    private void mostrarMensaje(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}