package com.pasistence.mantrafingerprint.Models.APIResponseModels;

import java.io.Serializable;

public class APIBankResponse implements Serializable {
    public boolean error ;
    public int success;
    public String error_msg;
    public BankAccount bankdetails ;

    public APIBankResponse(boolean error, int success, String error_msg, BankAccount bankdetails) {
        this.error = error;
        this.success = success;
        this.error_msg = error_msg;
        this.bankdetails = bankdetails;
    }

    public APIBankResponse() {
    }

    @Override
    public String toString() {
        return "APIBankResponse{" +
                "error=" + error +
                ", success=" + success +
                ", error_msg='" + error_msg + '\'' +
                ", bankdetails=" + bankdetails +
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

    public BankAccount getBankdetails() {
        return bankdetails;
    }

    public void setBankdetails(BankAccount bankdetails) {
        this.bankdetails = bankdetails;
    }
}
