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

public class ModificarHospitalesMedicosController
{
    @FXML
    private TextField TextFieldIDHospitalViejo;

    @FXML
    private TextField TextFieldIDHospital;

    @FXML
    private TextField TextFieldCedula;

    private HospitalMedicoDAO hospitalMedicoDAO = new HospitalMedicoDAO();


    private HospitalDAO hospitalDAO = new HospitalDAO();

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
    void BtnModificarHospitalMedicoOnAction(ActionEvent event) {


        try {
            int id_HospitalAntiguo = Integer.parseInt(TextFieldIDHospitalViejo.getText());
            int nuevoId_Hospital = Integer.parseInt(TextFieldIDHospital.getText());
            int cedulaMedico = Integer.parseInt(TextFieldCedula.getText());

            if (hospitalMedicoDAO.actualizarIDHospitalMedico(id_HospitalAntiguo, nuevoId_Hospital, cedulaMedico)) {
                mostrarMensaje("Se actualizó el Id_Hospital correctamente.");
            } else {
                mostrarMensaje("No se pudo actualizar el Id_Hospital.");
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Ingrese números válidos para los campos.");
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
        TextFieldIDHospital.clear();
        TextFieldIDHospitalViejo.clear();
        TextFieldCedula.clear();
    }
}