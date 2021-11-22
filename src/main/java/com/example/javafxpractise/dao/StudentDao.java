package com.example.javafxpractise.dao;

import com.example.javafxpractise.model.Religion;
import com.example.javafxpractise.model.Student;
import javafx.beans.value.ObservableObjectValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDao {
    private static final String tableName = "students";
    private static final String religionTableName = "religion";

    private static final String studentIdColumn = "student_id";
    private static final String rollNumberColumn = "roll_number";
    private static final String studentNameColumn = "student_name";
    private static final String fathernameColumn = "father_name";
    private static final String nrcColumn = "student_nrc";
    private static final String dobColumn = "date_of_birth";
    private static final String religionColumn = "religion";
    private static final String genderColumn = "gender";
    private static final String addressColumn = "address";
    public static final String studentPhotoUrlColumn = "student_photo";
    public static final String remarkColumn = "remark";

    private static ObservableList<Student> students;
    private static Religion religion;

    static {
        students = FXCollections.observableArrayList();
        updateUsersFromDB();
    }

    public static ObservableList<Student> getStudents() {
        return FXCollections.unmodifiableObservableList(students);
    }

    public static String getReligionName(int religionId){
        String queryString = "SELECT * FROM " + religionTableName + " WHERE id = " + religionId;
        try (Connection connection = Database.connect()) {
            PreparedStatement statement = connection.prepareStatement(queryString);
            ResultSet rs = statement.executeQuery();
            religion = new Religion(rs.getInt("id"), rs.getString("name"));
            return religion.getName();
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ": Could not load Religion from database ");
            return "";
        }
    }

    private static void updateUsersFromDB(){
        String queryString = "SELECT * FROM " + tableName;
        try (Connection connection = Database.connect()) {
            PreparedStatement statement = connection.prepareStatement(queryString);
            ResultSet rs = statement.executeQuery();
            students.clear();
            while (rs.next()) {
                String religion = getReligionName(rs.getInt(religionColumn));
                students.add(new Student(
                        rs.getString(studentIdColumn),
                        rs.getString(rollNumberColumn),
                        rs.getString(studentNameColumn),
                        rs.getString(fathernameColumn),
                        rs.getString(nrcColumn),
                        rs.getString(dobColumn),
                        religion,
                        rs.getString(genderColumn),
                        rs.getString(addressColumn),
                        rs.getString(studentPhotoUrlColumn),
                        rs.getString(remarkColumn)
                        ));
            }
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ": Could not load Student from database ");
            students.clear();
        }
    }

}
