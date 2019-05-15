package com.hl7.in_mysql.manager;

import com.hl7.in_mysql.enuitity.Allergy;
import com.hl7.in_mysql.mapper.AllergyMapper;
import com.hl7.in_mysql.mapper.PatientMapper;
import com.hl7.in_mysql.util.MybatisUtils;
import com.hl7.manage.SqlOperation;

public class AllergyManager {
    public static void insert(final Allergy allergy){

        MybatisUtils.getMapper(AllergyMapper.class, (SqlOperation<PatientMapper>)(knife)->{
            if(knife.selectById(allergy.patient_id) == null){
                System.out.println("不存在该患者，请确认后再插入");
            } else {
                MybatisUtils.getMapper(AllergyMapper.class, (SqlOperation<AllergyMapper>)(knife1)->{
                    if(knife1.selectByPatient(allergy.patient_id) != null){
                        System.out.println("已存在该患者的过敏信息，请重新确认您的操作！");
                    } else {
                        knife1.insertAllergy(allergy);
                        System.out.println("插入患者信息成功");
                    }
                });
            }
        });
    }

    public static void update(final Allergy allergy){
        MybatisUtils.getMapper(AllergyMapper.class, (SqlOperation<AllergyMapper>)(knife)->{
            if(knife.selectByPatient(allergy.patient_id) != null){
                int number = knife.updateByPatient(allergy);
                if(number == 1){
                    System.out.println("过敏信息更新成功！");
                } else {
                    System.out.println("过敏信息更新失败！");
                }
            }
        });
    }
}
