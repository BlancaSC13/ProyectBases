package Controller.HospitalMedicos;

import DAO.HospitalDAO;
import DAO.HospitalMedicoDAO;
import DAO.MedicoDAO;
import Objects.Hospital;
import Objects.HospitalMedico;
import Objects.Medico;
import Util.FXUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EliminarHospitalMedicoController {
    @javafx.fxml.FXML
    private TextField TextFieldIDHospital;
    @javafx.fxml.FXML
    private TextField TextFieldCedula;

    private HospitalDAO hospitalDAO = new HospitalDAO();
    private HospitalMedicoDAO hospitalMedicoDAO = new HospitalMedicoDAO();
    private HospitalMedico hospitalMedico = new HospitalMedico();

    private MedicoDAO medicoDAO = new MedicoDAO();

    private Medico medico = new Medico();

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
    void BtnEliminarOnAction(ActionEvent event) {
        if (TextFieldIDHospital.getText().isEmpty() || TextFieldIDHospital.getText().isEmpty()) {
            mostrarMensaje("Por favor complete los datos");
        } else {
            hospitalMedico.setId_Hospital(Integer.parseInt(TextFieldIDHospital.getText()));
            hospitalMedico.setCedula(Integer.parseInt(TextFieldCedula.getText()));
            String respuesta = FXUtility.alertYesNo("Confirmación", "Borrar Registro", "¿Está seguro de eliminar el registro?");
            if (respuesta.equals("YES")) {
                hospitalMedicoDAO.borrarHospitalMedico(hospitalMedico);
                mostrarMensaje("El eliminaron los datos de forma correcta");
                limpiarCampos();
            } else {
                mostrarMensaje("Registro no eliminado");
            }
        }

    }

    private void limpiarCampos() {
        TextFieldIDHospital.clear();
        TextFieldCedula.clear();
    }

    private void mostrarMensaje(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}