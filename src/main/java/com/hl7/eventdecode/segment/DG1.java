package com.hl7.eventdecode.segment;

import com.hl7.eventdecode.deal.*;
import com.hl7.in_mysql.enuitity.OutPatient;

/**
 * DG1 字段信息处理
 * DG1-1 序号
 * DG1-2 诊断代码
 * DG1-4 诊断描述
 * DG1-5 诊断日期
 * DG1-6 诊断类型（例如  入院诊断代码标志 A   出院诊断代码标志 F ）
 * DG1-16 诊断医师
 */

public class DG1 {

    private Terser terser;

    public DG1(Terser terser){
        this.terser = terser;
    }


    public OutPatient getOutPatient(OutPatient outPatient){

        try{
            outPatient.doctor_name = terser.get("DG1-16");
            outPatient.doctor_diagnosis = terser.get("DG1-4");
        }catch (Exception e){
            return null;
        }
        return outPatient;
    }

    public OutPatient getOutPatient(OutPatient outPatient, int i){

        try{
            outPatient.doctor_name = terser.get("DG1(" + i + ")-16");
            outPatient.doctor_diagnosis = terser.get("DG1(" + i + ")-4");
        }catch (Exception e){
            return null;
        }
        return outPatient;
    }
}
