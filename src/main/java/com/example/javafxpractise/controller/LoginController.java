package com.example.javafxpractise.controller;
import java.io.IOException;
import java.nio.charset.CharsetEncoder;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.javafxpractise.MainApp;
import com.example.javafxpractise.dao.UserDAO;
import com.example.javafxpractise.model.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    TextField userNameTextField;
    @FXML
    PasswordField passwordTextField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void login(ActionEvent event)throws IOException{
        String username = userNameTextField.getText();
        String password = passwordTextField.getText();
        if(username!=null && password !=null){
            User user = new User(username, password);
            ObservableList<User> users = UserDAO.getUsers();
            if(users.get(0).getUserName() == user.getUserName() &&
                users.get(0).getPassword() == user.getPassword()
            ){
                Logger.getAnonymousLogger().log(
                        Level.SEVERE,
                        LocalDateTime.now() + ": Login Success ");
            }else{
                Logger.getAnonymousLogger().log(
                        Level.SEVERE,
                        LocalDateTime.now() + user.getUserName()+ user.getPassword() +  ": Check user name or Password\n  "+ users.toString());
            }
        }
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/HomeScene.fxml"));
        root = loader.load();
//        HomeSceneController homeSceneController = loader.getController();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("SDA School Management");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

}
