package com.hl7.eventdecode.segment;

import com.hl7.eventdecode.deal.*;
import com.hl7.in_mysql.enuitity.Patient;

/**
 * 对PID段的消息进行处理
 * PID-1 顺序号
 * PID-2 患者ID
 * PID-3 患者标识表
 * PID-4 备选患者ID-PID（4.1 婴儿标志 4.5 患者信息保密等级（例如0/空 不需要保密  1 需要保密））
 * PID-5 患者姓名 （5.1 拼音 5.2 患者姓名）
 * PID-6 暂无
 * PID-7 出生日期
 * PID-8 性别
 * PID-9 暂无
 * PID-10
 * PID-11 患者地址
 * PID-12 
 * PID-13 家庭电话
 * PID-14 工作电话
 * PID-15
 * PID-16 婚姻状况
 * PID-17 宗教
 * PID-18 医保卡号
 * PID-19 身份证号
 * PID-20 
 * PID-21 母亲的标识ID
 * PID-22 民族
 * PID-23 出生地
 * PID-24 
 * PID-25
 * PID-26
 * PID-27
 * PID-28 国籍
 * PID-29
 * PID-30
 * PID-31 黑名单病人
 * PID-34 最近更新机构
 * 。
 * 。
 * 。
 * PID-39 籍贯  （自定义）
 * PID-40 户口地址  （自定义）
 * PID-41 工作单位   （自定义）
 * PID-42 职业       （自定义）
 * PID-43 ABO血型      （自定义）
 */

public class PID {

    private Terser terser;

    public PID(Terser terser){
        this.terser = terser;
    }

    public Patient getPatient(){
        Patient patient = new Patient();
        try{
            patient.patient_id = terser.get("PID-2");
//            System.out.println(patient.patient_id);

            String patient_name = terser.get("PID-5-2");
            patient.patient_name = (patient_name == null ? terser.get("PID-5") : patient_name);

            patient.sex = terser.get("PID-8");

            String home_phone = terser.get("PID-13");
            patient.patient_home_phone = (home_phone == null ? null : home_phone);

            String work_phone = terser.get("PID-14");
            patient.patient_work_phone = (work_phone == null ? null : work_phone);

            String address = terser.get("PID-11");
            patient.patient_address = (address == null ? null : address);

            patient.patient_marry = terser.get("PID-16");
            patient.birthday = terser.get("PID-7");
            patient.religion = terser.get("PID-17");
            patient.nation = terser.get("PID-22");
            patient.country = terser.get("PID-28");
            patient.id_card_number = terser.get("PID-19");
            patient.authority = (terser.get("PID-4-5") == null ? 1 : terser.get("PID-4-5").charAt(0) - '0' + 1);
            patient.remark = null;

        }catch (Exception e){
            return null;
        }
        return patient;
    }

    public String getHospital(){
        try{
            return terser.get("PID-34");
        }catch (Exception e){
            return null;
        }
    }

    public Patient getPatient(int i){
        Patient patient = new Patient();
        try{
            patient.patient_id = terser.get("PID("+ i + ")-2");

            String patient_name = terser.get("PID(" + i + ")-5-2");
            patient.patient_name = (patient_name == null ? terser.get("PID(" + i + ")-5") : patient_name);

            patient.sex = terser.get("PID(" + i + ")-8");

            String home_phone = terser.get("PID(" + i + ")-13");
            patient.patient_home_phone = (home_phone == null ? null : home_phone);

            String work_phone = terser.get("PID(" + i + ")-14");
            patient.patient_work_phone = (work_phone == null ? null : work_phone);

            String address = terser.get("PID(" + i + ")-11");
            patient.patient_address = (address == null ? null : address);

            patient.patient_marry = terser.get("PID(" + i + ")-16");
            patient.birthday = terser.get("PID(" + i + ")-7");
            patient.religion = terser.get("PID(" + i + ")-17");
            patient.nation = terser.get("PID(" + i + ")-22");
            patient.country = terser.get("PID(" + i + ")-28");
            patient.id_card_number = terser.get("PID(" + i + ")-19");
            patient.authority = (terser.get("PID(" + i + ")-4-5") == null ? 1 : terser.get("PID(" + i + ")-4-5").charAt(0) - '0' + 1);
            patient.remark = null;

        }catch (Exception e){
            return null;
        }
        return patient;
    }
    
}
