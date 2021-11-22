package com.example.javafxpractise.dao;

import com.example.javafxpractise.model.Person;
import com.example.javafxpractise.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    private static final String tableName = "Users";
    private static final String userNameColumn = "username";
    private static final String passwordColumn = "password";

    private static ObservableList<User> users;

    static {
        users = FXCollections.observableArrayList();
        updateUsersFromDB();
    }

    public static ObservableList<User> getUsers() {
        return FXCollections.unmodifiableObservableList(users);
    }

    private static void updateUsersFromDB(){
        String queryString = "SELECT * FROM " + tableName;
        try (Connection connection = Database.connect()) {
            PreparedStatement statement = connection.prepareStatement(queryString);
            ResultSet rs = statement.executeQuery();
            users.clear();
            while (rs.next()) {
                users.add(new User(
                        rs.getString(userNameColumn),
                        rs.getString(passwordColumn)));
            }
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ": Could not load User from database ");
            users.clear();
        }
    }

}
