package com.pasistence.mantrafingerprint.Models;

public class WorkerList {
    public  int id;
    public String firstName,empId, contactNo;

    public WorkerList(int id, String firstName, String empId, String contactNo) {
        this.id = id;
        this.firstName = firstName;
        this.empId = empId;
        this.contactNo = contactNo;
    }

    public WorkerList() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}
