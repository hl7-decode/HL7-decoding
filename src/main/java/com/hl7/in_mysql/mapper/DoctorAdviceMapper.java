package com.example.hl7server.dao;


import com.example.hl7server.enuitity.DoctorAdvice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DoctorAdviceDao {

    @Select("select * from `doctor_advice`;")
    List<DoctorAdvice> selectAll();

    @Select("select * from `doctor_advice` where `doctor_advice_id` = #{doctor_advice_id};")
    DoctorAdvice selectById(@Param("doctor_advice_id") String doctor_advice_id);

    @Select("select * from `doctor_advice` where `doctor_name` = #{doctor};")
    List<DoctorAdvice> selectByDoctor(@Param("doctor") String doctor);

    @Update("update `doctor_advice` set `remark` = #{advice.remark}" +
            " where `doctor_advice_id` = #{advice.doctor_advice_id};")
    int updateRemark(@Param("advice") DoctorAdvice advice);

}
