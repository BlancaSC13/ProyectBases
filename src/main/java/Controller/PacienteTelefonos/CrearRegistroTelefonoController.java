package Controller.PacienteTelefonos;

import DAO.PacienteDAO;
import DAO.TelefonoPacienteDAO;
import Objects.TelefonoPaciente;
import Util.FXUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CrearRegistroTelefonoController
{
    @javafx.fxml.FXML
    private TextField TestFieldCedula;
    @javafx.fxml.FXML
    private TextField TextFieldTelefono;

    private PacienteDAO pacienteDAO = new PacienteDAO();
    private TelefonoPacienteDAO telefonoPacienteDAO = new TelefonoPacienteDAO();
    private TelefonoPaciente telefonoPaciente = new TelefonoPaciente();

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
    void BtnCrearOnAction(ActionEvent event) {
        int cedulaObtenida = Integer.parseInt(TestFieldCedula.getText());
        if(pacienteDAO.obtenerPaciente(cedulaObtenida).getCedula()!=0){
            telefonoPaciente.setCedula(cedulaObtenida);
            telefonoPaciente.setTelefono(Integer.parseInt(TextFieldTelefono.getText()));
            telefonoPacienteDAO.agregarTelefonoPaciente(telefonoPaciente);
            mostrarMensaje("El teléfono se registró con éxito");
        }else{
            mostrarMensaje("El número de cédula no se encuentra registrado en la base de datos.");
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
    private void limpiarCampos(){
        TextFieldTelefono.clear();
        TestFieldCedula.clear();
    }
}