package com.android.acadgild.employeedetails113.model;

import java.sql.Blob;

/**
 * Created by Jal on 28-07-2017.
 */

public class EmployeeData {

    String empName;
    String empId;
    String empAge;
    byte[] empPhotoImagae;

    String id;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpAge() {
        return empAge;
    }

    public void setEmpAge(String empAge) {
        this.empAge = empAge;
    }

    public byte[] getEmpPhotoImage() {
        return empPhotoImagae;
    }

    public void setEmpPhotoImage(byte[] empPhotoImagae) {
        this.empPhotoImagae = empPhotoImagae;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
