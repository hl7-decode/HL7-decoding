package com.hl7.in_mysql.mapper;

import com.hl7.in_mysql.enuitity.Patient;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PatientMapper {

    /**
     * 本方法利用患者ID查询患者信息
     * @param patient_id 患者ID
     * @return
     */

    @Select("select * from `patient_message` where `patient_id` = #{patient_id};")
    Patient selectById(@Param("patient_id") String patient_id);



    @Select("select * from `patient_message`;")
    List<Patient> selectAll();


//    `country` like #{message} or`id_card_number` like #{message}" +

    @Select("select * from `patient_message` where (" +
        "`patient_address` like #{message} or `patient_name` like #{message} " +
        "or `nation` = #{message} or `sex` like #{message}) and `authority` <= #{authority};")
    List<Patient> selectByMessage(String message, int authority);

    /**
     * 本方法利用身份证号查询患者相关信息
     */
    @Select("select * from `patient_message` where `id_card_number` = #{id_card_number};")
    List<Patient> selectByIDCard(@Param("id_card_number") String id_card_number);


    /**
     * 本方法利用患者地址查找相关患者信息
     **/
    @Select("select * from `patient_message` where `patient_address` like #{patient_address};")
    List<Patient> selectByAdress(@Param("patient_address") String patient_address);


    /**
     * 本方法利用患者姓名查询相关信息
     * */
    @Select("select * from `patient_message` where `patient_name` like #{patient_name};")
    List<Patient> selectByName(@Param("patient_name") String patient_name);

    /**
     * 本方法利用民族来查询相关患者信息
     * */
    @Select("select * from `patient_message` where `nation` = #{nation};")
    List<Patient> selectByNation(@Param("nation") String nation);

    /**
     * 本方法利用所在国籍查询相关信息
     * */
    @Select("select * from `patient_message` where `country` = #{country};")
    List<Patient> selectByCountry(@Param("country") String country);

    @Select("select * from `patient_message` where `religion` = #{religion};")
    List<Patient> selectByReligion(@Param("religion") String religion);

    @Select("select * from `patient_message` where `sex` = #{sex};")
    List<Patient> selectBySex(@Param("sex") String sex);

    @Select("select * from `patient_message` where `authority` <= 1;")
    List<Patient> selectAllPatient1();

    @Select("select * from `patient_message` where `authority` <= 2;")
    List<Patient> selectAllPatient2();

    @Select("select * from `patient_message`;")
    List<Patient> selectAllPatient3();

    @Insert("insert into `patient_message`" +
        "values(#{patient.patient_id},#{patient.patient_name},#{patient.birthday}," +
        "#{patient.sex}, #{patient.id_card_number}, #{patient_address}, #{patient.patient_work_phone}," +
        "#{patient.patient_home_phone},#{patient.patient_marry},#{patient.nation},#{patient.country}," +
        "#{patient.religion},#{patient.remark},#{patient.authority});")
    int insert(@Param("patient") Patient patient);


    @Update("update `patient_message` set `patient_address`"+
        "= #{patient.patient_address} where `patient_id` = #{patient.patient_id};")
    int updateAddress(@Param("patient") Patient patient);


    @Update("update `patient_message` set `patient_name` = #{patient.patient_name}, `birthday` = #{patient.birthday}," +
        "`sex` = #{patient.sex}, `id_card_number` = #{patient.id_card_number}, `patient_address` = #{patient_address}," +
        "`patient_work_phone` = #{patient.patient_work_phone}," +
        "patient_home_phone` = #{patient.patient_home_phone}, `patient_marry` = #{patient.patient_marry}," +
        "`nation` = #{patient.nation}, `country` = #{patient.country}," +
        "`religion` = #{patient.religion},`remark` = #{patient.remark}, `authority` = #{patient.authority} " +
        "where `patient_id` = #{patient.patient_id};")
    int updateAll(Patient patient);

    @Update("update `patient_message` set `patient_work_phone`"
        + "= #{patient.patient_work_phone} where `patient_id` = #{patient.patient_id};")
    int updateWorkPhone(@Param("patient") Patient patient);

    @Update("update `patient_message` set `patient_home_phone`"+
        "= #{patient.patient_home_phone} where `patient_id` = #{patient.patient_id};")
    int updateHomePhone(@Param("patient") Patient patient);

    @Update("update `patient_message` set `patient_marry`"+
        "= #{patient.patient_marry} where `patient_id` = #{patient.patient_id};")
    int updateMarry(@Param("patient") Patient patient);

    @Update("update `patient_message` set `country`"+
        "= #{patient.country} where `patient_id` = #{patient.patient_id};")
    int updateCountry(@Param("patient") Patient patient);

    @Update("update `patient_message` set `religion`"+
        "= #{patient.religion} where `patient_id` = #{patient.patient_id};")
    int updateReligion(@Param("patient") Patient patient);

    @Update("update `patient_message` set `remark`"+
        "= #{patient.remark} where `patient_id` = #{patient.patient_id};")
    int updateRemark(@Param("patient") Patient patient);

    @Update("update `patient_message` set `authority`"+
        "= #{patient.authority} where `patient_id` = #{patient.patient_id};")
    int updateAuthority(@Param("patient") Patient patient);
}

