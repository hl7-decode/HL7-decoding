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
                if(knife.insert(patient) == 1){
                     System.out.println("插入患者信息成功");
                }else {
                     System.out.println("插入患者信息失败");
                }
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

    public static void delete(final Patient patient){
        MybatisUtils.getMapper(PatientMapper.class, (SqlOperation<PatientMapper>)(knife)->{
            if(knife.selectById(patient.patient_id) == null) {
                 System.out.println("不存在该患者，请检查信息");
            }else {
                int number = knife.delete(patient);
                if(number == 1){
                     System.out.println("患者信息删除成功!");
                }else{
                     System.out.println("患者信息删除失败！");
                }
            }
        });
    }


    public static Patient selectById(final Patient patient){
        return selectById(patient.patient_id);
    }

    static Patient result = null;
    public static Patient selectById(final String patient_id){
        MybatisUtils.getMapper(PatientMapper.class, (SqlOperation<PatientMapper>)(knife) -> {
            result = knife.selectById(patient_id);
        });
        Patient res = result;
        result = null;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(selectById("12321321321321212345"));
//        System.out.println(result);
    }
}
