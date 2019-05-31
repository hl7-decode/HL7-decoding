package com.hl7.in_mysql.mapper;


import com.hl7.in_mysql.enuitity.OutPatient;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OutPatientMapper {

    @Select("select * from `out_patient` where `diagnosis_id` = #{diagnosis_id};")
    OutPatient selectById(String diagnosis_id);

    @Select("select * from `out_patient` where `patient_id` = #{patient_id};")
    List<OutPatient> selectByPatient(String patient_id);

    @Select("select * from `out_patient`;")
    List<OutPatient> selectAll();

    @Select("select * from `out_patient` where `doctor_name` like #{doctor_name};")
    List<OutPatient> selectByDoctor(String doctor_name);

    @Select("select * from `out_patient` where `hospital_name` like #{hospital_name};")
    List<OutPatient> selectByHospital(String hospital_name);

    @Select("select `patient_message`.`authority` from `patient_message`, " +
            "`out_patient` where `out_patient`.`diagnosis_id` = #{diagnosis_id} and" +
            "`out_patient`.`patient_id` = `patient_message`.`patient_id`;")
    int selectAuthority(String diagnosis_id);

    @Update("update `out_patient` set `remark` = #{out_patient.remark};")
    int updateRemark(@Param("out_patient") OutPatient out_patient);


    @Update("update `out_patient` set" +
        "`doctor_advice_id` = #{outPatient.doctor_advice_id}, `patient_read_me` = #{outPatient.patient_read_me}" +
        "`doctor_diagnosis` = #{outPatient.doctor_diagnosis}, " +
        "`doctor_name` = #{outPatient.doctor_name}, `hospital_name` = #{outPatient.hospital_name}," +
        "`remark` = #{outPatient.remark}" +
        "where `diagnosis_id` = #{outPatient.diagnosis_id};")
    int update(@Param("outPatient") OutPatient outPatient);

    @Insert("insert into `out_patient` values(#{outPatient.diagnosis_id}, " +
        "#{outPatient.patient_id},#{outPatient.doctor_advice_id}, #{outPatient.patient_read_me}," +
        "#{outPatient.doctor_diagnosis}, #{outPatient.doctor_name}, #{outPatient.hospital_name}," +
        " #{outPatient.remark});")
    int insert(@Param("outPatient") OutPatient outPatient);

    @Delete("delete from `out_patient` where `patient_id` = #{patient_id};")
    int delete(@Param("patient_id") String patient_id);
}
