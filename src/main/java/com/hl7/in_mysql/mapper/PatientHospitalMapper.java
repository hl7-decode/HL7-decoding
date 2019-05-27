package com.hl7.in_mysql.mapper;


import com.hl7.in_mysql.enuitity.PatientHospital;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PatientHospitalMapper {

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
    int updateRemark(@Param("patient_hospital")PatientHospital patient_hospital);

    @Update("update `patient_hospital` set " +
        "`doctor_advice_id` = #{patientHospital.doctor_advice_id}, `patient_location` = #{patientHospital.patient_location}, " +
        "`attending_doctor` = #{patientHospital.attending_doctor}," +
        "`referring_doctor` = #{patientHospital.referring_doctor}, `consult_doctor` = #{patientHospital.consult_doctor}, " +
        "`admit_source` = #{patientHospital.admit_source}, " +
        "`admitting_doctor` = #{patientHospital.admitting_doctor}, `discharged_to_location` = #{patientHospital.discharged_to_location}," +
        "`bed_status` = #{patientHospital.bed_status}, `admit_time` = #{patientHospital.admit_time}, " +
        "`out_time` = #{patientHospital.out_time}, `remark` = #{patientHospital.remark}," +
        "`hospital` = #{patientHospital.hospital} " +
        "where `admission_id` = #{patientHospital.admission_id};")
    int update(@Param("patientHospital") PatientHospital patientHospital);

    @Insert("insert into `patient_hospital` values(#{patientHospital.admission_id}, #{patientHospital.patient_id}," +
        "#{patientHospital.doctor_advice_id}, #{patientHospital.patient_location}, #{patientHospital.attending_doctor}," +
        "#{patientHospital.referring_doctor}, #{patientHospital.consult_doctor}," +
        "#{patientHospital.admit_source}, #{patientHospital.admitting_doctor}, #{patientHospital.discharged_to_location}," +
        "#{patientHospital.bed_status}, #{patientHospital.admit_time}, #{patientHospital.out_time}, #{patientHospital.remark}," +
        "#{patientHospital.hospital});")
    int insert(@Param("patientHospital") PatientHospital patientHospital);

    @Delete("delete from `patient_hospital` where `patient_id` = #{patient_id};")
    int delte(@Param("patient_id") String patient_id);

}
