package com.pasistence.mantrafingerprint.Models;

import com.pasistence.mantrafingerprint.Models.APIResponseModels.BankAccount;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.CurrentAddress;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.PermanentAddress;

import java.io.Serializable;

public class WorkerModel implements Serializable {
    public String id, workerId, name, adharcardId, gender, dob, fingerprint1, fingerprint2, email, permanentAddressId,
            currentAddressId, contact1, contact2, salary, createdAt, updatedAt, bankId, projectId, activation,
            imageUrl, permanent_address1, current_address1, bank_name, holder_name, ifsc_code, account_number, city,pincode;
    public PermanentAddress permanent_address;
    public CurrentAddress current_address;
    public BankAccount bank_account ;


    public WorkerModel(String id, String workerId, String name, String adharcardId, String gender, String dob, String fingerprint1, String fingerprint2, String email, String permanentAddressId, String currentAddressId, String contact1, String contact2, String salary, String createdAt, String updatedAt, String bankId, String projectId, String activation, String imageUrl, String permanent_address1, String current_address1, String bank_name, String holder_name, String ifsc_code, String account_number, String city, String pincode, PermanentAddress permanent_address, CurrentAddress current_address, BankAccount bank_account) {
        this.id = id;
        this.workerId = workerId;
        this.name = name;
        this.adharcardId = adharcardId;
        this.gender = gender;
        this.dob = dob;
        this.fingerprint1 = fingerprint1;
        this.fingerprint2 = fingerprint2;
        this.email = email;
        this.permanentAddressId = permanentAddressId;
        this.currentAddressId = currentAddressId;
        this.contact1 = contact1;
        this.contact2 = contact2;
        this.salary = salary;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.bankId = bankId;
        this.projectId = projectId;
        this.activation = activation;
        this.imageUrl = imageUrl;
        this.permanent_address1 = permanent_address1;
        this.current_address1 = current_address1;
        this.bank_name = bank_name;
        this.holder_name = holder_name;
        this.ifsc_code = ifsc_code;
        this.account_number = account_number;
        this.city = city;
        this.pincode = pincode;
        this.permanent_address = permanent_address;
        this.current_address = current_address;
        this.bank_account = bank_account;
    }

    public WorkerModel() {
    }

    @Override
    public String toString() {
        return "WorkerModel{" +
                "id='" + id + '\'' +
                ", workerId='" + workerId + '\'' +
                ", name='" + name + '\'' +
                ", adharcardId='" + adharcardId + '\'' +
                ", gender='" + gender + '\'' +
                ", dob='" + dob + '\'' +
                ", fingerprint1='" + fingerprint1 + '\'' +
                ", fingerprint2='" + fingerprint2 + '\'' +
                ", email='" + email + '\'' +
                ", permanentAddressId='" + permanentAddressId + '\'' +
                ", currentAddressId='" + currentAddressId + '\'' +
                ", contact1='" + contact1 + '\'' +
                ", contact2='" + contact2 + '\'' +
                ", salary='" + salary + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", bankId='" + bankId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", activation='" + activation + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", permanent_address1='" + permanent_address1 + '\'' +
                ", current_address1='" + current_address1 + '\'' +
                ", bank_name='" + bank_name + '\'' +
                ", holder_name='" + holder_name + '\'' +
                ", ifsc_code='" + ifsc_code + '\'' +
                ", account_number='" + account_number + '\'' +
                ", city='" + city + '\'' +
                ", pincode='" + pincode + '\'' +
                ", permanent_address=" + permanent_address +
                ", current_address=" + current_address +
                ", bank_account=" + bank_account +
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdharcardId() {
        return adharcardId;
    }

    public void setAdharcardId(String adharcardId) {
        this.adharcardId = adharcardId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getFingerprint1() {
        return fingerprint1;
    }

    public void setFingerprint1(String fingerprint1) {
        this.fingerprint1 = fingerprint1;
    }

    public String getFingerprint2() {
        return fingerprint2;
    }

    public void setFingerprint2(String fingerprint2) {
        this.fingerprint2 = fingerprint2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPermanentAddressId() {
        return permanentAddressId;
    }

    public void setPermanentAddressId(String permanentAddressId) {
        this.permanentAddressId = permanentAddressId;
    }

    public String getCurrentAddressId() {
        return currentAddressId;
    }

    public void setCurrentAddressId(String currentAddressId) {
        this.currentAddressId = currentAddressId;
    }

    public String getContact1() {
        return contact1;
    }

    public void setContact1(String contact1) {
        this.contact1 = contact1;
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPermanent_address1() {
        return permanent_address1;
    }

    public void setPermanent_address1(String permanent_address1) {
        this.permanent_address1 = permanent_address1;
    }

    public String getCurrent_address1() {
        return current_address1;
    }

    public void setCurrent_address1(String current_address1) {
        this.current_address1 = current_address1;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getHolder_name() {
        return holder_name;
    }

    public void setHolder_name(String holder_name) {
        this.holder_name = holder_name;
    }

    public String getIfsc_code() {
        return ifsc_code;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public PermanentAddress getPermanent_address() {
        return permanent_address;
    }

    public void setPermanent_address(PermanentAddress permanent_address) {
        this.permanent_address = permanent_address;
    }

    public CurrentAddress getCurrent_address() {
        return current_address;
    }

    public void setCurrent_address(CurrentAddress current_address) {
        this.current_address = current_address;
    }

    public BankAccount getBank_account() {
        return bank_account;
    }

    public void setBank_account(BankAccount bank_account) {
        this.bank_account = bank_account;
    }
}