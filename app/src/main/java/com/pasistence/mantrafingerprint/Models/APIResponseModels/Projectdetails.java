package com.pasistence.mantrafingerprint.Models.APIResponseModels;

import com.pasistence.mantrafingerprint.Models.WorkerModel;

import java.io.Serializable;
import java.util.List;

public class Projectdetails implements Serializable {
    public int id ;
    public int project_id ;
    public String project_name ;
    public String location ;
    public String password;
    public String created_at ;
    public String updated_at ;
    public String activation;
    public int admin_id;
    public int employee_id ;
    public EmployeeDetails employee_details ;
    public List<WorkerModel> worker_list ;


    public Projectdetails() {
    }

    public Projectdetails(int id, int project_id, String project_name, String location, String password, String created_at, String updated_at, String activation, int admin_id, int employee_id, EmployeeDetails employee_details, List<WorkerModel> worker_list) {
        this.id = id;
        this.project_id = project_id;
        this.project_name = project_name;
        this.location = location;
        this.password = password;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.activation = activation;
        this.admin_id = admin_id;
        this.employee_id = employee_id;
        this.employee_details = employee_details;
        this.worker_list = worker_list;
    }

    @Override
    public String toString() {
        return "Projectdetails{" +
                "id=" + id +
                ", project_id=" + project_id +
                ", project_name='" + project_name + '\'' +
                ", location='" + location + '\'' +
                ", password='" + password + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", activation='" + activation + '\'' +
                ", admin_id=" + admin_id +
                ", employee_id=" + employee_id +
                ", employee_details=" + employee_details +
                ", worker_list=" + worker_list +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public EmployeeDetails getEmployee_details() {
        return employee_details;
    }

    public void setEmployee_details(EmployeeDetails employee_details) {
        this.employee_details = employee_details;
    }

    public List<WorkerModel> getWorker_list() {
        return worker_list;
    }

    public void setWorker_list(List<WorkerModel> worker_list) {
        this.worker_list = worker_list;
    }
}
