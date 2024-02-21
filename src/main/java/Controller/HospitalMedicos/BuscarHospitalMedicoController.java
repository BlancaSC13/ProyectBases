package Controller.HospitalMedicos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;


public class BuscarHospitalMedicoController
{
    @FXML
    private ComboBox<String> ComboBoxDato;

    @FXML
    private TextField TextFieldIDHospital;

    @FXML
    private TextField TextFieldCedula;

    @FXML
    private TextArea TextAreaInfo;

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

        String datoSeleccionado = ComboBoxDato.getValue();
        String idHospital = TextFieldIDHospital.getText();
        String cedulaMedico = TextFieldCedula.getText();

        if (datoSeleccionado != null && !idHospital.isEmpty() && !cedulaMedico.isEmpty()) {
            // Dependiendo de tu lógica de búsqueda, puedes utilizar los datos proporcionados
            // para buscar en tu base de datos o realizar cualquier otra acción.
            String resultado = "Realizar búsqueda con:\nDato: " + datoSeleccionado + "\nID Hospital: " + idHospital + "\nCédula Médico: " + cedulaMedico;
            TextAreaInfo.setText(resultado);
        } else {
            // Mensaje de error si no se proporcionan todos los datos necesarios
            TextAreaInfo.setText("Error: Todos los campos deben estar llenos");
        }
    }




    @FXML
    void BtnCancelarOnAction(ActionEvent event) {
        // Obtener la referencia del Stage actual
        Stage stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
        // Cerrar el Stage
        stage.close();

    }}