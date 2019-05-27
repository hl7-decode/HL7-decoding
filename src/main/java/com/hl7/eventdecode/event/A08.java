package com.hl7.eventdecode.event;

import com.hl7.eventdecode.deal.*;
import com.hl7.eventdecode.deal.exception.HashNullPointException;
import com.hl7.eventdecode.segment.*;
import com.hl7.in_mysql.enuitity.*;
import com.hl7.in_mysql.manager.*;
import com.hl7.in_mysql.util.FormatTime;
import com.hl7.in_mysql.util.Json;

import java.util.Date;

/**
 * A08 更新病人信息
 * MSH
 * PID
 * NK1
 * PV1
 * DB1
 * AL1
 * DG1
 *
 *
 */


public class A08 {

    private Terser terser;

    public A08(Terser terser){
        this.terser = terser;
    }

    public String getMessage(){

        String result = "";

        Patient patient = new PID(this.terser).getPatient();
        //获取患者信息并
        result += "," + Json.getJson(patient);

        PatientManager.update(patient);

        //写入数据库 暂无
        Family family;
        try{
            terser.get("NK1-1");
            family = new NK1(this.terser).getFamily();
            family.patient_id = patient.patient_id;
            family.family_id = patient.patient_id + family.family_name;
            FamilyManager.update(family);
            result += "," + Json.getJson(family);
            //
        }catch (HashNullPointException e){
            for(int i = 0; i < 10; i++) {
                try {
                    this.terser.get("NK1(" + i + ")-1");
                    family = new NK1(this.terser).getFamily(i);
                    //
                    family.patient_id = patient.patient_id;
                    family.family_id = patient.patient_id + family.family_name;
                    FamilyManager.update(family);
                    result += "," + Json.getJson(family);

                } catch (HashNullPointException ex) {
                    break;
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Disability disability;
        try{
            terser.get("DB1-1");
            disability = new DB1(this.terser).getDisability();
            disability.patient_id  = patient.patient_id;
            disability.disability_id = patient.patient_id + disability.disability_start_time;
            DisabilityManager.update(disability);
            result += "," + Json.getJson(disability);
            //
        }catch (HashNullPointException e){
            for(int i = 0; i < 10; i++) {
                try {
                    this.terser.get("DB1(" + i + ")-1");
                    disability = new DB1(this.terser).getDisability(i);
                    disability.patient_id  = patient.patient_id;
                    disability.disability_id = patient.patient_id + disability.disability_start_time;

                    DisabilityManager.update(disability);

                    result += "," + Json.getJson(disability);
                    //
                } catch (Exception ex) {
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Allergy allergy;
        try{
            this.terser.get("AL1-1");
            allergy = new AL1(this.terser).getAllergy();
            if(allergy != null) {
                allergy.patient_id = patient.patient_id;
                allergy.allergy_id = patient.patient_id + allergy.allergy_type_code;

                AllergyManager.update(allergy);

                result += "," + Json.getJson(allergy);
            }
        }catch (HashNullPointException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
