module org.example.petshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

    exports org.example.petshop.controller;
    opens org.example.petshop.controller to javafx.fxml;
    exports org.example.petshop;
    opens org.example.petshop to javafx.fxml;
}