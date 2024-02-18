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

public class ModificarTelefonoPacienteController {
    @javafx.fxml.FXML
    private TextField TextFieldCedula;
    @FXML
    private TextField TextFieldTelefonoViejo;
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
    void BtnModificarOnAction(ActionEvent event) {
        int cedulaObtenida = Integer.parseInt(TextFieldCedula.getText());
        if (pacienteDAO.obtenerPaciente(cedulaObtenida).getCedula() != 0) {
            telefonoPaciente.setCedula(cedulaObtenida);
            telefonoPaciente.setTelefono(Integer.parseInt(TextFieldTelefono.getText()));
            int telefonoViejo = Integer.parseInt(TextFieldTelefonoViejo.getText());
            String respuesta = FXUtility.alertYesNo("Confirmación", "Modificar registro", "¿Está seguro de modificar el registro??");
            if (respuesta.equals("YES")) {
                telefonoPacienteDAO.actualizarTelefonoPaciente(telefonoPaciente, telefonoViejo);
                mostrarMensaje("El número de teléfono se modificó con éxito");
            } else {
                mostrarMensaje("El registro no ha sido modificado");
            }

        } else {
            mostrarMensaje("El número de cédula no se encuentra registrado en la base de datos.");
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
        TextFieldTelefono.clear();
        TextFieldCedula.clear();
    }
}