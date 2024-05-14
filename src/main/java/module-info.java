module anderson.c195.dbclientapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


//    opens anderson.c195.dbclientapp to javafx.fxml;
//    exports anderson.c195.dbclientapp;
//    exports anderson.c195.dbclientapp.controller;
//    opens anderson.c195.dbclientapp.controller to javafx.fxml;

    opens anderson.c195 to javafx.fxml;
    exports anderson.c195;
    exports anderson.c195.controller;
    opens anderson.c195.controller to javafx.fxml;
    exports anderson.c195.model;
    opens anderson.c195.model to javafx.fxml;
//    exports anderson.c195.helper;
//    opens anderson.c195.helper to javafx.fxml;

}