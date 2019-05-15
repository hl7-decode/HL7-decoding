package com.hl7.eventdecode.event;

import com.hl7.eventdecode.deal.*;
import com.hl7.eventdecode.segment.*;
import com.hl7.in_mysql.enuitity.*;
import com.hl7.in_mysql.util.FormatTime;
import com.hl7.in_mysql.util.Json;

import java.util.Date;

public class A07 {

    public Terser terser;

    public A07(Terser terser){
        this.terser = terser;
    }

    public String getMessage(){

        String result = "";

        Patient patient = new PID(this.terser).getPatient();
        //获取患者信息并
        //写入数据库 暂无
        result += "," + Json.getJson(patient);

        Family family;
        try{
            terser.get("NK1-1");
            family = new NK1(this.terser).getFamily();
            family.patient_id = patient.patient_id;
            String time = FormatTime.formatTime(new Date());
            family.family_id = patient.patient_id.substring(0, (20 - time.length()) >
                patient.patient_id.length()? patient.patient_id.length() : 20 - time.length() )+ time;
            //
            result += "," + Json.getJson(family);
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
                    result += "," + Json.getJson(family);
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
                doctorAdvice.doctor_advice_id = FormatTime.formatTime(new Date());
                //
                result += "," + Json.getJson(doctorAdvice);
            }
        }catch (Exception e){

        }

        PatientHospital patientHospital = new PV1(this.terser).getPatientHospital();
        if(patientHospital != null) {
            patientHospital.patient_id = patient.patient_id;
            String time = FormatTime.formatTime(new Date());
            patientHospital.admission_id = patient.patient_id.substring(0, (20 - time.length()) >
                patient.patient_id.length() ? patient.patient_id.length() : 20 - time.length()) + time;
            if (doctorAdvice != null)
                patientHospital.doctor_advice_id = doctorAdvice.doctor_advice_id;
            //
            result += "," + Json.getJson(patientHospital);
        }

        Disability disability;
        try{
            terser.get("DB1-1");
            disability = new DB1(this.terser).getDisability();
            disability.patient_id  = patient.patient_id;
            String time1  = FormatTime.formatTime(new Date());
            disability.disability_id = patient.patient_id.substring(0, (20 - time1.length()) >
                patient.patient_id.length()? patient.patient_id.length() : 20 - time1.length() )+ time1;
            //
            result += "," + Json.getJson(disability);
        }catch (Exception e){
            for(int i = 0; i < 10; i++) {
                try {
                    this.terser.get("/DB1(" + i + ")-1");
                    disability = new DB1(this.terser).getDisability(i);
                    disability.patient_id  = patient.patient_id;
                    String time1  = FormatTime.formatTime(new Date());
                    disability.disability_id = patient.patient_id.substring(0, (20 - time1.length()) >
                        patient.patient_id.length()? patient.patient_id.length() : 20 - time1.length() )+ time1;
                    //
                    result += "," + Json.getJson(disability);
                } catch (Exception ex) {
                    break;
                }
            }
        }

        Allergy allergy;
        try{
            this.terser.get("AL1-1");
            allergy = new AL1(this.terser).getAllergy();
            if(allergy != null) {
                allergy.patient_id = patient.patient_id;
                String time1 = FormatTime.formatTime(new Date());
                allergy.allergy_id = patient.patient_id.substring(0, (20 - time1.length()) >
                    patient.patient_id.length() ? patient.patient_id.length() : 20 - time1.length()) + time1;
                result += "," + Json.getJson(allergy);
            }
        }catch (Exception e){

        }

        OutPatient outPatient = new OutPatient();
        try{
            this.terser.get("/DG1-1");
            outPatient = new DG1(this.terser).getOutPatient(outPatient);
            outPatient = new NTE(this.terser).getOutPatient(outPatient);
            outPatient.hospital_name = new PID(this.terser).getHospital();
            String time1  = FormatTime.formatTime(new Date());
            outPatient.diagnosis_id = patient.patient_id.substring(0, (20 - time1.length()) >
                patient.patient_id.length()? patient.patient_id.length() : 20 - time1.length() )+ time1;
            outPatient.doctor_advice_id = doctorAdvice.doctor_advice_id;
            outPatient.patient_id = patient.patient_id;
            //
            result += "," + Json.getJson(outPatient);
        }catch (Exception e){
            outPatient = null;
        }

        return result;
    }

}
