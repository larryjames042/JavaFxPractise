package com.example.javafxpractise.model;

public class StudentYearlyRecord {
    int id;
    String studentId;
    String standardId;
    String academicYearId;
    String lodging;
    String isDrop;
    String sponsorship;
    String isBaptizedYear;
    String remark;


    public StudentYearlyRecord(int id, String studentId, String standardId, String academicYearId, String lodging, String isDrop, String sponsorship, String isBaptizedYear, String remark) {
        this.id = id;
        this.studentId = studentId;
        this.standardId = standardId;
        this.academicYearId = academicYearId;
        this.lodging = lodging;
        this.isDrop = isDrop;
        this.sponsorship = sponsorship;
        this.isBaptizedYear = isBaptizedYear;
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStandardId() {
        return standardId;
    }

    public void setStandardId(String standardId) {
        this.standardId = standardId;
    }

    public String getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(String academicYearId) {
        this.academicYearId = academicYearId;
    }

    public String getLodging() {
        return lodging;
    }

    public void setLodging(String lodging) {
        this.lodging = lodging;
    }

    public String getIsDrop() {
        return isDrop;
    }

    public void setIsDrop(String isDrop) {
        this.isDrop = isDrop;
    }

    public String getSponsorship() {
        return sponsorship;
    }

    public void setSponsorship(String sponsorship) {
        this.sponsorship = sponsorship;
    }

    public String getIsBaptizedYear() {
        return isBaptizedYear;
    }

    public void setIsBaptizedYear(String isBaptizedYear) {
        this.isBaptizedYear = isBaptizedYear;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "StudentYearlyRecord{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", standardId=" + standardId +
                ", academicYearId=" + academicYearId +
                ", lodging='" + lodging + '\'' +
                ", isDrop=" + isDrop +
                ", sponsorship=" + sponsorship +
                ", isBaptizedYear=" + isBaptizedYear +
                ", remark='" + remark + '\'' +
                '}';
    }
}
