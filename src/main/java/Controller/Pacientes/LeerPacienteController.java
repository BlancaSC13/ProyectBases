package Controller.Pacientes;

import DAO.PacienteDAO;
import Objects.Paciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LeerPacienteController
{
    @javafx.fxml.FXML
    private TextField TextFieldCedula;
    @javafx.fxml.FXML
    private TextArea TextAreaInfo;
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
    void BtnLeerOnAction(ActionEvent event) {
        limpiarCampos();
        int cedula_Obtenida = Integer.parseInt(TextFieldCedula.getText());
        paciente = pacienteDAO.obtenerPaciente(cedula_Obtenida);
        if(paciente.getCedula()!= 0) {
            TextAreaInfo.setText(paciente.toString());
        }else{
            mostrarMensaje("Registro no existente");
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