package com.hl7.in_mysql.enuitity;

import com.hl7.in_mysql.util.Json;

public class Allergy {

    public String allergy_id;
    public String patient_id;
    public String allergy_type_code;
    public String allergy_type_message;
    public String allergy_severity_code;
    public String allergy_reaction_code;
    public String remark;

    public String getAllergy_id(){
        return this.allergy_id;
    }

    public String getPatient_id(){
        return patient_id;
    }

    public String getAllergy_type_code(){
        return allergy_type_code;
    }

    public String getAllergy_type_message(){
        return allergy_type_message;
    }

    public String getAllergy_severity_code(){
        return allergy_severity_code;
    }

    public String getAllergy_reaction_code(){
        return allergy_reaction_code;
    }

    public String getRemark(){
        return remark;
    }

    public void setAllergy_id(String allergy_id){
        this.allergy_id = allergy_id;
    }

    public void setPatient_id(String patient_id){
        this.patient_id = patient_id;
    }

    public void setAllergy_type_code(String allergy_type_code){
        this.allergy_type_code = allergy_type_code;
    }

    public void setAllergy_type_message(String allergy_type_message){
        this.allergy_type_message = allergy_type_message;
    }

    public void setAllergy_severity_code(String allergy_severity_code){
        this.allergy_severity_code = allergy_severity_code;
    }

    public void setAllergy_reaction_code(String allergy_reaction_code){
        this.allergy_reaction_code = allergy_reaction_code;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }

    @Override
    public String toString(){
        return Json.getJson(this);
    }

}
