package com.pasistence.mantrafingerprint.Models;

import java.io.Serializable;

public class AddressModel implements Serializable {
    String id, address_line1,
            address_line2,
            city,
            pincode,
            state,
            country,
            created_at,
            updated_at;

    public AddressModel(String id, String address_line1, String address_line2, String city, String pincode, String state, String country, String created_at, String updated_at) {
        this.id = id;
        this.address_line1 = address_line1;
        this.address_line2 = address_line2;
        this.city = city;
        this.pincode = pincode;
        this.state = state;
        this.country = country;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public AddressModel() {
    }

    @Override
    public String toString() {
        return "AddressModel{" +
                "id='" + id + '\'' +
                ", address_line1='" + address_line1 + '\'' +
                ", address_line2='" + address_line2 + '\'' +
                ", city='" + city + '\'' +
                ", pincode='" + pincode + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress_line1() {
        return address_line1;
    }

    public void setAddress_line1(String address_line1) {
        this.address_line1 = address_line1;
    }

    public String getAddress_line2() {
        return address_line2;
    }

    public void setAddress_line2(String address_line2) {
        this.address_line2 = address_line2;
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
}
