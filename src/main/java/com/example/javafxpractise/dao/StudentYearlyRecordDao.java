package com.example.javafxpractise.dao;

import com.example.javafxpractise.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentYearlyRecordDao {
    // tables names
    private static final String yearlyRecordTableName = "student_yearly_record";
    private static final String standardTableName = "standard";
    private static final String academicYearTableName = "academic_year";
    private static final String sponsorshipTableName = "sponsorship";

    // yearly record columns
    private static final String recordId = "id";
    private static final String studentIdColumn = "student_id";
    private static final String standardColumn = "standard";
    private static final String academicYearColumn = "academic_year";
    private static final String lodgingColumn = "lodging";
    private static final String dropColumn = "drop";
    private static final String sponsorshipColumn = "sponsorship";
    private static final String baptizedColumn = "baptized_this_year";
    private static final String remarkColumn= "remark";

    // standard Column
    private static final String standardIdCol = "id";
    private static final String standardNameCol = "name";

    // standard columns
    private static final String standardIdColumn = "id";
    private static final String standardNameColumn = "name";

    // academic year Table columns
    private static final String academicYearIdColumn = "id";
    private  static final String academicYearNameColumn = "name";

    // sponsorship table columns;
    private static final String sponsorshipIdColumn  ="id";
    private static final String sponsorshipNameColumn  = "name";
    private static final String sponsorshipDescriptionColumn  = "description";

    private static ObservableList<StudentYearlyRecord> yearlyRecordList;
    private static ObservableList<Standard> standardList;
    private static ObservableList<AcademicYear> academicYearsList;
    private static ObservableList<Sponsorship> sponsorshipsList;
//    private static ObservableList<>
    private static Religion religion;

    static {
        yearlyRecordList = FXCollections.observableArrayList();
        standardList = FXCollections.observableArrayList();
        academicYearsList= FXCollections.observableArrayList();
        sponsorshipsList = FXCollections.observableArrayList();
    }

    public static ObservableList<StudentYearlyRecord> getYearlyRecord(String studentId) {
        getRecordFromDB(studentId);
        return FXCollections.unmodifiableObservableList(yearlyRecordList);
    }

    public static ObservableList<Standard> getStandard(){
        getStandardList();
        return FXCollections.unmodifiableObservableList(standardList);
    }

    public static ObservableList<AcademicYear> getAcademicYear(){
        getAcademicYearsList();
        return FXCollections.unmodifiableObservableList(academicYearsList);
    }

    public static ObservableList<Sponsorship> getSponsorship(){
        getSponsorshipList();
        return FXCollections.unmodifiableObservableList(sponsorshipsList);
    }

    private static void  getSponsorshipList(){
        String queryString = "SELECT * FROM " + sponsorshipTableName;
        try (Connection connection = Database.connect()) {
            PreparedStatement statement = connection.prepareStatement(queryString);
            ResultSet rs = statement.executeQuery();
            sponsorshipsList.clear();
            while (rs.next()) {
                sponsorshipsList.add(new Sponsorship(
                        rs.getInt(sponsorshipIdColumn),
                        rs.getString(sponsorshipNameColumn),
                        rs.getString(sponsorshipDescriptionColumn)
                ));
            }
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ": Could not load Standard Table from database " + e.getMessage());
            sponsorshipsList.clear();
        }
    }

    private static void getAcademicYearsList(){
        String queryString = "SELECT * FROM " + academicYearTableName;
        try (Connection connection = Database.connect()) {
            PreparedStatement statement = connection.prepareStatement(queryString);
            ResultSet rs = statement.executeQuery();
            academicYearsList.clear();
            while (rs.next()) {
                academicYearsList.add(new AcademicYear(
                        rs.getInt(academicYearIdColumn),
                        rs.getString(academicYearNameColumn)
                ));
            }
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ": Could not load Standard Table from database " + e.getMessage());
            academicYearsList.clear();
        }
    }

    private static void getStandardList(){
        String queryString = "SELECT * FROM " + standardTableName;
        try (Connection connection = Database.connect()) {
            PreparedStatement statement = connection.prepareStatement(queryString);
            ResultSet rs = statement.executeQuery();
            standardList.clear();
            while (rs.next()) {
                standardList.add(new Standard(
                        rs.getInt(standardIdColumn),
                        rs.getString(standardNameColumn)
                ));
            }
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ": Could not load Standard Table from database " + e.getMessage());
            standardList.clear();
        }
    }

//    public static String getReligionName(int religionId){
//        String queryString = "SELECT * FROM " + yearlyRecordTableName + " WHERE id = " + religionId;
//        try (Connection connection = Database.connect()) {
//            PreparedStatement statement = connection.prepareStatement(queryString);
//            ResultSet rs = statement.executeQuery();
//            religion = new Religion(rs.getInt("id"), rs.getString("name"));
//            return religion.getName();
//        } catch (SQLException e) {
//            Logger.getAnonymousLogger().log(
//                    Level.SEVERE,
//                    LocalDateTime.now() + ": Could not load Religion from database ");
//            return "";
//        }
//    }

    private static void getRecordFromDB(String id) {
        String queryString = "SELECT * FROM " + yearlyRecordTableName + " WHERE "+ "student_id = " + "\""+id+"\"";
        try (Connection connection = Database.connect()) {
            PreparedStatement statement = connection.prepareStatement(queryString);
            ResultSet rs = statement.executeQuery();
            yearlyRecordList.clear();
            while (rs.next()) {
                yearlyRecordList.add(new StudentYearlyRecord(
                        rs.getInt(recordId),
                        rs.getString(studentIdColumn),
                        String.valueOf(rs.getInt(standardColumn)),
                        String.valueOf(rs.getInt(academicYearColumn)),
                        String.valueOf(rs.getString(lodgingColumn)),
                        String.valueOf(rs.getInt(dropColumn)),
                        String.valueOf(rs.getInt(sponsorshipColumn)),
                        String.valueOf(rs.getInt(baptizedColumn)),
                        rs.getString(remarkColumn)
                ));
            }
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ": Could not load YearlyRecordTable from database " + e.getMessage());
            yearlyRecordList.clear();
        }
    }
}
