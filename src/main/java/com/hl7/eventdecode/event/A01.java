package com.hl7.eventdecode.event;

import ca.uhn.hl7v2.util.Terser;
import com.hl7.eventdecode.segment.*;
import com.hl7.in_mysql.enuitity.*;
import com.hl7.in_mysql.util.FormatTime;
import com.hl7.in_mysql.util.Json;

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
        result += Json.getJson(patient);

        //写入数据库 暂无
        Family family;
        try{
            terser.get("NK1-1");
            family = new NK1(this.terser).getFamily();
            family.patient_id = patient.patient_id;
            String time = FormatTime.formatTime(new Date());
            family.family_id = patient.patient_id.substring(0, (20 - time.length()) >
                patient.patient_id.length()? patient.patient_id.length() : 20 - time.length() )+ time;

            result += Json.getJson(family);
            //
        }catch (Exception e){
            for(int i = 0; i < 10; i++) {
                try {
                    this.terser.get("/NK1(" + i + ")-1");
                    family = new NK1(this.terser).getFamily(i);
                    //
                    family.patient_id = patient.patient_id;
                    String time = FormatTime.formatTime(new Date());
                    family.family_id = patient.patient_id.substring(0, (20 - time.length()) >
                        patient.patient_id.length()? patient.patient_id.length() : 20 - time.length() )+ time;
                    result += Json.getJson(family);

                } catch (Exception ex) {
                    break;
                }
            }
        }

        DoctorAdvice doctorAdvice = null;
        try{
            this.terser.get("NTE-1");
            doctorAdvice = new NTE(this.terser).getDoctorAdvice(new DoctorAdvice());
            if(doctorAdvice != null){
                doctorAdvice.doctor_advice_id = new Date().toString() + FormatTime.formatTime(new Date());
                result += Json.getJson(doctorAdvice);
                //
            }
        }catch (Exception e){

        }

        PatientHospital patientHospital = new PV1(this.terser).getPatientHospital();
        patientHospital.patient_id = patient.patient_id;
        String time = FormatTime.formatTime(new Date());
        patientHospital.admission_id = patient.patient_id.substring(0, (30 - time.length()) >
            patient.patient_id.length() ? patient.patient_id.length() : 30 - time.length() )+ time;
        if(doctorAdvice != null)
            patientHospital.doctor_advice_id = doctorAdvice.doctor_advice_id;
        result += Json.getJson(patientHospital);
        //

        Disability disability;
        try{
            terser.get("DB1-1");
            disability = new DB1(this.terser).getDisability();
            disability.patient_id  = patient.patient_id;
            String time1  = FormatTime.formatTime(new Date());
            disability.disability_id = patient.patient_id.substring(0, (20 - time1.length()) >
                patient.patient_id.length()? patient.patient_id.length() : 20 - time1.length() )+ time1;
            result += Json.getJson(disability);
            //
        }catch (Exception e){
            for(int i = 0; i < 10; i++) {
                try {
                    this.terser.get("/DB1(" + i + ")-1");
                    disability = new DB1(this.terser).getDisability(i);
                    disability.patient_id  = patient.patient_id;
                    String time1  = FormatTime.formatTime(new Date());
                    disability.disability_id = patient.patient_id.substring(0, (20 - time1.length()) >
                        patient.patient_id.length()? patient.patient_id.length() : 20 - time1.length() )+ time1;
                    result += Json.getJson(disability);
                    //
                } catch (Exception ex) {
                    break;
                }
            }
        }

        Allergy allergy;
        try{
            this.terser.get("AL1-1");
            allergy = new AL1(this.terser).getAllergy();
            allergy.patient_id = patient.patient_id;
            String time1  = FormatTime.formatTime(new Date());
            allergy.allergy_id = patient.patient_id.substring(0, (20 - time1.length()) >
                patient.patient_id.length()? patient.patient_id.length() : 20 - time1.length() )+ time1;
            result += Json.getJson(allergy);
        }catch (Exception e){

        }

        return result;
    }
}