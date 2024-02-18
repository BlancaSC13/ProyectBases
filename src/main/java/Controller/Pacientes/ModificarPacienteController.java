package Controller.Pacientes;

import DAO.PacienteDAO;
import Objects.Paciente;
import Util.FXUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ModificarPacienteController {
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
    void BtnModificarOnAction(ActionEvent event) {
        paciente.setCedula(Integer.parseInt(TextFieldCedula.getText()));
        paciente.setNombre(TextFieldNombre.getText());
        paciente.setApellido1(TextFieldPrimerApellido.getText());
        paciente.setApellido2(TextFieldSegundoApellido.getText());
        paciente.setFec_Nacimiento(TextFieldFecNA.getText());
        String respuesta = FXUtility.alertYesNo("Confirmación", "Modificar registro", "¿Está seguro que desea modificar este registro?");
        if (respuesta.equals("YES")) {
            if (pacienteDAO.actualizarPaciente(paciente)) {
                mostrarMensaje("Registro de Paciente actualizado con éxito");
            } else {
                mostrarMensaje("No se pudo actualizar el registro del Paciente porque no existe.");
            }
        } else {
            mostrarMensaje("El registro no se ha actualizado");
        }
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