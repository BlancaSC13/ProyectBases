package Controller.Hospital;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ucr.proyectbases.HelloApplication;

import java.io.IOException;

public class MenuHospitalController
{
    @javafx.fxml.FXML
    public void initialize() {
    }
    @FXML
    void BtnActualizarHospitalOnAction(ActionEvent event) {
        loadPage2("Hospital/ModificarRegistroHospital.fxml");
    }

    @FXML
    void BtnBorrarHospitalOnAction(ActionEvent event) {
        loadPage2("Hospital/DeleteHospital.fxml");
    }

    @FXML
    void BtnCrearHospitalOnAction(ActionEvent event) {
        loadPage2("Hospital/CrearRegistroHospital.fxml");
    }

    @FXML
    void BtnLeerHospitalOnAction(ActionEvent event) {
        loadPage2("Hospital/ReadHospitales.fxml");
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