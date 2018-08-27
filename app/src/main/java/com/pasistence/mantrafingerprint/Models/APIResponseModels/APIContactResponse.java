package com.pasistence.mantrafingerprint.Models.APIResponseModels;

import java.io.Serializable;

public class APIContactResponse implements Serializable {
    public boolean error ;
    public int success;
    public String error_msg;
    public Contactdetails contactdetails;

    public APIContactResponse() {
    }

    @Override
    public String toString() {
        return "APIContactResponse{" +
                "error=" + error +
                ", success=" + success +
                ", error_msg='" + error_msg + '\'' +
                ", contactdetails=" + contactdetails +
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

    public Contactdetails getContactdetails() {
        return contactdetails;
    }

    public void setContactdetails(Contactdetails contactdetails) {
        this.contactdetails = contactdetails;
    }
}
