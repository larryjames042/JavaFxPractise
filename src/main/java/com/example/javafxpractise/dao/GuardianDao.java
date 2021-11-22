package com.example.javafxpractise.dao;

import com.example.javafxpractise.model.Guardian;
import com.example.javafxpractise.model.Religion;
import com.example.javafxpractise.model.Student;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.security.Guard;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GuardianDao {
    private static final String guardianTableName = "guardian";


    private static final String guardianId = "id";
    private static final String studentId = "student_id";
    private static final String guardianName = "guardian_name";
    private static final String address = "address";
    private static final String guardianType = "guardian_type";
    private static final String guardianPhone = "guardian_phone";

//    private static ObservableList<Guardian> guardianList;

    private static Guardian guardian;

    static {
//        guardianList = FXCollections.observableArrayList();
    }

//    public static ObservableList<Guardian> getStudents() {
//        return FXCollections.unmodifiableObservableList(guardianList);
//    }

    public static Guardian getGuardianInfo(String studId){
        String queryString = "SELECT * FROM " + guardianTableName + " WHERE student_id = " + "\"" + studId + "\"";
        try (Connection connection = Database.connect()) {
            PreparedStatement statement = connection.prepareStatement(queryString);
            ResultSet rs = statement.executeQuery();
            guardian = new Guardian(rs.getInt(guardianId),
                    rs.getInt(studentId),
                    rs.getString(guardianName),
                    rs.getString(address),
                    rs.getString(guardianType),
                    rs.getString(guardianPhone)
                    );
            return guardian;
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ": Could not load Guardian from database " + e.getMessage().toString());
            return null;
        }
    }
}
