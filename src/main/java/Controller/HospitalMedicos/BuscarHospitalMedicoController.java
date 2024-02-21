package Controller.HospitalMedicos;

import DAO.HospitalDAO;
import DAO.HospitalMedicoDAO;
import Objects.HospitalMedico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class BuscarHospitalMedicoController {
    @FXML
    private ComboBox<String> ComboBoxDato;

    @FXML
    private TextField TextFieldIDHospital;

    @FXML
    private TextField TextFieldCedula;

    @FXML
    private TextArea TextAreaInfo;
    private HospitalMedicoDAO hospitalMedicoDAO = new HospitalMedicoDAO();

    @FXML
    public void initialize() {
        // Inicializar el ComboBox con opciones de búsqueda
        ObservableList<String> opcionesBusqueda = FXCollections.observableArrayList(
                "Buscar por ID Hospital",
                "Buscar por Cédula del Médico"
        );
        ComboBoxDato.setItems(opcionesBusqueda);

        // Seleccionar la primera opción por defecto
        ComboBoxDato.getSelectionModel().selectFirst();
    }


    @FXML
    void BtnBuscarOnAction(ActionEvent event) {
        limpiarCampos();
        String datoSeleccionado = ComboBoxDato.getValue();
        String idHospital = TextFieldIDHospital.getText();
        String cedulaMedico = TextFieldCedula.getText();
        List<HospitalMedico> datosObtenidos;
        if (datoSeleccionado.equals("Buscar por ID Hospital") && !idHospital.isEmpty()) {
            datosObtenidos = hospitalMedicoDAO.obtenerHospitalMedico(Integer.parseInt(idHospital));
            if (datosObtenidos.isEmpty()) {
                mostrarMensaje("Registro no existente");
            } else {
                TextAreaInfo.setText(datosObtenidos.toString());
            }
        } else if (datoSeleccionado.equals("Buscar por Cédula del Médico") && !cedulaMedico.isEmpty()) {
            datosObtenidos = hospitalMedicoDAO.obtenerHospitalMedico2(Integer.parseInt(cedulaMedico));
            if (datosObtenidos.isEmpty()) {
                mostrarMensaje("Registro no existente");
            } else {
                TextAreaInfo.setText(datosObtenidos.toString());
            }
        }else{
            mostrarMensaje("Por favor ingrese un dato válido");
        }

    }


    @FXML
    void BtnCancelarOnAction(ActionEvent event) {
        // Obtener la referencia del Stage actual
        Stage stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
        // Cerrar el Stage
        stage.close();

    }

    private void mostrarMensaje(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void limpiarCampos() {
        TextAreaInfo.clear();
    }

}