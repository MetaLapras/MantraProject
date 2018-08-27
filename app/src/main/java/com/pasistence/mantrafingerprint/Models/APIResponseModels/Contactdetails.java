package com.pasistence.mantrafingerprint.Models.APIResponseModels;

import java.io.Serializable;

class Contactdetails implements Serializable {
    public int id ;
    public int contact1 ;
    public int contact2 ;
    public String address_line_1 ;
    public String address_line_2 ;
    public String city;
    public String pincode ;
    public String state;
    public String country ;
    public int worker_id;
    public String type ;

    public Contactdetails(int id, int contact1, int contact2, String address_line_1, String address_line_2, String city, String pincode, String state, String country, int worker_id, String type) {
        this.id = id;
        this.contact1 = contact1;
        this.contact2 = contact2;
        this.address_line_1 = address_line_1;
        this.address_line_2 = address_line_2;
        this.city = city;
        this.pincode = pincode;
        this.state = state;
        this.country = country;
        this.worker_id = worker_id;
        this.type = type;
    }

    public Contactdetails() {
    }

    @Override
    public String toString() {
        return "Contactdetails{" +
                "id=" + id +
                ", contact1=" + contact1 +
                ", contact2=" + contact2 +
                ", address_line_1='" + address_line_1 + '\'' +
                ", address_line_2='" + address_line_2 + '\'' +
                ", city='" + city + '\'' +
                ", pincode='" + pincode + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
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

    public int getContact1() {
        return contact1;
    }

    public void setContact1(int contact1) {
        this.contact1 = contact1;
    }

    public int getContact2() {
        return contact2;
    }

    public void setContact2(int contact2) {
        this.contact2 = contact2;
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
