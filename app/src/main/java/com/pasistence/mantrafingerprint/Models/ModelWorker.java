package com.pasistence.mantrafingerprint.Models;

import java.util.Arrays;

public class ModelWorker {

    private int id;
    private String name;
    private String lastname;
    private String aadharnumber;
    private String contactnum;
    private String alternatenum;
    private String age;
    private String doj;
    private String marritalstatus;
    private String gender;
    private String phone;
    private byte[] image;

    public ModelWorker(int id, String name,  String lastname, String aadharnumber,String contactnum,String alternatenum, String age, String doj,String marritalstatus, String gender, String phone, byte[] image) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.aadharnumber = aadharnumber;
        this.contactnum = contactnum;
        this.alternatenum = alternatenum;
        this.age = age;
        this.doj = doj;
        this.marritalstatus = marritalstatus;
        this.gender = gender;
        this.phone = phone;
        this.image = image;
    }



    @Override
    public String toString() {
        return "ModelWorker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", aadharnumber='" + aadharnumber + '\'' +
                ", contactnum='" + contactnum + '\'' +
                ", alternatenum='" + alternatenum + '\'' +
                ", age='" + age + '\'' +
                ", doj='" + doj + '\'' +
                ", marritalstatus='" + marritalstatus + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public String getAadharnumber() {
        return aadharnumber;
    }

    public void setAadharnumber(String aadharnumber) {
        this.aadharnumber = aadharnumber;
    }

    public String getContactnum() {
        return contactnum;
    }

    public void setContactnum(String contactnum) {
        this.contactnum = contactnum;
    }

    public String getAlternatenum() {
        return alternatenum;
    }

    public void setAlternatenum(String alternatenum) {
        this.alternatenum = alternatenum;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getMarritalstatus() {
        return marritalstatus;
    }

    public void setMarritalstatus(String marritalstatus) {
        this.marritalstatus = marritalstatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}


