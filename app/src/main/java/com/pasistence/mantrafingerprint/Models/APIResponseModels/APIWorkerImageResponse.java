package com.pasistence.mantrafingerprint.Models.APIResponseModels;

import com.pasistence.mantrafingerprint.Models.WorkerModel;

import java.io.Serializable;

public class APIWorkerImageResponse implements Serializable {
    public boolean error;
    public int success ;
    public String error_msg;
    public String ImageURL ;
    public String ImageName ;

    public APIWorkerImageResponse() {
    }

    @Override
    public String toString() {
        return "APIWorkerImageResponse{" +
                "error=" + error +
                ", success=" + success +
                ", error_msg='" + error_msg + '\'' +
                ", ImageURL='" + ImageURL + '\'' +
                ", ImageName='" + ImageName + '\'' +
                '}';
    }

    public APIWorkerImageResponse(boolean error, int success, String error_msg, String imageURL, String imageName) {
        this.error = error;
        this.success = success;
        this.error_msg = error_msg;
        ImageURL = imageURL;
        ImageName = imageName;
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

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }
}
