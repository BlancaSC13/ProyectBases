package Controller.Hospital;

import DAO.HospitalDAO;
import Objects.Hospital;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CrearHospitalController {
    @javafx.fxml.FXML
    private TextField TextFieldNombre;
    @javafx.fxml.FXML
    private TextField TextFieldDireccion;
    private HospitalDAO hospitalDAO = new HospitalDAO();

    @javafx.fxml.FXML
    public void initialize() {

    }

    @FXML
    void BtnCancelar(ActionEvent event) {
        // Obtener la referencia del Stage actual
        Stage stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
        // Cerrar el Stage
        stage.close();
    }

    @FXML
    void BtnCrear(ActionEvent event) {
        String nombre = TextFieldNombre.getText();
        String direccion = TextFieldDireccion.getText();

        Hospital nuevoHospital = new Hospital();
        nuevoHospital.setNombre(nombre);
        nuevoHospital.setDireccion(direccion);

        hospitalDAO.agregarHospital(nuevoHospital);

        mostrarMensaje("Hospital creado correctamente");
        limpiarCampos();
    }

    private void limpiarCampos() {
        TextFieldNombre.clear();
        TextFieldDireccion.clear();
    }

    private void mostrarMensaje(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}