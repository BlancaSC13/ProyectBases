package Controller.Medicos;

import DAO.MedicoDAO;
import Objects.Medico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LeerMedicoController {
    @javafx.fxml.FXML
    private TextField TextFieldCedula;
    @javafx.fxml.FXML
    private TextArea TextAreaInfo;
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
    void BtnLeerOnAction(ActionEvent event) {
        limpiarCampos();
        if (TextFieldCedula.getText().isEmpty()) {
            mostrarMensaje("Por favor ingrese un dato para buscar");
        } else {
            int cedula_Obtenida = Integer.parseInt(TextFieldCedula.getText());
            medico = medicoDAO.obtenerMedico(cedula_Obtenida);
            if (medico.getCedula() != 0) {
                TextAreaInfo.setText(medico.toString());
            } else {
                mostrarMensaje("Registro no existente");
            }
        }
    }

    private void limpiarCampos() {
        TextAreaInfo.clear();
    }

    private void mostrarMensaje(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}