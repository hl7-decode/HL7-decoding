package com.example.hl7server.dao;


import com.example.hl7server.enuitity.OutPatient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OutPatientDao {

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
}
