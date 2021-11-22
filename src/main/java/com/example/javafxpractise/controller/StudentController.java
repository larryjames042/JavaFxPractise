package com.example.javafxpractise.controller;

import com.example.javafxpractise.MainApp;
import com.example.javafxpractise.dao.StudentDao;
import com.example.javafxpractise.model.Student;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable {


    public TableColumn<Student, String> studentIdColumn;
    public TableColumn<Student, String> rollNumberColumn;
    public TableColumn<Student, String> studentNameColumn;
    public TableColumn<Student, String> fatherNameColumn;
    public TableColumn<Student, String> nrcColumn;
    public TableColumn<Student, String> dobColumn;
    public TableColumn<Student, String> religionColumn;
    public TableColumn<Student, String> genderColumn;
    public TableColumn<Student, String> addressColumn;
    public TableColumn<Student, String> studentPhotoUrlColumn;
    public TableColumn<Student, String> remarkColumn;

    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TextField searchTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showStudents();
    }

    public void showStudents(){
        // get Student data from database
        ObservableList<Student> students = StudentDao.getStudents();
        // add data to table
        studentTable.setItems(students);

//        // Automatic row number
        TableColumn numberCol = new TableColumn<>("#");
        numberCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student,String>, ObservableValue>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student,String> p) {
                return new ReadOnlyObjectWrapper<>(studentTable.getItems().indexOf(p.getValue())+ 1 + "") {
                };
            }
        });
        numberCol.setSortable(false);
        studentTable.getColumns().add(numberCol);

        // set Column value
        studentIdColumn = new TableColumn<>("Student Id");
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentTable.getColumns().add(studentIdColumn);

        rollNumberColumn = new TableColumn<>("Roll Number");
        rollNumberColumn.setCellValueFactory(new PropertyValueFactory<>("rollNumber"));
        studentTable.getColumns().add(rollNumberColumn);

        studentNameColumn = new TableColumn<>("Name");
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        studentTable.getColumns().add(studentNameColumn);

        fatherNameColumn = new TableColumn<>("Father Name");
        fatherNameColumn.setCellValueFactory(new PropertyValueFactory<>("fatherName"));
        studentTable.getColumns().add(fatherNameColumn);

        nrcColumn = new TableColumn<>("NRC");
        nrcColumn.setCellValueFactory(new PropertyValueFactory<>("nrc"));
        studentTable.getColumns().add(nrcColumn);

        dobColumn = new TableColumn<>("Date of Birth");
        dobColumn.setCellValueFactory(new PropertyValueFactory<>("dob"));
        studentTable.getColumns().add(dobColumn);

        religionColumn = new TableColumn<>("Religion");
        religionColumn.setCellValueFactory(new PropertyValueFactory<>("religion"));
        studentTable.getColumns().add(religionColumn);

        genderColumn = new TableColumn<>("Gender");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        studentTable.getColumns().add(genderColumn);

        addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        studentTable.getColumns().add(addressColumn);

        studentPhotoUrlColumn = new TableColumn<>("Photo URL");
        studentPhotoUrlColumn.setCellValueFactory(new PropertyValueFactory<>("studentPhotoUrl"));
        studentTable.getColumns().add(studentPhotoUrlColumn);

        remarkColumn = new TableColumn<>("Remark");
        remarkColumn.setCellValueFactory(new PropertyValueFactory<>("remark"));
        studentTable.getColumns().add(remarkColumn);


        FilteredList<Student> filteredData = new FilteredList<>(students, b-> true);
        // Search View
        searchTextField.textProperty().addListener((observable, oldValue, newValue) ->{
            filteredData.setPredicate(studentSearchModel -> {
                if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();

                if(studentSearchModel.getStudentId().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }else if(studentSearchModel.getRollNumber().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }else if(studentSearchModel.getStudentName().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }else{
                    return false;
                }
            });
        } );

        // bind sorted result with Table View
        SortedList<Student> sortedStudent = new SortedList<>(filteredData);
        sortedStudent.comparatorProperty().bind(studentTable.comparatorProperty());
        studentTable.setItems(sortedStudent);

        // onclick
        studentTable.setRowFactory(tv -> {
            TableRow<Student>  row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(! row.isEmpty() && event.getButton()== MouseButton.PRIMARY &&  event.getClickCount() == 2) {
                    Student student = row.getItem();
                    System.out.print(student.toString());
                    openStudentDetail(student);
                }
            });
            return row;
        });
    }

    private void openStudentDetail(Student student){
        try {
            Stage studentDetailWindow = new Stage();
            studentDetailWindow.setTitle("Edit Student information");
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/StudentDetailScene.fxml"));
            Parent root = loader.load();
            StudentDetailController studentDetailController = loader.getController();
            studentDetailController.getStudentId(student);
            studentDetailWindow.setScene(new Scene(root));
            studentDetailWindow.initModality(Modality.APPLICATION_MODAL);
            studentDetailWindow.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
