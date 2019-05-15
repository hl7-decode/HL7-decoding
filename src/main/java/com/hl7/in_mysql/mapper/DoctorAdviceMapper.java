package com.hl7.in_mysql.mapper;


import com.hl7.in_mysql.enuitity.DoctorAdvice;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DoctorAdviceMapper {

    @Select("select * from `doctor_advice`;")
    List<DoctorAdvice> selectAll();

    @Select("select * from `doctor_advice` where `doctor_advice_id` = #{doctor_advice_id};")
    DoctorAdvice selectById(@Param("doctor_advice_id") String doctor_advice_id);

    @Select("select * from `doctor_advice` where `doctor_name` = #{doctor};")
    List<DoctorAdvice> selectByDoctor(@Param("doctor") String doctor);

    @Update("update `doctor_advice` set `remark` = #{advice.remark}" +
            " where `doctor_advice_id` = #{advice.doctor_advice_id};")
    int updateRemark(@Param("advice") DoctorAdvice advice);

    @Update("update `doctor_advice` set `patient_status` = #{advice.patient_status}," +
        "`doctor_advice` = #{advice.doctor_advice}, `advice_time` = #{advice.advice_time}," +
        "`doctor_name` = #{advice.doctor_name}, `remark` = #{advice.remark}" +
        " where `doctor_advice_id` = #{advice.doctor_advice_id};")
    int update(@Param("advice") DoctorAdvice advice);

    @Insert("insert into `doctor_advice` values(" +
        "#{doctorAdvice.doctor_advice_id}, #{doctorAdvice.patient_status}, " +
        "#{doctorAdvice.doctor_advice}, #{doctorAdvice.advice_time}, " +
        "#{doctorAdvice.doctor_name},#{doctorAdvice.remark});")
    int insert(DoctorAdvice doctorAdvice);

}
