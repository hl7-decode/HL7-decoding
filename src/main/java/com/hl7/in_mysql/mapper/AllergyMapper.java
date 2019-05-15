package com.hl7.in_mysql.mapper;


import com.hl7.in_mysql.enuitity.Allergy;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AllergyMapper {

    @Select("select * from `allergy`;")
    List<Allergy> selectBy();

    @Select("select * from `allergy` where `allergy_id` = #{allergy.allergy_id};")
    List<Allergy> selectById(@Param("allergy") Allergy allergy);

    @Select("select * from `allergy` where `patient_id` = #{patient_id};")
    List<Allergy> selectByPatient(String patient_id);

    @Update("update `allergy` set `allergy_type_code` = #{allergy.allergy_type_code}, " +
            "`allergy_type_message` = #{allergy.allergy_type_message}, " +
            "`allergy_severity_code` = #{allergy.allergy_severity_code}, " +
            "`allergy_reaction_code` = #{allergy.allergy_reaction_code}," +
            "`remark` = #{allergy.remark};")
    int updateMessage(@Param("allergy") Allergy allergy);

    @Update("update `allergy` set `allergy.allergy_id` = #{allergy.allergy_id}," +
        "`allergy_type_code` = #{allergy.allergy_type_code}," +
        "allergy_type_message` = #{allergy.allergy_type_message}, `allergy_severity_code` = #{allergy.allergy_severity_code}," +
        "`allergy_reaction_code` = #{allergy.allergy_reaction_code}, `remark` = #{allergy.remark} where `patient_id` = #{allergy.patient_id};")
    int updateByPatient(@Param("allergy") Allergy allergy);

    @Delete("delete from `allergy` where `allergy_id` = #{allergy_id};")
    int deleteAllergy(String allergy_id);

    @Insert("insert into `allergy` values(#{allergy.allergy_id}, #{allergy.patient_id}" +
            ",#{allergy.allergy_type_code}," +
            "#{allergy.allergy_type_message},#{allergy.allergy_severity_code}," +
            "#{allergy.allergy_reaction_code}, #{allergy.remark});")
    int insertAllergy(@Param("allergy") Allergy allergy);
}
