package com.hl7.in_mysql.manager;

import com.hl7.in_mysql.enuitity.Allergy;
import com.hl7.in_mysql.enuitity.Patient;
import com.hl7.in_mysql.mapper.AllergyMapper;
import com.hl7.in_mysql.mapper.PatientMapper;
import com.hl7.in_mysql.util.MybatisUtils;
import com.hl7.manage.SqlOperation;
import com.mysql.cj.protocol.a.MysqlBinaryValueDecoder;

public class AllergyManager {

    // allergy_id 由患者ID和过敏类型构成

    public static void insert(final Allergy allergy){

        MybatisUtils.getMapper(PatientMapper.class, (SqlOperation<PatientMapper>)(knife)->{
            if(knife.selectById(allergy.patient_id) == null){
                 System.out.println("不存在该患者，请确认后再插入");
            } else {
                MybatisUtils.getMapper(AllergyMapper.class, (SqlOperation<AllergyMapper>)(knife1)->{
                    if(knife1.selectById(allergy) != null){
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

    public static  void delete(final String patient_id){
        MybatisUtils.getMapper(AllergyMapper.class, (SqlOperation<AllergyMapper>)(knife)->{
            if(knife.selectByPatient(patient_id) != null){
                int number = knife.delete(patient_id);
                if(number != 0){
                     System.out.println("过敏信息删除成功！");
                } else {
                     System.out.println("过敏信息删除失败！");
                }
            }
        });
    }
}
