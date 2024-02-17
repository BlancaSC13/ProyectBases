package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ucr.proyectbases.HelloApplication;

import java.io.IOException;

public class MainMenuController
{
    @javafx.fxml.FXML
    public void initialize() {

    }
    @FXML
    void BtnHospital(ActionEvent event) {
        loadPage2("Hospital/MenuHospitales.fxml");

    }

    @FXML
    void BtnMedicoHospital(ActionEvent event) {
        loadPage2("HospitalMedicos/MenuHopitalMedico.fxml");

    }

    @FXML
    void BtnMedicos(ActionEvent event) {
        loadPage2("Medicos/MenuMedicos.fxml");

    }

    @FXML
    void BtnPacientes(ActionEvent event) {
        loadPage2("Pacientes/MenuPaciente.fxml");
    }

    @FXML
    void BtnTelefonoPaciente(ActionEvent event) {
        loadPage2("PacienteTelefonos/MenuTelefonosPaciente.fxml");
    }
    private void loadPage2(String page) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage= new Stage();
        stage.setScene(scene);
        stage.show();
    }
}