package com.hl7.in_mysql.enuitity;

import com.hl7.in_mysql.util.Json;

public class PatientHospital {

    public String admission_id;
    public String patient_id;
    public String doctor_advice_id;
    public String patient_location;
    public String attending_doctor;
    public String referring_doctor;
    public String consult_doctor;
    public String admit_source;
    public String admitting_doctor;
    public String discharged_to_location;
    public String bed_status;
    public String admit_time;
    public String out_time;
    public String remark;
    public String hospital;

    public String getPatient_id() {
        return patient_id;
    }

    public String getRemark() {
        return remark;
    }

    public String getDoctor_advice_id() {
        return doctor_advice_id;
    }

    public String getAdmission_id() {
        return admission_id;
    }

    public String getAdmit_source() {
        return admit_source;
    }

    public String getAdmit_time() {
        return admit_time;
    }

    public String getAdmitting_doctor() {
        return admitting_doctor;
    }

    public String getAttending_doctor() {
        return attending_doctor;
    }

    public String getBed_status() {
        return bed_status;
    }

    public String getConsult_doctor() {
        return consult_doctor;
    }

    public String getDischarged_to_location() {
        return discharged_to_location;
    }

    public String getPatient_location() {
        return patient_location;
    }

    public String getReferring_doctor() {
        return referring_doctor;
    }

    public String getHospital() {
        return hospital;
    }

    public String getOut_time() {
        return out_time;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public void setDoctor_advice_id(String doctor_advice_id) {
        this.doctor_advice_id = doctor_advice_id;
    }

    public void setAdmission_id(String admission_id) {
        this.admission_id = admission_id;
    }

    public void setAdmit_source(String admit_source) {
        this.admit_source = admit_source;
    }

    public void setAttending_doctor(String attending_doctor) {
        this.attending_doctor = attending_doctor;
    }

    public void setAdmitting_doctor(String admitting_doctor) {
        this.admitting_doctor = admitting_doctor;
    }

    public void setBed_status(String bed_status) {
        this.bed_status = bed_status;
    }

    public void setConsult_doctor(String consult_doctor) {
        this.consult_doctor = consult_doctor;
    }

    public void setDischarged_to_location(String discharged_to_location) {
        this.discharged_to_location = discharged_to_location;
    }

    public void setAdmit_time(String admit_time) {
        this.admit_time = admit_time;
    }

    public void setPatient_location(String patient_location) {
        this.patient_location = patient_location;
    }

    public void setReferring_doctor(String referring_doctor) {
        this.referring_doctor = referring_doctor;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public void setOut_time(String out_time) {
        this.out_time = out_time;
    }

    @Override
    public String toString() {
        return Json.getJson(this);
    }
}
