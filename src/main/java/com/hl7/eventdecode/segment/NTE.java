package com.hl7.eventdecode.segment;

import ca.uhn.hl7v2.util.Terser;
import com.hl7.in_mysql.enuitity.DoctorAdvice;
import com.hl7.in_mysql.enuitity.OutPatient;

import javax.print.Doc;

/**
 * NTE字段信息处理
 * NTE-1 NTE序号
 * NTE-2 病理描述
 * NTE-4 患者主诉（4.4 例如 ：反复咳嗽气急8年，再发9天 ）
 *
 *
 * 以下为自定义字段
 * NTE-5 医生建议
 * NTE-6 医生姓名
 * NTE-7 医嘱时间
 */

public class NTE {
    private Terser terser;

    public NTE(Terser terser){
        this.terser = terser;
    }

    public OutPatient getOutPatient(OutPatient outPatient){

        try{
            outPatient.patient_read_me = terser.get("/NTE-4");
        }catch (Exception e){
            return null;
        }
        return outPatient;
    }

    public OutPatient getOutPatient(OutPatient outPatient, int i){

        try{
            outPatient.patient_read_me = terser.get("/NTE("+ i + ")-4");
        }catch (Exception e){
            return null;
        }

        return outPatient;
    }

    public DoctorAdvice getDoctorAdvice(DoctorAdvice doctorAdvice){
        try{
            doctorAdvice.doctor_advice = terser.get("/NTE-5");
            doctorAdvice.advice_time = terser.get("/NTE-7");
            doctorAdvice.doctor_name = terser.get("/NTE-6");
        }catch (Exception e){
            return null;
        }
        return doctorAdvice;
    }

    public DoctorAdvice getDoctorAdvice(DoctorAdvice doctorAdvice, int i){
        try{
            doctorAdvice.doctor_advice = terser.get("/NTE("+ i + ")-5");
            doctorAdvice.advice_time = terser.get("/NTE("+ i + ")-7");
            doctorAdvice.doctor_name = terser.get("/NTE(" + i + ")-6");
        }catch (Exception e){
            return null;
        }
        return doctorAdvice;
    }
}
