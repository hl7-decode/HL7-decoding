package com.hl7.in_mysql.manager;

import com.hl7.in_mysql.enuitity.DoctorAdvice;
import com.hl7.in_mysql.mapper.DoctorAdviceMapper;
import com.hl7.in_mysql.util.MybatisUtils;
import com.hl7.manage.SqlOperation;

public class DoctorAdviceManager {
    public static void insert(DoctorAdvice doctorAdvice){
        MybatisUtils.getMapper(DoctorAdviceMapper.class, (SqlOperation<DoctorAdviceMapper>)(knife) -> {
            if(knife.selectById(doctorAdvice.doctor_advice_id) != null){
                 System.out.println("您要插入的医嘱已存在，请检查相关信息");
            } else {
                knife.insert(doctorAdvice);
            }
        });
    }

    public static void update(DoctorAdvice doctorAdvice){
        MybatisUtils.getMapper(DoctorAdviceMapper.class, (SqlOperation<DoctorAdviceMapper>)(knife) -> {
            if(knife.selectById(doctorAdvice.doctor_advice_id) == null){
                 System.out.println("您要更新的医嘱不存在，请重新检查信息！");
            } else {
                knife.update(doctorAdvice);
            }
        });
    }

    public static void delete(DoctorAdvice doctorAdvice){
        MybatisUtils.getMapper(DoctorAdviceMapper.class, (SqlOperation<DoctorAdviceMapper>)(knife) -> {
            if(knife.selectById(doctorAdvice.doctor_advice_id) == null){
                 System.out.println("您要删除的医嘱不存在，请重新检查信息！");
            } else {
                knife.delete(doctorAdvice);
            }
        });
    }
}
