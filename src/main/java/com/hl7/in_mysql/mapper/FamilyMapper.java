package com.hl7.in_mysql.mapper;

import com.hl7.in_mysql.enuitity.Family;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FamilyMapper {

    @Select("select * from `family`;")
    List<Family> selectAll();

    @Select("select * from `family` where `patient_id` = #{patient_id};")
    List<Family> selectByPatient(String patient_id);

    @Select("select * from `family` where `family_id` = #{family_id};")
    Family selectById(String family_id);

    @Update("update `family` set `patient_id` = #{family.patient_id}, " +
            "`family_name` = #{family.family_name}, `sex` = #{family.sex}," +
            "`family_phone` = #{family.family_phone}, `family_relation` = #{family.family_relation}," +
            "`family_address` = #{family.family_address}, `last_update_time` = #{family.last_update_time}," +
            "`remark` = #{family.remark} where `family_id` = #{family.family_id};")
    int updateFamily(@Param("family") Family family);

    @Insert("insert into `family` values(#{family.family_id},#{family.patient_id}, " +
            "#{family.family_name}, #{family.sex}, #{family.family_phone}, " +
            "#{family.family_relation}, #{family.family_address}, #{family.last_update_time},#{family.remark});")
    int insertFamily(@Param("family") Family family);

    @Delete("delete from `family` where `family_id` = #{family_id};")
    int deleteFamily(String family_id);

    @Delete("delete from `family` where `patient_id` = #{patient_id};")
    int delete(@Param("patient_id") String patient_id);
}
