package com.pasistence.mantrafingerprint.Models.APIResponseModels;

import com.pasistence.mantrafingerprint.Models.WorkerModel;

import java.io.Serializable;

public class APIWorkerPersonalResponse implements Serializable{

    public boolean error ;
    public int success;
    public String error_msg;
    public WorkerModel workerModel;

    public APIWorkerPersonalResponse(boolean error, int success, String error_msg, WorkerModel workerModel) {
        this.error = error;
        this.success = success;
        this.error_msg = error_msg;
        this.workerModel = workerModel;
    }

    public APIWorkerPersonalResponse() {
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

    public WorkerModel getWorkerModel() {
        return workerModel;
    }

    public void setWorkerModel(WorkerModel workerModel) {
        this.workerModel = workerModel;
    }
}
