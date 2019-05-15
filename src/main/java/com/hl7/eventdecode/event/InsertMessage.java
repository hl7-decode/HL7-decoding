package com.hl7.eventdecode.event;

import com.hl7.eventdecode.deal.*;
import com.hl7.eventdecode.segment.NK1;
import com.hl7.eventdecode.segment.NTE;
import com.hl7.eventdecode.segment.PID;
import com.hl7.in_mysql.enuitity.DoctorAdvice;
import com.hl7.in_mysql.enuitity.Family;
import com.hl7.in_mysql.enuitity.Patient;
import com.hl7.in_mysql.util.FormatTime;
import com.hl7.in_mysql.util.Json;

import java.util.Date;

public class InsertMessage {
    public void getNecessyMessage(Terser terser){
        String result = "";

        Patient patient = new PID(terser).getPatient();
        //获取患者信息并
        result += "," + Json.getJson(patient);

        //写入数据库 暂无
        Family family;
        try{
            terser.get("NK1-1");
            family = new NK1(terser).getFamily();
            family.patient_id = patient.patient_id;
            String time = FormatTime.formatTime(new Date());
            family.family_id = patient.patient_id.substring(0, (20 - time.length()) >
                patient.patient_id.length()? patient.patient_id.length() : 20 - time.length() )+ time;

            result += "," + Json.getJson(family);
            //
        }catch (Exception e){
            for(int i = 0; i < 10; i++) {
                try {
                    terser.get("/NK1(" + i + ")-1");
                    family = new NK1(terser).getFamily(i);
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
            terser.get("NTE-1");
            doctorAdvice = new NTE(terser).getDoctorAdvice(new DoctorAdvice());
            if(doctorAdvice != null){
                doctorAdvice.doctor_advice_id = FormatTime.formatTime(new Date());
                result += "," + Json.getJson(doctorAdvice);
                //
            }
        }catch (Exception e){

        }
    }
}
