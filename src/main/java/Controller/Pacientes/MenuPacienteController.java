package Controller.Pacientes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ucr.proyectbases.HelloApplication;

import java.io.IOException;

public class MenuPacienteController
{
    @javafx.fxml.FXML
    public void initialize() {
    }
    @FXML
    void BtnMenuActualizarOnAction(ActionEvent event) {
        loadPage2("Pacientes/ModificarRegistroPaciente.fxml");
    }

    @FXML
    void BtnMenuBorrarOnAction(ActionEvent event) {
        loadPage2("Pacientes/DeletePaciente.fxml");
    }

    @FXML
    void BtnMenuCrearOnAction(ActionEvent event) {
        loadPage2("Pacientes/CrearRegistroPaciente.fxml");
    }

    @FXML
    void BtnMenuLeerOnAction(ActionEvent event) {
        loadPage2("Pacientes/ReadPaciente.fxml");
    }

    private void loadPage2(String page) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}

