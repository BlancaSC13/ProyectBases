package Controller.HospitalMedicos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ucr.proyectbases.HelloApplication;

import java.io.IOException;

public class MenuHospitalesMedicosController
{
    @javafx.fxml.FXML
    public void initialize() {
    }
    @FXML
    void BtnMenuActualizar(ActionEvent event) {
        loadPage2("HospitalMedicos/ModificarHospitalMedico.fxml");
    }

    @FXML
    void BtnMenuBorrar(ActionEvent event) {
        loadPage2("HospitalMedicos/EliminarHospitalMedico.fxml");
    }

    @FXML
    void BtnMenuCrear(ActionEvent event) {

        loadPage2("HospitalMedicos/CrearHospitalMedico.fxml");
    }

    @FXML
    void BtnMenuLeer(ActionEvent event) {
        loadPage2("HospitalMedicos/ReadHospitalMedico.fxml");
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