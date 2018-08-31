package com.pasistence.mantrafingerprint.Models.APIResponseModels;

import java.io.Serializable;

public class BankAccount implements Serializable {
    public int id ;
    public String account_holder_name ;
    public String ifsc_code;
    public String account_no ;
    public String bank_name ;
    public int worker_id ;
    public String activation ;

    public BankAccount(int id, String account_holder_name, String ifsc_code, String account_no, String bank_name, int worker_id, String activation) {
        this.id = id;
        this.account_holder_name = account_holder_name;
        this.ifsc_code = ifsc_code;
        this.account_no = account_no;
        this.bank_name = bank_name;
        this.worker_id = worker_id;
        this.activation = activation;
    }

    public BankAccount() {
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", account_holder_name='" + account_holder_name + '\'' +
                ", ifsc_code='" + ifsc_code + '\'' +
                ", account_no='" + account_no + '\'' +
                ", bank_name='" + bank_name + '\'' +
                ", worker_id=" + worker_id +
                ", activation='" + activation + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount_holder_name() {
        return account_holder_name;
    }

    public void setAccount_holder_name(String account_holder_name) {
        this.account_holder_name = account_holder_name;
    }

    public String getIfsc_code() {
        return ifsc_code;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public int getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(int worker_id) {
        this.worker_id = worker_id;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }
}
