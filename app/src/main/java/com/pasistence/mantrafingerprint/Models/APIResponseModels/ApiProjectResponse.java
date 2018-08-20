package com.pasistence.mantrafingerprint.Models.APIResponseModels;

import java.io.Serializable;
import java.util.List;

public class ApiProjectResponse implements Serializable {

    public boolean error ;
    public int success;
    public String error_msg;
    public Projectdetails projectdetails ;

    public ApiProjectResponse(boolean error, int success, String error_msg, Projectdetails projectdetails) {
        this.error = error;
        this.success = success;
        this.error_msg = error_msg;
        this.projectdetails = projectdetails;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public Projectdetails getProjectdetails() {
        return projectdetails;
    }

    public void setProjectdetails(Projectdetails projectdetails) {
        this.projectdetails = projectdetails;
    }
}
