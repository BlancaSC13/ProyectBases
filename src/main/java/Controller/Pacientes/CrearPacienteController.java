package Controller.Pacientes;

import DAO.PacienteDAO;
import Objects.Paciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CrearPacienteController {
    @javafx.fxml.FXML
    private TextField TextFieldCedula;
    @javafx.fxml.FXML
    private TextField TextFieldNombre;
    @javafx.fxml.FXML
    private TextField TextFieldPrimerApellido;
    @javafx.fxml.FXML
    private TextField TextFieldSegundoApellido;
    @FXML
    private TextField TextFieldFecNA;
    private PacienteDAO pacienteDAO = new PacienteDAO();
    private Paciente paciente = new Paciente();

    @javafx.fxml.FXML
    public void initialize() {
    }

    @FXML
    void BtnCancelarOnAction(ActionEvent event) {
        // Obtener la referencia del Stage actual
        Stage stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
        // Cerrar el Stage
        stage.close();
    }

    @FXML
    void BtnCrearOnAction(ActionEvent event) {
        paciente.setCedula(Integer.parseInt(TextFieldCedula.getText()));
        paciente.setNombre(TextFieldNombre.getText());
        paciente.setApellido1(TextFieldPrimerApellido.getText());
        paciente.setApellido2(TextFieldSegundoApellido.getText());
        paciente.setFec_Nacimiento(TextFieldFecNA.getText());
        pacienteDAO.agregarPaciente(paciente);
        mostrarMensaje("Registro de Paciente creado con éxito");
        limpiarCampos();
    }
    private void mostrarMensaje(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    private void limpiarCampos() {
        TextFieldCedula.clear();
        TextFieldNombre.clear();
        TextFieldPrimerApellido.clear();
        TextFieldSegundoApellido.clear();
        TextFieldFecNA.clear();
    }
}