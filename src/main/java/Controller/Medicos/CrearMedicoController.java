package Controller.Medicos;

import DAO.MedicoDAO;
import Objects.Medico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CrearMedicoController {
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
    private Medico medico = new Medico();
    private MedicoDAO medicoDAO = new MedicoDAO();

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
        medico.setCedula(Integer.parseInt(TextFieldCedula.getText()));
        medico.setNombre(TextFieldNombre.getText());
        medico.setApellido1(TextFieldPrimerApellido.getText());
        medico.setApellido2(TextFieldSegundoApellido.getText());
        medico.setFec_Nacimiento(TextFieldFecNA.getText());
        medicoDAO.agregarMedico(medico);
        mostrarMensaje("Registro creado con éxito");
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