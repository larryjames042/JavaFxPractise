package com.example.javafxpractise.model;

public class Guardian {
    int guardianId;
    int studentId;
    String guardianName;
    String guardianAddress;
    String guardianType;
    String guardianPhone;


    public Guardian(int guardianId, int studentId, String guardianName, String guardianAddress, String guardianType, String guardianPhone) {
        this.guardianId = guardianId;
        this.studentId = studentId;
        this.guardianName = guardianName;
        this.guardianAddress = guardianAddress;
        this.guardianType = guardianType;
        this.guardianPhone = guardianPhone;
    }

    public int getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(int guardianId) {
        this.guardianId = guardianId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getGuardianAddress() {
        return guardianAddress;
    }

    public void setGuardianAddress(String guardianAddress) {
        this.guardianAddress = guardianAddress;
    }


    public String getGuardianType() {
        return guardianType;
    }

    public void setGuardianType(String guardianType) {
        this.guardianType = guardianType;
    }

    public String getGuardianPhone() {
        return guardianPhone;
    }

    public void setGuardianPhone(String guardianPhone) {
        this.guardianPhone = guardianPhone;
    }

    @Override
    public String toString() {
        return "Guardian{" +
                "guardianId=" + guardianId +
                ", studentId=" + studentId +
                ", guardianName='" + guardianName + '\'' +
                ", guardianAddress='" + guardianAddress + '\'' +
                ", guardianType='" + guardianType + '\'' +
                ", guardianPhone='" + guardianPhone + '\'' +
                '}';
    }
}
