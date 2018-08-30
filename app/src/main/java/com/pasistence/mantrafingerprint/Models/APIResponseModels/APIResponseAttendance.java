package com.pasistence.mantrafingerprint.Models.APIResponseModels;

import java.io.Serializable;

public class APIResponseAttendance implements Serializable {
    public boolean error ;
    public String error_msg ;
    public int success ;
    public Attendance Attendace ;

    public APIResponseAttendance() {
    }

    public APIResponseAttendance(boolean error, String error_msg, int success, Attendance attendace) {
        this.error = error;
        this.error_msg = error_msg;
        this.success = success;
        Attendace = attendace;
    }

    @Override
    public String toString() {
        return "APIResponseAttendance{" +
                "error=" + error +
                ", error_msg='" + error_msg + '\'' +
                ", success=" + success +
                ", Attendace=" + Attendace +
                '}';
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public Attendance getAttendace() {
        return Attendace;
    }

    public void setAttendace(Attendance attendace) {
        Attendace = attendace;
    }
}
