package com.example.javafxpractise;

import com.example.javafxpractise.dao.Database;
import com.example.javafxpractise.util.Alerts;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;


    private static final String location = MainApp.class.getResource("database/database.db").toExternalForm();

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/LoginScene.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNIFIED);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

//    private void launchSqlite() throws IOException {
//        //TODO: the checks here take some time, so I would advise a splash screen
//        if (Database.isOK()) {
//            FXMLLoader loader= new FXMLLoader();
//            loader.setLocation(MainApp.class.getResource("view/sqLiteView.fxml"));
//
//            Parent root = loader.load();
//            primaryStage.setTitle("Connecting SQLite to JavaFX");
//            primaryStage.setScene(new Scene(root));
//            primaryStage.initStyle(StageStyle.UNDECORATED);
//            primaryStage.show();
//        } else {
//            Alerts.error(
//                    "Database error",
//                    "Could not load database",
//                    "Error loading SQLite database. See log. Quitting..."
//            ).showAndWait();
//            Platform.exit();
//        }
//    }

    public static void main(String[] args) {
        launch();
    }
}