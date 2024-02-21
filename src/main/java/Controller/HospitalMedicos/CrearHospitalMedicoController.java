package Controller.HospitalMedicos;

import DAO.HospitalDAO;
import DAO.HospitalMedicoDAO;
import DAO.MedicoDAO;
import Objects.Hospital;
import Objects.HospitalMedico;
import Objects.Medico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CrearHospitalMedicoController {

    @FXML
    private TextField TextFieldIDHospital;

    @FXML
    private TextField TextFieldCedula;

    private HospitalDAO hospitalDAO = new HospitalDAO();
    private HospitalMedicoDAO hospitalMedicoDAO = new HospitalMedicoDAO();
    private HospitalMedico hospitalMedico = new HospitalMedico();

    private MedicoDAO medicoDAO = new MedicoDAO();

    private Medico medico = new Medico();

    private Hospital hospital = new Hospital();



    @FXML
    public void initialize() {
        // Puedes realizar algunas operaciones de inicialización si es necesario
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
        try {
            int id_HospitalObtenido = Integer.parseInt(TextFieldIDHospital.getText());
            int cedulaMedico = Integer.parseInt(TextFieldCedula.getText());

            // Verificar si el hospital existe
            Hospital hospital = hospitalDAO.obtenerHospital(id_HospitalObtenido);
            if (hospital == null || hospital.getId() == 0) {
                mostrarMensaje("Error: El hospital no existe en la base de datos.");
                return;
            }


            // Verificar si la cédula del médico es válida (puedes agregar más lógica según tus necesidades)
            if (cedulaMedico == 0) {
                mostrarMensaje("Error: La cédula del médico no es válida.");
                return;
            }

            // Verificar si el médico existe
            Medico medico = medicoDAO.obtenerMedico(cedulaMedico);
            if (medico == null || medico.getCedula() == 0) {
                mostrarMensaje("Error: El médico no existe en la base de datos.");
                return;
            }

            // Configurar el médico y agregarlo al hospital
            hospitalMedico.setId_Hospital(id_HospitalObtenido);
            hospitalMedico.setCedula(cedulaMedico);

            hospitalMedicoDAO.agregarHospitalMedico(hospitalMedico);
            mostrarMensaje("El médico se registró con éxito en el hospital");

            limpiarCampos();

        } catch (NumberFormatException e) {
            mostrarMensaje("Ingrese números válidos para el ID del Hospital y la Cédula del Médico.");
            // Realiza alguna acción adicional si es necesario.
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
        TextFieldCedula.clear();
    }
}
