package com.hl7.in_mysql.enuitity;

import com.hl7.in_mysql.util.Json;

public class Patient {
    public String patient_id;
    public String patient_name;
    public String birthday;
    public String sex;
    public String id_card_number;
    public String patient_address;
    public String patient_work_phone;
    public String patient_home_phone;
    public String patient_marry;
    public String nation;
    public String country;
    public String religion;
    public String remark;
    public int authority;

    public String getPatient_id() {
        return patient_id;
    }

    public String getRemark() {
        return remark;
    }

    public String getSex() {
        return sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getId_card_number() {
        return id_card_number;
    }

    public String getCountry() {
        return country;
    }

    public String getNation() {
        return nation;
    }

    public String getPatient_address() {
        return patient_address;
    }

    public String getPatient_home_phone() {
        return patient_home_phone;
    }

    public String getPatient_marry() {
        return patient_marry;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public String getPatient_work_phone() {
        return patient_work_phone;
    }

    public int getAuthority() {
        return authority;
    }

    public String getReligion() {
        return religion;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setId_card_number(String id_card_number) {
        this.id_card_number = id_card_number;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public void setPatient_address(String patient_address) {
        this.patient_address = patient_address;
    }

    public void setPatient_home_phone(String patient_home_phone) {
        this.patient_home_phone = patient_home_phone;
    }

    public void setPatient_marry(String patient_marry) {
        this.patient_marry = patient_marry;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setPatient_work_phone(String patient_work_phone) {
        this.patient_work_phone = patient_work_phone;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return Json.getJson(this);
    }
}
