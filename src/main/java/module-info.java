module ucr.proyectbases {
    requires javafx.controls;
    requires javafx.fxml;


    opens ucr.proyectbases to javafx.fxml;
    exports ucr.proyectbases;
    opens Controller to javafx.fxml;
    exports Controller;
}