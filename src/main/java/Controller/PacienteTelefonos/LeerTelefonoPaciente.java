package Controller.PacienteTelefonos;

import DAO.TelefonoPacienteDAO;
import Objects.TelefonoPaciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class LeerTelefonoPaciente
{
    @javafx.fxml.FXML
    private TextField TextFieldCedula;
    @javafx.fxml.FXML
    private TextArea TextAreaInfo;
    private TelefonoPacienteDAO telefonoPacienteDAO = new TelefonoPacienteDAO();

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
    int cedula_Obtenida = Integer.parseInt(TextFieldCedula.getText());
    List<TelefonoPaciente> telefonosPaciente = telefonoPacienteDAO.obtenerTelefonos(cedula_Obtenida);
    TextAreaInfo.setText(telefonosPaciente.toString());
    }
}