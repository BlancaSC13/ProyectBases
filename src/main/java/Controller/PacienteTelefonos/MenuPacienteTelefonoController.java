package Controller.PacienteTelefonos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ucr.proyectbases.HelloApplication;

import java.io.IOException;

public class MenuPacienteTelefonoController {
    @javafx.fxml.FXML
    public void initialize() {
    }

    @FXML
    void BtnMenuActulizarOnAction(ActionEvent event) {
        loadPage2("PacienteTelefonos/ModificarTelefono.fxml");
    }

    @FXML
    void BtnMenuBorrarOnAction(ActionEvent event) {
        loadPage2("PacienteTelefonos/DeleteTelefono.fxml");
    }

    @FXML
    void BtnMenuCrearOnAction(ActionEvent event) {
        loadPage2("PacienteTelefonos/CrearRegistroTelefono.fxml");
    }

    @FXML
    void BtnMenuLeerOnAction(ActionEvent event) {
        loadPage2("PacienteTelefonos/ReadTelefonos.fxml");
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