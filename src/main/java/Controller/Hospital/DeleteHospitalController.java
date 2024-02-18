package Controller.Hospital;

import DAO.HospitalDAO;
import Objects.Hospital;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeleteHospitalController {
    @javafx.fxml.FXML
    private TextField TextFieldIdHospital;
    @javafx.fxml.FXML
    private TextArea TextAreaInfo;
    private HospitalDAO hospitalDAO = new HospitalDAO();
    private Hospital hospital = new Hospital();

    @javafx.fxml.FXML
    public void initialize() {
    }

    @FXML
    void BtnBorrarOnAction(ActionEvent event) {
        int id_Obtenido = Integer.parseInt(TextFieldIdHospital.getText());
        hospitalDAO.borrarHospital(id_Obtenido);
        mostrarMensaje("Hospital borrado correctamente");
        limpiarCampos();

    }

    @FXML
    void BtnCancelarOnAction(ActionEvent event) {
        // Obtener la referencia del Stage actual
        Stage stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
        // Cerrar el Stage
        stage.close();
    }

    @FXML
    void BtnCargarOnAction(ActionEvent event) {
        limpiarCampos();
        int id_Obtenido = Integer.parseInt(TextFieldIdHospital.getText());
        hospital = hospitalDAO.obtenerHospital(id_Obtenido);
        if(hospital!= null) {
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