package com.hl7.in_mysql.enuitity;

import com.hl7.in_mysql.util.Json;

public class Family {

    public String family_id;
    public String patient_id;
    public String family_name;
    public String sex;
    public String family_phone;
    public String family_relation;
    public String family_address;
    public String last_update_time;
    public String remark;


    public String getFamily_id() {
        return family_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public String getFamily_name() {
        return family_name;
    }

    public String getSex() {
        return sex;
    }

    public String getFamily_phone() {
        return family_phone;
    }

    public String getFamily_relation() {
        return family_relation;
    }

    public String getFamily_address() {
        return family_address;
    }

    public String getLast_update_time() {
        return last_update_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setFamily_id(String family_id) {
        this.family_id = family_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setFamily_address(String family_address) {
        this.family_address = family_address;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public void setFamily_phone(String family_phone) {
        this.family_phone = family_phone;
    }

    public void setFamily_relation(String family_relation) {
        this.family_relation = family_relation;
    }

    public void setLast_update_time(String last_update_time) {
        this.last_update_time = last_update_time;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return Json.getJson(this);
    }
}
