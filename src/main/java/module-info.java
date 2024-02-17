module ucr.proyectbases {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ucr.proyectbases to javafx.fxml;
    exports ucr.proyectbases;
    opens Controller to javafx.fxml;
    exports Controller;
    opens Controller.Hospital to javafx.fxml;
    exports Controller.Hospital;
    opens Controller.PacienteTelefonos to javafx.fxml;
    exports Controller.PacienteTelefonos;
    opens Controller.HospitalMedicos to javafx.fxml;
    exports Controller.HospitalMedicos;
    opens Controller.Pacientes to javafx.fxml;
    exports Controller.Pacientes;
    opens Controller.Medicos to javafx.fxml;
    exports Controller.Medicos;
}