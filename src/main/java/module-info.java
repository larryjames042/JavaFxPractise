module com.example.javafxpractise {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.example.javafxpractise to javafx.fxml;
    opens com.example.javafxpractise.model to javafx.base;
    opens com.example.javafxpractise.controller to javafx.fxml;
    exports com.example.javafxpractise;
    exports com.example.javafxpractise.controller;

}