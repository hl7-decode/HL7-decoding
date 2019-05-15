package com.hl7.in_mysql.manager;

import com.hl7.in_mysql.enuitity.Patient;
import com.hl7.in_mysql.mapper.PatientMapper;
import com.hl7.in_mysql.util.MybatisUtils;
import com.hl7.manage.SqlOperation;

public class PatientManager {
    public static void insert(final Patient patient){
        MybatisUtils.getMapper(PatientMapper.class, (SqlOperation<PatientMapper>)(knife)->{
            if(knife.selectById(patient.patient_id) == null){
                int number = knife.insert(patient);
                if(number == 1){
                    System.out.println("患者信息插入成功！");
                }else {
                    System.out.println("患者信息插入失败！");
                }
            } else {
                System.out.println("患者信息已存在！");
            }
        });
    }

    public static void update(final Patient patient){
        MybatisUtils.getMapper(PatientMapper.class, (SqlOperation<PatientMapper>)(knife)->{
            if(knife.selectById(patient.patient_id) == null) {
                System.out.println("不存在该患者，请检查信息");
            }else {
                int number = knife.updateAll(patient);
                if(number == 1){
                    System.out.println("患者信息更新成功!");
                }else{
                    System.out.println("患者信息更新失败！");
                }
            }
        });
    }
}
