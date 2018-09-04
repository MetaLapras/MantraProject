package com.pasistence.mantrafingerprint.Models.APIResponseModels;

import java.io.Serializable;

public class Attendance implements Serializable {
    public String id,workerId,workerAssignmentId,projectId,checkInDate,checkInTime,overTime,
    fullTime,halfday,checkOutTime,wages,created_at,updated_at,day,month,year;

    public Attendance(String id, String workerId, String workerAssignmentId, String projectId, String checkInDate, String checkInTime, String overTime, String fullTime, String halfday, String checkOutTime, String wages, String created_at, String updated_at, String day, String month, String year) {
        this.id = id;
        this.workerId = workerId;
        this.workerAssignmentId = workerAssignmentId;
        this.projectId = projectId;
        this.checkInDate = checkInDate;
        this.checkInTime = checkInTime;
        this.overTime = overTime;
        this.fullTime = fullTime;
        this.halfday = halfday;
        this.checkOutTime = checkOutTime;
        this.wages = wages;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Attendance() {
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id='" + id + '\'' +
                ", workerId='" + workerId + '\'' +
                ", workerAssignmentId='" + workerAssignmentId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", checkInDate='" + checkInDate + '\'' +
                ", checkInTime='" + checkInTime + '\'' +
                ", overTime='" + overTime + '\'' +
                ", fullTime='" + fullTime + '\'' +
                ", halfday='" + halfday + '\'' +
                ", checkOutTime='" + checkOutTime + '\'' +
                ", wages='" + wages + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", day='" + day + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getWorkerAssignmentId() {
        return workerAssignmentId;
    }

    public void setWorkerAssignmentId(String workerAssignmentId) {
        this.workerAssignmentId = workerAssignmentId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getOverTime() {
        return overTime;
    }

    public void setOverTime(String overTime) {
        this.overTime = overTime;
    }

    public String getFullTime() {
        return fullTime;
    }

    public void setFullTime(String fullTime) {
        this.fullTime = fullTime;
    }

    public String getHalfday() {
        return halfday;
    }

    public void setHalfday(String halfday) {
        this.halfday = halfday;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String getWages() {
        return wages;
    }

    public void setWages(String wages) {
        this.wages = wages;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
