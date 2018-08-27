package com.pasistence.mantrafingerprint.Models.APIResponseModels;

import com.pasistence.mantrafingerprint.Models.WorkerModel;

import java.io.Serializable;

public class APIWorkerImageResponse implements Serializable {
    public boolean error;
    public int success ;
    public String error_msg;
    public String ImageURL ;
    public WorkerModel workerModel;

    public APIWorkerImageResponse(boolean error, int success, String error_msg, String imageURL, WorkerModel workerModel) {
        this.error = error;
        this.success = success;
        this.error_msg = error_msg;
        ImageURL = imageURL;
        this.workerModel = workerModel;
    }

    public APIWorkerImageResponse() {
    }

    @Override
    public String toString() {
        return "APIWorkerImageResponse{" +
                "error=" + error +
                ", success=" + success +
                ", error_msg='" + error_msg + '\'' +
                ", ImageURL='" + ImageURL + '\'' +
                ", workerModel=" + workerModel +
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

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public WorkerModel getWorkerModel() {
        return workerModel;
    }

    public void setWorkerModel(WorkerModel workerModel) {
        this.workerModel = workerModel;
    }
}
