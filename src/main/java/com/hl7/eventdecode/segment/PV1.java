package com.hl7.eventdecode.segment;

import com.hl7.eventdecode.deal.*;
import com.hl7.in_mysql.enuitity.PatientHospital;

/**
 * PV1段的信息处理
 * PV1-1 住院次数
 * PV1-2 患者分类
 * PV1-3 患者位置（病区^病房^病床^科室 ID&科室名称 ）
 * PV1-4 入院类型（R:常规 ）
 * PV1-5 预收入院号码
 * PV1-6 前患者位置
 * PV1-7 主治医生
 * PV1-8 助理医生
 * PV1-9 咨询医生
 * PV1-10 医院服务       //挂号类别  （例如  1 普通 2 急诊 4 专家 6 老教授）
 * PV1-11 临时位置
 * PV1-12 预收入院检验标识
 * PV1-13 再次入院标识
 * PV1-14 入院来源
 * PV1-15 走动状况
 * PV1-16 VIP标识
 * PV1-17 入院医生  （例如  1173^王薇薇 ）
 * PV1-18 患者类型
 * PV1-19 访问号 （19.1 就诊ID或者患者住院ID）
 * PV1-20 经济状况类别
 * PV1-21 费用价格标识
 * PV1-22 礼貌代码
 * PV1-23 信贷分类
 * PV1-24 合同代码
 * PV1-25 入科日期
 * PV1-29 欠费标识 0/空：不欠费  1：欠费
 * PV1-36 出院处置
 * PV1-39 挂号的上下午标志
 * PV1-42 当前位置（科室 ID^^^院区 ID ）
 * PV1-44 就诊/住院时间
 * PV1-45 出院时间
 * PV1-51 访问标志
 * PV1-52 操作人信息  （例如 1173^王薇薇）
 */

public class PV1 {
    private Terser terser;

    public PV1(Terser terser){
        this.terser = terser;
    }

    public PatientHospital getPatientHospital(){
        PatientHospital patientHospital = new PatientHospital();
        try{
            patientHospital.admission_id = null;
            patientHospital.admit_source = terser.get("PV1-14");

            String attending_doctor = terser.get("PV1-7-2");
            patientHospital.attending_doctor = (attending_doctor == null ? terser.get("PV1-7") : attending_doctor);

            String consult_doctor = terser.get("PV1-9-2");
            patientHospital.consult_doctor = (consult_doctor == null ? terser.get("PV1-9") : consult_doctor);

            String admitting_doctor = terser.get("PV1-17-2");
            patientHospital.admitting_doctor = (admitting_doctor == null ? terser.get("PV1-17") : admitting_doctor);

            String location = terser.get("PV1-3");
            patientHospital.patient_location = location;

            String referring_doctor = terser.get("PV1-8-2");
            patientHospital.referring_doctor = (referring_doctor == null ? terser.get("PV1-8") : referring_doctor);

            String admit_time = terser.get("PV1-44");
            patientHospital.admit_time = admit_time;

            String out_time = terser.get("PV1-45");
            if(out_time!=null && out_time.equals(""))
                out_time = null;
            patientHospital.out_time = out_time;

            String discharged_to_location = terser.get("PV1-36");
            patientHospital.discharged_to_location = discharged_to_location;

            String bed_status = terser.get("PV1-4");
            patientHospital.bed_status = getKind(bed_status);

            patientHospital.hospital = null;


        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return patientHospital;
    }

    public static String getKind(String code){
        String str[] = {
            "事故",
            "紧急",
            "分娩",
            "常规",
            "新生儿",
            "危重",
            "选择的"
        };
        if(code.length() < 1) return null;
        switch (code.charAt(0)){
            case 'A': return str[0];
            case 'E': return str[1];
            case 'L': return str[2];
            case 'R': return str[3];
            case 'N': return str[4];
            case 'U': return str[5];
            case 'C': return str[6];
            default: return null;
        }
    }


    public PatientHospital getPatientHospital(int i){
        PatientHospital patientHospital = new PatientHospital();
        try{
            patientHospital.admission_id = null;
            patientHospital.admit_source = terser.get("PV1(" + i + ")-14");

            String attending_doctor = terser.get("PV1(" + i + ")-7-2");
            patientHospital.attending_doctor = (attending_doctor == null ? terser.get("PV1(" + i + ")-7") : attending_doctor);

            String consult_doctor = terser.get("PV1(" + i + ")-9-2");
            patientHospital.consult_doctor = (consult_doctor == null ? terser.get("PV1(" + i + ")-9") : consult_doctor);

            String admitting_doctor = terser.get("PV1(" + i + ")-17-2");
            patientHospital.admitting_doctor = (admitting_doctor == null ? terser.get("PV1(" + i + ")-17") : admitting_doctor);

            String location = terser.get("PV1(" + i + ")-3");
            patientHospital.patient_location = location;

            String referring_doctor = terser.get("PV1(" + i + ")-8-2");
            patientHospital.referring_doctor = (referring_doctor == null ? terser.get("PV1(" + i +")-8") : referring_doctor);

            String admit_time = terser.get("PV1(" + i + ")-44");
            patientHospital.admit_time = admit_time;

            String out_time = terser.get("PV1(" + i + ")-45");
            patientHospital.out_time = out_time;

            String discharged_to_location = terser.get("PV1(" + i + ")-36");
            patientHospital.discharged_to_location = discharged_to_location;

            String bed_status = terser.get("PV1(" + i + ")-4");
            patientHospital.bed_status = getKind(bed_status);

            patientHospital.hospital = null;
        }catch (Exception e){
            return null;
        }

        return patientHospital;
    }
}
