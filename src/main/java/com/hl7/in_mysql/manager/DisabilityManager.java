package com.hl7.in_mysql.manager;

import com.hl7.in_mysql.enuitity.Disability;
import com.hl7.in_mysql.mapper.DisabilityMapper;
import com.hl7.in_mysql.mapper.PatientMapper;
import com.hl7.in_mysql.util.MybatisUtils;
import com.hl7.manage.SqlOperation;


// 特此标注：残疾信息主键应由残疾开始时间和患者ID组成，以保证其唯一和确定性
public class DisabilityManager {
    public static void insert(final Disability disability){
        MybatisUtils.getMapper(PatientMapper.class, (SqlOperation<PatientMapper>)(knife1) -> {
           if(knife1.selectById(disability.patient_id) != null){
               MybatisUtils.getMapper(DisabilityMapper.class, (SqlOperation<DisabilityMapper>)(knife) -> {
                   if(knife.selectById(disability.disability_id) != null){
                       System.out.println("数据库已存在此残疾信息，请重新检查！");
                   } else {
                       int number = knife.insertDisability(disability);
                       if(number == 1){
                           System.out.println("插入患者信息成功！");
                       } else {
                           System.out.println("插入患者信息失败!");
                       }
                   }
               });
           }else {
               System.out.println("相关患者不存在，请检查患者信息！");
           }
        });
    }

    public static void update(final Disability disability){
        MybatisUtils.getMapper(DisabilityMapper.class, (SqlOperation<DisabilityMapper>)(knife) -> {
            if(knife.selectById(disability.disability_id) != null){
                int number = knife.update(disability);
                if(number == 1){
                    System.out.println("更改患者残疾信息成功！");
                } else {
                    System.out.println("更改患者残疾信息失败！");
                }
            } else {
                int number = knife.insertDisability(disability);
                if(number == 1){
                    System.out.println("残疾信息插入成功");
                } else {
                    System.out.println("残疾信息插入失败");
                }
            }
        });
    }

    public static void delete(String patient_id){
        MybatisUtils.getMapper(DisabilityMapper.class, (SqlOperation<DisabilityMapper>)(knife) -> {
            if(knife.selectByPatient(patient_id) != null){
                int number = knife.delete(patient_id);
                if(number != 0){
                    System.out.println("删除患者残疾信息成功！");
                } else {
                    System.out.println("删除患者残疾信息失败！");
                }
            }
        });
    }

}
