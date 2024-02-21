package Controller.Medicos;

import DAO.HospitalDAO;
import DAO.MedicoDAO;
import Objects.Medico;
import Util.FXUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BorrarMedicoController {
    @javafx.fxml.FXML
    private TextField TextFieldCedula;
    @javafx.fxml.FXML
    private TextArea TextAreaInfo;

    private MedicoDAO medicoDAO = new MedicoDAO();
    private Medico medico = new Medico();


    @javafx.fxml.FXML
    public void initialize() {
    }

    @FXML
    void BtnBorrarOnAction(ActionEvent event) {
        if (TextFieldCedula.getText().isEmpty()) {
            mostrarMensaje("Por favor ingrese un dato");
        } else {
            int id_Obtenido = Integer.parseInt(TextFieldCedula.getText());
            String respuesta = FXUtility.alertYesNo("Confimación", "Eliminar registro", "¿Está seguro de eliminar el registro?");
            if (respuesta.equals("YES")) {
                medicoDAO.borrarMedico(id_Obtenido);
                mostrarMensaje("Medico borrado correctamente");
            } else {
                mostrarMensaje("Registro no eliminado");
            }
        }
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
    void BtnLeerOnAction(ActionEvent event) {
        limpiarCampos();
        if (TextFieldCedula.getText().isEmpty()) {
            mostrarMensaje("Por favor ingrese un dato para buscar");
        } else {
            int cedula_Obtenida = Integer.parseInt(TextFieldCedula.getText());
            medico = medicoDAO.obtenerMedico(cedula_Obtenida);
            if (medico.getCedula() != 0) {
                TextAreaInfo.setText(medico.toString());
            } else {
                mostrarMensaje("Registro no existente");
            }
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