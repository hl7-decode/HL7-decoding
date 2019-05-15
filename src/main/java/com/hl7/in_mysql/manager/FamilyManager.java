package com.hl7.in_mysql.manager;

import com.hl7.in_mysql.enuitity.Family;
import com.hl7.in_mysql.mapper.FamilyMapper;
import com.hl7.in_mysql.mapper.PatientMapper;
import com.hl7.in_mysql.util.MybatisUtils;
import com.hl7.manage.SqlOperation;

// 联系人信息主键：由患者ID和联系人姓名构成，保证其唯一性
public class FamilyManager {
    public static void insert(Family family){
        MybatisUtils.getMapper(PatientMapper.class, (SqlOperation<PatientMapper>)(knife) -> {
            if(knife.selectById(family.patient_id) != null){
                MybatisUtils.getMapper(FamilyMapper.class, (SqlOperation<FamilyMapper>)(knife1) -> {
                    if(knife1.selectById(family.family_id) != null){
                        System.out.println("您的联系人信息已存在，请重新检查");
                    }else {
                        knife1.insertFamily(family);
                    }
                });
            }else{
                System.out.println("患者信息不存在，请重新检查相关信息！");
            }
        });
    }

    public static void update(Family family){
        MybatisUtils.getMapper(FamilyMapper.class, (SqlOperation<FamilyMapper>)(knife) -> {
            if(knife.selectById(family.family_id) == null){
                System.out.println("您要修改的相关联系人信息不存在！");
            }else {
                knife.updateFamily(family);
            }
        });
    }
}
