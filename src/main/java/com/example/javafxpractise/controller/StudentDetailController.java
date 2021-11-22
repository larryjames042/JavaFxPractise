package com.example.javafxpractise.controller;

import com.example.javafxpractise.dao.GuardianDao;
import com.example.javafxpractise.dao.StudentYearlyRecordDao;
import com.example.javafxpractise.model.*;
import com.example.javafxpractise.util.HelperClass;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.*;

public class StudentDetailController implements Initializable {

    // Standardlist , AcademicYearList , SponsorshipList
    private Map<String, String> standardMap;
    private Map<String, String> academicYearsMap;
    private Map<String, String> sponsorshipMap;

    // Student Info
    @FXML
    TextField studentIdTextField;
    @FXML
    TextField rollNumberTextField;
    @FXML
    TextField studentNameTextField;
    @FXML
    TextField fatherNameTextField;
    @FXML
    TextField nrcTextField;
    @FXML
    DatePicker dobDatePicker;
    @FXML
    TextField addressTextField;
    @FXML
    TextField remarkTextField;
    @FXML
    ChoiceBox religionChoice;
    @FXML
    ChoiceBox genderChoice;

    // Add new academic_year info
    @FXML
    ChoiceBox standardChoice;
    @FXML
    ChoiceBox academicYearChoice;
    @FXML
    ChoiceBox lodgingChoice;
    @FXML
    ChoiceBox sponsorshipChoice;
    @FXML
    TextField academicRemarkTextField;
    @FXML
    Button addButton;

    // standard table
    @FXML
    TableColumn<StudentYearlyRecord, String> studentIdCol;
    @FXML
    TableView<StudentYearlyRecord> yearlyRecordTableView;
    @FXML
    TableColumn<StudentYearlyRecord,String> academicYearCol;
    @FXML
    TableColumn<StudentYearlyRecord,String> lodgingCol;
    @FXML
    TableColumn<StudentYearlyRecord, String> standardCol;
    @FXML
    TableColumn<StudentYearlyRecord, String> sponsorshipCol;
    @FXML
    TableColumn<StudentYearlyRecord, String> dropCol;
    @FXML
    TableColumn<StudentYearlyRecord, String> baptizedCol;
    @FXML
    TableColumn<StudentYearlyRecord, String> remarkCol;


    // Guardian Info
    @FXML
    TextField guardianName;
    @FXML
    TextField guardianPhone;
    @FXML
    TextField guardianAddress;

    @FXML
    ChoiceBox guardianType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Standard> list = StudentYearlyRecordDao.getStandard();
        List<Sponsorship> sponList = StudentYearlyRecordDao.getSponsorship();
        List<AcademicYear> acadList = StudentYearlyRecordDao.getAcademicYear();

        standardMap = new HashMap<>();
        sponsorshipMap = new HashMap<>();
        academicYearsMap = new HashMap<>();

        for(Standard i : list){
            standardMap.put(String.valueOf(i.getId()), i.getName());
        }
        for(AcademicYear a : acadList){
            academicYearsMap.put(String.valueOf(a.getId()), a.getName());
        }
        for(Sponsorship s : sponList){
            sponsorshipMap.put(String.valueOf(s.getId()), s.getName());
        }

        addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                addYearlyRecord();
            }
        });
    }

    public void getStudentId(Student student) {
        // display student info
        showStudentInfo(student);
        // get guardian information from database
        Guardian guardian = GuardianDao.getGuardianInfo(student.getStudentId());
        showGuardianInfo(guardian);
        guardianName.setText(guardian.getGuardianName());

        // yearly record information
        showYearlyRecord(student.getStudentId());
        prepareDataYearlyRecord();
    }

    // method to show guardian information in guardian tab

    private void showGuardianInfo(Guardian guardian){
        String[] guardianTypeList = {"worker", "non-worker"};
        guardianType.setItems(FXCollections.observableArrayList(guardianTypeList));

        if(guardian.getGuardianName()!=null) guardianName.setText(guardian.getGuardianName());
        if(guardian.getGuardianAddress()!=null) guardianAddress.setText(guardian.getGuardianAddress());
        if(guardian.getGuardianPhone()!=null) guardianPhone.setText(guardian.getGuardianPhone());
        if(guardian.getGuardianType()!=null) {
            guardianType.setValue(guardian.getGuardianType());
            guardianType.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    guardianType.setValue(guardianTypeList[t1.intValue()]);
                }
            });
        }

    }

    // method to show student information in student tab
    private void showStudentInfo(Student student){
        String[] genderArray = {"male", "female"};
        genderChoice.setItems(FXCollections.observableArrayList(genderArray));
        String[] religionArray = {"Christian", "Buddhist", "Non-SDA", "SDA Baptized", "SDA non-baptized", "Muslim", "Hindu" };
        religionChoice.setItems(FXCollections.observableArrayList(religionArray));
        if(student.getGender()!=null) {
            genderChoice.setValue(student.getGender());
        }

        if(student.getReligion()!=null) {
            System.out.print(student.getReligion());
            religionChoice.setValue(student.getReligion());
        }
        genderChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                genderChoice.setValue(genderArray[t1.intValue()]);
            }
        });

        religionChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                religionChoice.setValue(religionArray[t1.intValue()]);
            }
        });

        if(student.getStudentId()!=null) studentIdTextField.setText(student.getStudentId());
        if(student.getRollNumber()!=null) rollNumberTextField.setText(student.getRollNumber());
        if(student.getStudentName()!=null) studentNameTextField.setText(student.getStudentName());
        if(student.getFatherName()!=null) fatherNameTextField.setText(student.getFatherName());
        if(student.getNrc()!=null) nrcTextField.setText(student.getNrc());
        if(student.getAddress()!=null) addressTextField.setText(student.getAddress());
        if(student.getRemark()!=null) remarkTextField.setText(student.getRemark());
        if(student.getDob()!=null) dobDatePicker.setValue(HelperClass.convertStringToLocalDate(student.getDob()));

    }

    // mehtod to show yearlyRecord in standard tab
    private void showYearlyRecord(String studentId){
         ObservableList<StudentYearlyRecord> result =  StudentYearlyRecordDao.getYearlyRecord(studentId);
         ObservableList<StudentYearlyRecord> newResult = FXCollections.observableArrayList();
        for (StudentYearlyRecord record : result) {
            String studentId1 = record.getStudentId();
            String standard = standardMap.get(String.valueOf(record.getStandardId()));
            String lodging = record.getLodging();
            String drop = record.getIsDrop() == "1" ? "Yes" : "-";
            String isBaptizedThisYear = record.getIsBaptizedYear() == "1" ? "Yes" : "-";
            String sponsorship = sponsorshipMap.get(record.getSponsorship());
            String academicYear = academicYearsMap.get(record.getAcademicYearId());
            String remark = record.getRemark()==null ? "-" : record.getRemark();
            StudentYearlyRecord newRecord = new StudentYearlyRecord(
                    record.getId(),
                    studentId1,
                    standard,
                    academicYear,
                    lodging,
                    drop,
                    sponsorship,
                    isBaptizedThisYear,
                    remark
            );
            newResult.add(newRecord);
        }

         yearlyRecordTableView.setItems(newResult);
         studentIdCol.setCellValueFactory(new PropertyValueFactory<>("studentId"));
         academicYearCol.setCellValueFactory(new PropertyValueFactory<>("academicYearId"));
         lodgingCol.setCellValueFactory(new PropertyValueFactory<>("lodging"));
         standardCol.setCellValueFactory(new PropertyValueFactory<>("standardId"));
         dropCol.setCellValueFactory(new PropertyValueFactory<>("isDrop"));
         sponsorshipCol.setCellValueFactory(new PropertyValueFactory<>("sponsorship"));
         remarkCol.setCellValueFactory(new PropertyValueFactory<>("remark"));
         baptizedCol.setCellValueFactory(new PropertyValueFactory<>("isBaptizedYear"));

    }

    private void prepareDataYearlyRecord(){
        List<Standard> standardlist = StudentYearlyRecordDao.getStandard();
        standardChoice.setItems(FXCollections.observableArrayList(standardlist));
        List<AcademicYear> academicYearList = StudentYearlyRecordDao.getAcademicYear();
        academicYearChoice.setItems(FXCollections.observableArrayList(academicYearList));
        List<Sponsorship> sponsorshipsList = StudentYearlyRecordDao.getSponsorship();
        sponsorshipChoice.setItems(FXCollections.observableArrayList(sponsorshipsList));
        String[] lodgingList = {"boarding", "day"};
        lodgingChoice.setItems(FXCollections.observableArrayList(lodgingList));
    }

    public void addYearlyRecord(String studentId){
        Standard standardValue =  (Standard)standardChoice.getValue();
        if(standardValue!=null){
            System.out.print(standardValue);
        }else{
            System.out.print("null");
        }

    }

}
