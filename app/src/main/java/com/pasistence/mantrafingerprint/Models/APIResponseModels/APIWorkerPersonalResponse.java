package com.pasistence.mantrafingerprint.Models.APIResponseModels;

import com.pasistence.mantrafingerprint.Models.WorkerModel;

import java.io.Serializable;
import java.util.List;

public class APIWorkerPersonalResponse implements Serializable{

        public String error_msg ;
    public boolean error ;
    public int success ;
    public WorkerModel WorkerModel;

    public APIWorkerPersonalResponse(String error_msg, boolean error, int success, com.pasistence.mantrafingerprint.Models.WorkerModel workerModel) {
        this.error_msg = error_msg;
        this.error = error;
        this.success = success;
        WorkerModel = workerModel;
    }

    @Override
    public String toString() {
        return "APIWorkerPersonalResponse{" +
                "error_msg='" + error_msg + '\'' +
                ", error=" + error +
                ", success=" + success +
                ", WorkerModel=" + WorkerModel +
                '}';
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
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

    public com.pasistence.mantrafingerprint.Models.WorkerModel getWorkerModel() {
        return WorkerModel;
    }

    public void setWorkerModel(com.pasistence.mantrafingerprint.Models.WorkerModel workerModel) {
        WorkerModel = workerModel;
    }

    public APIWorkerPersonalResponse() {

    }
}
