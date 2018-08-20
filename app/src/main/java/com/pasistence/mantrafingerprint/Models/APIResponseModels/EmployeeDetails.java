package com.pasistence.mantrafingerprint.Models.APIResponseModels;

import java.io.Serializable;

public class EmployeeDetails implements Serializable {
    public int id;
    public String name;
    public String email ;
    public String adharcard_id ;
    public String gender ;
    public String dob ;
    public String permanent_address_id ;
    public String current_address_id;
    public String contact1 ;
    public String contact2;
    public String salary ;
    public String password ;
    public String created_at ;
    public String updated_at ;

    public EmployeeDetails(int id, String name, String email, String adharcard_id, String gender, String dob, String permanent_address_id, String current_address_id, String contact1, String contact2, String salary, String password, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.adharcard_id = adharcard_id;
        this.gender = gender;
        this.dob = dob;
        this.permanent_address_id = permanent_address_id;
        this.current_address_id = current_address_id;
        this.contact1 = contact1;
        this.contact2 = contact2;
        this.salary = salary;
        this.password = password;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "EmployeeDetails{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", adharcard_id='" + adharcard_id + '\'' +
                ", gender='" + gender + '\'' +
                ", dob='" + dob + '\'' +
                ", permanent_address_id='" + permanent_address_id + '\'' +
                ", current_address_id='" + current_address_id + '\'' +
                ", contact1='" + contact1 + '\'' +
                ", contact2='" + contact2 + '\'' +
                ", salary='" + salary + '\'' +
                ", password='" + password + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdharcard_id() {
        return adharcard_id;
    }

    public void setAdharcard_id(String adharcard_id) {
        this.adharcard_id = adharcard_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPermanent_address_id() {
        return permanent_address_id;
    }

    public void setPermanent_address_id(String permanent_address_id) {
        this.permanent_address_id = permanent_address_id;
    }

    public String getCurrent_address_id() {
        return current_address_id;
    }

    public void setCurrent_address_id(String current_address_id) {
        this.current_address_id = current_address_id;
    }

    public String getContact1() {
        return contact1;
    }

    public void setContact1(String contact1) {
        this.contact1 = contact1;
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
