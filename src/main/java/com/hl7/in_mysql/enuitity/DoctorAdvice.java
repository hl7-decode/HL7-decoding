package com.hl7.in_mysql.enuitity;

import com.hl7.in_mysql.util.Json;

public class DoctorAdvice {
    public String doctor_advice_id;
    public String patient_status;
    public String doctor_advice;
    public String advice_time;
    public String doctor_name;
    public String remark;

    public String getDoctor_advice_id(){
        return doctor_advice_id;
    }
    public String getPatient_status(){
        return patient_status;
    }

    public String getDoctor_advice() {
        return doctor_advice;
    }

    public String getAdvice_time() {
        return advice_time;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setDoctor_advice_id(String doctor_advice_id) {
        this.doctor_advice_id = doctor_advice_id;
    }

    public void setPatient_status(String patient_status) {
        this.patient_status = patient_status;
    }

    public void setDoctor_advice(String doctor_advice) {
        this.doctor_advice = doctor_advice;
    }

    public void setAdvice_time(String advice_time) {
        this.advice_time = advice_time;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return Json.getJson(this);
    }
}
