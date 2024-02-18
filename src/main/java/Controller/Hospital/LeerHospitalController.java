package Controller.Hospital;

import DAO.HospitalDAO;
import Objects.Hospital;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LeerHospitalController
{
    @javafx.fxml.FXML
    private TextField TextFieldIDHospital;
    @javafx.fxml.FXML
    private TextArea TextAreaInfo;
    private HospitalDAO hospitalDAO = new HospitalDAO();
    private Hospital hospital = new Hospital();

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
        int id_Obtenido = Integer.parseInt(TextFieldIDHospital.getText());
        hospital = hospitalDAO.obtenerHospital(id_Obtenido);
        if(this.hospital!= null) {
            TextAreaInfo.setText(hospital.toString());
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