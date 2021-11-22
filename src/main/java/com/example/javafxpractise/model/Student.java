package com.example.javafxpractise.model;


public class Student {
    String studentId;
    String rollNumber;
    String studentName;
    String fatherName;
    String nrc;
    String dob;
    String religion;
    String gender;
    String address;
    String studentPhotoUrl;
    String remark;


    public Student(String studentId, String rollNumber, String studentName, String fatherName, String nrc, String dob, String religion, String gender, String address, String studentPhotoUrl, String remark) {
        this.studentId = studentId;
        this.rollNumber = rollNumber;
        this.studentName = studentName;
        this.fatherName = fatherName;
        this.nrc = nrc;
        this.dob = dob;
        this.religion = religion;
        this.gender = gender;
        this.address = address;
        this.studentPhotoUrl = studentPhotoUrl;
        this.remark = remark;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStudentPhotoUrl() {
        return studentPhotoUrl;
    }

    public void setStudentPhotoUrl(String studentPhotoUrl) {
        this.studentPhotoUrl = studentPhotoUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", rollNumber='" + rollNumber + '\'' +
                ", studentName='" + studentName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", nrc='" + nrc + '\'' +
                ", dob='" + dob + '\'' +
                ", religionId=" + religion +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", studentPhotoUrl='" + studentPhotoUrl + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
