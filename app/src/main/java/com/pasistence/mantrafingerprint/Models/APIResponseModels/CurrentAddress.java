package com.pasistence.mantrafingerprint.Models.APIResponseModels;

import java.io.Serializable;

public class CurrentAddress implements Serializable {
    public int id;
    public String address_line_1 ;
    public String address_line_2 ;
    public String contact1,contact2;
    public String city ;
    public String pincode ;
    public String state ;
    public String country ;
    public String created_at ;
    public String updated_at ;
    public int worker_id ;
    public String type;

    public CurrentAddress(int id, String address_line_1, String address_line_2, String contact1, String contact2, String city, String pincode, String state, String country, String created_at, String updated_at, int worker_id, String type) {
        this.id = id;
        this.address_line_1 = address_line_1;
        this.address_line_2 = address_line_2;
        this.contact1 = contact1;
        this.contact2 = contact2;
        this.city = city;
        this.pincode = pincode;
        this.state = state;
        this.country = country;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.worker_id = worker_id;
        this.type = type;
    }

    public CurrentAddress() {
    }

    @Override
    public String toString() {
        return "CurrentAddress{" +
                "id=" + id +
                ", address_line_1='" + address_line_1 + '\'' +
                ", address_line_2='" + address_line_2 + '\'' +
                ", contact1='" + contact1 + '\'' +
                ", contact2='" + contact2 + '\'' +
                ", city='" + city + '\'' +
                ", pincode='" + pincode + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", worker_id=" + worker_id +
                ", type='" + type + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress_line_1() {
        return address_line_1;
    }

    public void setAddress_line_1(String address_line_1) {
        this.address_line_1 = address_line_1;
    }

    public String getAddress_line_2() {
        return address_line_2;
    }

    public void setAddress_line_2(String address_line_2) {
        this.address_line_2 = address_line_2;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public int getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(int worker_id) {
        this.worker_id = worker_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
