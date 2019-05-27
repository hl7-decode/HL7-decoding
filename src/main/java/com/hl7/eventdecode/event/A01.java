package com.hl7.eventdecode.event;

import com.hl7.eventdecode.deal.*;
import com.hl7.eventdecode.deal.exception.ArrayNullPointException;
import com.hl7.eventdecode.deal.exception.HashNullPointException;
import com.hl7.eventdecode.deal.exception.ProgramQuestion;
import com.hl7.eventdecode.deal.exception.WrongSearchKey;
import com.hl7.eventdecode.segment.*;
import com.hl7.in_mysql.enuitity.*;
import com.hl7.in_mysql.manager.*;
import com.hl7.in_mysql.util.FormatTime;
import com.hl7.in_mysql.util.Json;

/**
 * A01 事件   接收病人
 * 主要包括以下段
 * MSH
 * PID
 * PD1
 * NK1
 * PV1
 * DB1
 * AL1
 * DG1
 *
 * */

import java.util.Date;

public class A01 {
    public Terser terser;
    public A01(Terser terser){
        this.terser = terser;
    }

    public String GetMessage(){
        String result = "";

        Patient patient = new PID(this.terser).getPatient();
        //获取患者信息并
        result += "," + Json.getJson(patient);

        PatientManager.insert(patient);

        //写入数据库 暂无
        Family family;
        try{
            terser.get("NK1-1");
            family = new NK1(this.terser).getFamily();
            family.patient_id = patient.patient_id;
            family.family_id = patient.patient_id + family.family_name;
            FamilyManager.insert(family);
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
                    FamilyManager.insert(family);
                    result += "," + Json.getJson(family);

                } catch (Exception ex) {
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        DoctorAdvice doctorAdvice = null;
        try{
            this.terser.get("NTE-1");
            doctorAdvice = new NTE(this.terser).getDoctorAdvice(new DoctorAdvice());
            if(doctorAdvice != null){
                doctorAdvice.doctor_advice_id = doctorAdvice.advice_time + doctorAdvice.doctor_name;
                result += "," + Json.getJson(doctorAdvice);
                //
                DoctorAdviceManager.insert(doctorAdvice);
            }
        }catch (HashNullPointException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        PatientHospital patientHospital = new PV1(this.terser).getPatientHospital();
        if(patientHospital != null){
            patientHospital.patient_id = (patient.patient_id == null ? null : patient.patient_id);
            patientHospital.admission_id = patient.patient_id + patientHospital.admit_time;
            if(doctorAdvice != null)
                patientHospital.doctor_advice_id = doctorAdvice.doctor_advice_id;
            if(patientHospital.admit_time == null || patientHospital.admit_time.equals("")){

            } else {
                PatientHospitalManager.insert(patientHospital);
            }
            result += "," + Json.getJson(patientHospital);
        }
//        System.out.println(patientHospital.patient_id);
        //

        Disability disability;
        System.out.println("残疾");
        try{
            terser.get("DB1-1");
            disability = new DB1(this.terser).getDisability();
            disability.patient_id  = patient.patient_id;
            disability.disability_id = patient.patient_id + disability.disability_start_time;
            DisabilityManager.insert(disability);

            System.out.println("残疾");
            result += "," + Json.getJson(disability);
            //
        }catch (HashNullPointException e1){
                for(int i = 0; i < 10; i++) {
                    try {
                        this.terser.get("DB1(" + i + ")-1");
                        disability = new DB1(this.terser).getDisability(i);
                        disability.patient_id  = patient.patient_id;
                        disability.disability_id = patient.patient_id + disability.disability_start_time;

                        DisabilityManager.insert(disability);

                        System.out.println("残疾");

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

                AllergyManager.insert(allergy);

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
