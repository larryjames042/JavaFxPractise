package com.example.javafxpractise.controller;

import com.example.javafxpractise.MainApp;
import com.example.javafxpractise.dao.StudentDao;
import com.example.javafxpractise.model.Student;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeSceneController implements Initializable {

    @FXML
    private StackPane mainContent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/StudentScene.fxml"));
            Parent fxml = loader.load();
            mainContent.getChildren().removeAll();
            mainContent.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void student(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/StudentScene.fxml"));
        Parent fxml = loader.load();
        mainContent.getChildren().removeAll();
        mainContent.getChildren().setAll(fxml);
    }

    public void staff(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/StaffScene.fxml"));
        Parent fxml = loader.load();
        mainContent.getChildren().removeAll();
        mainContent.getChildren().setAll(fxml);

    }

    public void classes(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/ClassScene.fxml"));
        Parent fxml = loader.load();
        mainContent.getChildren().removeAll();
        mainContent.getChildren().setAll(fxml);
    }


}
