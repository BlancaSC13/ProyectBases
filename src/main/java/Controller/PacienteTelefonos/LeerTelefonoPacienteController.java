package Controller.PacienteTelefonos;

import DAO.TelefonoPacienteDAO;
import Objects.TelefonoPaciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class LeerTelefonoPacienteController {
    @javafx.fxml.FXML
    private TextField TextFieldCedula;
    @javafx.fxml.FXML
    private TextArea TextAreaInfo;
    private TelefonoPacienteDAO telefonoPacienteDAO = new TelefonoPacienteDAO();

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
        if (TextFieldCedula.getText().isEmpty()){
            mostrarMensaje("Por favor ingrese un dato para buscar");
        }else {
            int cedula_Obtenida = Integer.parseInt(TextFieldCedula.getText());
            List<TelefonoPaciente> telefonosPaciente = telefonoPacienteDAO.obtenerTelefonos(cedula_Obtenida);
            if (telefonosPaciente.isEmpty()) {
                mostrarMensaje("Registro no existente");
            } else {
                TextAreaInfo.setText(telefonosPaciente.toString());
            }
        }
    }

    private void mostrarMensaje(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void limpiarCampos() {
        TextAreaInfo.clear();
    }

}