package Controller.Hospital;

import DAO.HospitalDAO;
import Objects.Hospital;
import Util.FXUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModificarHospitalController {
    @javafx.fxml.FXML
    private TextField TextFieldIdHospital;
    @javafx.fxml.FXML
    private TextField TextFieldNombreHospital;
    @javafx.fxml.FXML
    private TextField TextFieldDireccionHospital;
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
    void BtnModificarHospitalOnAction(ActionEvent event) {
        hospital.setId(Integer.parseInt(TextFieldIdHospital.getText()));
        hospital.setNombre(TextFieldNombreHospital.getText());
        hospital.setDireccion(TextFieldDireccionHospital.getText());
        String respuesta = FXUtility.alertYesNo("Confimación", "Modificar registro","¿Está seguro de modificar el registro?");
        if (respuesta.equals("YES")) {
            if (hospitalDAO.actualizarHospital(hospital)) {
                mostrarMensaje("Registro actualizado con éxito");
            } else {
                mostrarMensaje("No se pudo actualizar el registro porque no existe.");
            }
        }else{
            mostrarMensaje("Registro no modificado.");
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
        TextFieldDireccionHospital.clear();
        TextFieldIdHospital.clear();
        TextFieldNombreHospital.clear();
    }
}