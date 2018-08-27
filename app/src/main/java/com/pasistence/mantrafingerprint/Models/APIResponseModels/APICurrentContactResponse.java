package com.pasistence.mantrafingerprint.Models.APIResponseModels;

import java.io.Serializable;

public class APICurrentContactResponse implements Serializable {
    public boolean error ;
    public int success;
    public String error_msg;
    public CurrentContactdetails currentContactdetails;

    public APICurrentContactResponse() {
    }

    public APICurrentContactResponse(boolean error, int success, String error_msg, CurrentContactdetails currentContactdetails) {
        this.error = error;
        this.success = success;
        this.error_msg = error_msg;
        this.currentContactdetails = currentContactdetails;
    }

    @Override
    public String toString() {
        return "APICurrentContactResponse{" +
                "error=" + error +
                ", success=" + success +
                ", error_msg='" + error_msg + '\'' +
                ", currentContactdetails=" + currentContactdetails +
                '}';
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

    public CurrentContactdetails getCurrentContactdetails() {
        return currentContactdetails;
    }

    public void setCurrentContactdetails(CurrentContactdetails currentContactdetails) {
        this.currentContactdetails = currentContactdetails;
    }
}
