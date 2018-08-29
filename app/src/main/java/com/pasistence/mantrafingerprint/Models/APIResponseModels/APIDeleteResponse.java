package com.pasistence.mantrafingerprint.Models.APIResponseModels;

public class APIDeleteResponse {
    public boolean error ;
    public int success ;
    public String message ;
    public String error_msg;

    public APIDeleteResponse(boolean error, int success, String message, String error_msg) {
        this.error = error;
        this.success = success;
        this.message = message;
        this.error_msg = error_msg;
    }

    public APIDeleteResponse() {
    }

    @Override
    public String toString() {
        return "APIDeleteResponse{" +
                "error=" + error +
                ", success=" + success +
                ", message='" + message + '\'' +
                ", error_msg='" + error_msg + '\'' +
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }
}
