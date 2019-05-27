package com.hl7.in_mysql.manager;

import com.hl7.in_mysql.enuitity.OutPatient;
import com.hl7.in_mysql.mapper.OutPatientMapper;
import com.hl7.in_mysql.mapper.PatientMapper;
import com.hl7.in_mysql.util.MybatisUtils;
import com.hl7.manage.SqlOperation;

import java.net.ServerSocket;

public class OutPatientManager {

    public static void insert(final OutPatient outPatient){
        MybatisUtils.getMapper(PatientMapper.class, (SqlOperation<PatientMapper>)(knife1) -> {
            if(knife1.selectById(outPatient.patient_id) != null){

                MybatisUtils.getMapper(OutPatientMapper.class, (SqlOperation<OutPatientMapper>)(knife) -> {
                    if(knife.selectById(outPatient.diagnosis_id) != null){
                        System.out.println("已存在相关诊断信息，请重新确认相关信息是否正确");
                    } else {
                        int number = knife.insert(outPatient);
                        if(number == 1){
                            System.out.println("门诊信息插入成功");
                        }else {
                            System.out.println("门诊信息插入失败");
                        }
                    }
                });
            } else {
                System.out.println("患者信息不存在，请检查您的患者信息！");
            }
        });
    }

    public static void update(final OutPatient outPatient){
        MybatisUtils.getMapper(OutPatientMapper.class, (SqlOperation<OutPatientMapper>)(knife) -> {
            if(knife.selectById(outPatient.diagnosis_id) != null){
                int number = knife.update(outPatient);
                if(number == 1){
                    System.out.println("更新门诊信息成功。");
                } else {
                    System.out.println("更新门诊信息失败。");
                }
            } else {
                System.out.println("相关门诊信息不存在!");
            }
        });
    }

    public static void delete(final String patient_id){
        MybatisUtils.getMapper(OutPatientMapper.class, (SqlOperation<OutPatientMapper>)(knife) -> {
            if(knife.selectByPatient(patient_id).size() != 0){
                if(knife.delete(patient_id) != 0){
                    System.out.println("删除成功！");
                }else{
                    System.out.println("删除失败！");
                }
            }
        });
    }
}
