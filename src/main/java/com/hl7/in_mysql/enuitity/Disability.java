package com.hl7.in_mysql.enuitity;

import com.hl7.in_mysql.util.Json;

public class Disability {

    public String disability_id;
    public String patient_id;
    public String disability_message;
    public String disability_start_time;
    public String disability_end_time;
    public String remark;

    public String getDisability_id(){
        return disability_id;
    }

    public String getPatient_id(){
        return patient_id;
    }

    public String getDisability_message(){
        return disability_message;
    }

    public String getDisability_start_time(){
        return disability_start_time;
    }

    public String getDisability_end_time() {
        return disability_end_time;
    }

    public String getRemark(){
        return remark;
    }

    public void setDisability_id(String disability_id){
        this.disability_id = disability_id;
    }

    public void setPatient_id(String patient_id){
        this.patient_id = patient_id;
    }

    public void setDisability_message(String disability_message){
        this.disability_message = disability_message;
    }

    public void setDisability_start_time(String disability_start_time){
        this.disability_start_time = disability_start_time;
    }

    public void setDisability_end_time(String disability_end_time){
        this.disability_end_time = disability_end_time;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }

    @Override
    public String toString(){
        return Json.getJson(this);
    }

}
