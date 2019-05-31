package com.hl7.in_mysql.manager;

import com.hl7.in_mysql.enuitity.Patient;
import com.hl7.in_mysql.enuitity.PatientHospital;
import com.hl7.in_mysql.mapper.PatientHospitalMapper;
import com.hl7.in_mysql.mapper.PatientMapper;
import com.hl7.in_mysql.util.MybatisUtils;
import com.hl7.manage.SqlOperation;

public class PatientHospitalManager {

    // 住院患者病历ID有患者ID和住院时间组成

    public static void insert(final PatientHospital patientHospital){
        MybatisUtils.getMapper(PatientMapper.class, (SqlOperation<PatientMapper>) (knife)  -> {
            if(knife.selectById(patientHospital.patient_id)!=null){
                MybatisUtils.getMapper(PatientHospitalMapper.class, (SqlOperation<PatientHospitalMapper>) (knife1) -> {
                    if(knife1.selectById(patientHospital.admission_id) != null){
                         System.out.println("住院病历已存在，请重新查看。");
                    } else {
                        int number = knife1.insert(patientHospital);
                        if(number == 1){
                             System.out.println("信息插入成功！");
                        } else {
                             System.out.println("信息插入失败！");
                        }
                    }
                });
            } else {
                 System.out.println("相关患者不存在，请检查相关信息是否正确!");
            }
        });
    }

    public static void update(PatientHospital patientHospital){
        MybatisUtils.getMapper(PatientHospitalMapper.class, (SqlOperation<PatientHospitalMapper>) (knife) -> {
            if(knife.selectById(patientHospital.admission_id) != null){
                int number = knife.update(patientHospital);
                if(number == 1){
                     System.out.println("信息更新成功！");
                } else {
                     System.out.println("信息更新失败！");
                }
            }
        });
    }

    public static void delete(String patient_id){
        MybatisUtils.getMapper(PatientHospitalMapper.class, (SqlOperation<PatientHospitalMapper>) (knife) -> {
            if(knife.selectByPatient(patient_id) != null){
                int number = knife.delete(patient_id);
                if(number != 0){
                     System.out.println("信息删除成功！");
                } else {
                     System.out.println("信息删除失败！");
                }
            }
        });
    }
}
