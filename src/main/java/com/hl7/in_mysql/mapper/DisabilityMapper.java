package com.hl7.in_mysql.mapper;

import com.hl7.in_mysql.enuitity.Disability;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DisabilityMapper {

    @Select("select * from `disability` where `patient_id` = #{patient_id};")
    List<Disability> selectByPatient(@Param("patient_id") String patient_id);

    @Select("select * from `disability` where `disability_id` = #{disability};")
    Disability selectById(@Param("disability_id") String disability);


    @Update("update `disability` set" +
        "`disability_message` = #{disability.disability_message}, " +
        "`disability_start_time` = #{disability.disability_start_time}," +
        "`disability_end_time` = #{disability.disability_end_time}, `remark` = #{disability.remark}" +
        "where `disability_id` = #{disability.disability_id};")
    int update(@Param("disability") Disability disability);

    @Update("update `disability` set `disability_end_time` = #{disability.disability_end_time}" +
            " where `disability_id` = #{disability.disability_id};")
    int updateEndTime(@Param("disability") Disability disability);

    @Update("update `disability` set `remark` " +
            "= #{disability.remark} where `disability_id` = #{disability.disability_id};")
    int updateRemark(@Param("disability") Disability disability);

    @Update("update `disability` set `disability_end_time` = #{disability.disability_end_time}" +
            "`disability` set `remark` = #{disability.remark}" +
            " where `disability_id` = #{disability.disability_end_time};")
    int updateRemarkAndEnd(@Param("disability") Disability disability);

    @Insert("insert into `disability` values(#{disability.disability_id}," +
            "#{disability.patient_id},#{disability.disability_message}, " +
            "#{disability.disability_start_time},#{disability.disability_end_time},#{disability.remark});")
    int insertDisability(@Param("disability") Disability disability);
}
