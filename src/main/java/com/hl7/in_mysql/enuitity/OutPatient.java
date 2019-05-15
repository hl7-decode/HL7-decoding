package com.hl7.in_mysql.enuitity;

import com.hl7.in_mysql.util.Json;

public class OutPatient {
    public String diagnosis_id;
    public String patient_id;
    public String doctor_advice_id;
    public String patient_read_me;
    public String doctor_diagnosis;
    public String doctor_name;
    public String hospital_name;
    public String remark;

    public String getRemark() {
        return remark;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public String getDiagnosis_id() {
        return diagnosis_id;
    }

    public String getDoctor_advice_id() {
        return doctor_advice_id;
    }

    public String getDoctor_diagnosis() {
        return doctor_diagnosis;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public String getPatient_read_me() {
        return patient_read_me;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public void setDoctor_advice_id(String doctor_advice_id) {
        this.doctor_advice_id = doctor_advice_id;
    }

    public void setDiagnosis_id(String diagnosis_id) {
        this.diagnosis_id = diagnosis_id;
    }

    public void setDoctor_diagnosis(String doctor_diagnosis) {
        this.doctor_diagnosis = doctor_diagnosis;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public void setPatient_read_me(String patient_read_me) {
        this.patient_read_me = patient_read_me;
    }

    @Override
    public String toString() {
        return Json.getJson(this);
    }
}
