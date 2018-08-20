package com.pasistence.mantrafingerprint.Models.APIResponseModels;

import java.io.Serializable;

public class WorkerList implements Serializable {

    public int id;
    public String name;
    public String adharcard_id ;
    public String gender ;
    public String dob ;
    public String fingerprint1 ;
    public String fingerprint2;
    public String email;
    public String permanent_address_id ;
    public String current_address_id ;
    public String contact1 ;
    public String contact2 ;
    public String salary ;
    public String created_at ;
    public String updated_at ;
    public String bank_id ;
    public String project_id ;
    public String activation ;
    public String image_url;


    public WorkerList(int id, String name, String adharcard_id, String gender, String dob, String fingerprint1, String fingerprint2, String email, String permanent_address_id, String current_address_id, String contact1, String contact2, String salary, String created_at, String updated_at, String bank_id, String project_id, String activation, String image_url) {
        this.id = id;
        this.name = name;
        this.adharcard_id = adharcard_id;
        this.gender = gender;
        this.dob = dob;
        this.fingerprint1 = fingerprint1;
        this.fingerprint2 = fingerprint2;
        this.email = email;
        this.permanent_address_id = permanent_address_id;
        this.current_address_id = current_address_id;
        this.contact1 = contact1;
        this.contact2 = contact2;
        this.salary = salary;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.bank_id = bank_id;
        this.project_id = project_id;
        this.activation = activation;
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "WorkerList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adharcard_id='" + adharcard_id + '\'' +
                ", gender='" + gender + '\'' +
                ", dob='" + dob + '\'' +
                ", fingerprint1='" + fingerprint1 + '\'' +
                ", fingerprint2='" + fingerprint2 + '\'' +
                ", email='" + email + '\'' +
                ", permanent_address_id='" + permanent_address_id + '\'' +
                ", current_address_id='" + current_address_id + '\'' +
                ", contact1='" + contact1 + '\'' +
                ", contact2='" + contact2 + '\'' +
                ", salary='" + salary + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", bank_id='" + bank_id + '\'' +
                ", project_id='" + project_id + '\'' +
                ", activation='" + activation + '\'' +
                ", image_url='" + image_url + '\'' +
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

    public String getFingerprint1() {
        return fingerprint1;
    }

    public void setFingerprint1(String fingerprint1) {
        this.fingerprint1 = fingerprint1;
    }

    public String getFingerprint2() {
        return fingerprint2;
    }

    public void setFingerprint2(String fingerprint2) {
        this.fingerprint2 = fingerprint2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getBank_id() {
        return bank_id;
    }

    public void setBank_id(String bank_id) {
        this.bank_id = bank_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
