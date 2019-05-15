package com.example.hl7server.dao;


import com.example.hl7server.enuitity.PatientHospital;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PatientHospitalDao {

    @Select("select * from `patient_hospital` where `admission_id` = #{admission_id};")
    PatientHospital selectById(String admission_id);

    @Select("select * from `patient_hospital` where `patient_id` = #{patient_id};")
    List<PatientHospital> selectByPatient(String patient_id);

    @Select("select * from `patient_hospital` where `attending_doctor` = #{doctor_name} " +
            "or `referring_doctor` = #{doctor_name} or `consult_doctor` = #{doctor_name}" +
            " or `admitting_doctor` = #{doctor_name};")
    List<PatientHospital> selectByDoctor(String doctor_name);

    @Select("select * from `patient_hospital` where `hospital` = #{hospital_name};")
    List<PatientHospital> selectByHospital(String hospital_name);

    @Select("select `patient_message`.`authority` from `patient_message`, " +
            "`patient_hospital` where `patient_hospital`.`diagnosis_id` = #{diagnosis_id} and" +
            "`patient_hospital`.`patient_id` = `patient_message`.`patient_id`;")
    int selectAuthority(String admission_id);

    @Update("update `patient_hospital` set `remark` = #{patient_hospital.remark}" +
            " where `admission_id` = #{patient_hospital.admission_id};")
    int updateRemark(PatientHospital patient_hospital);

}
