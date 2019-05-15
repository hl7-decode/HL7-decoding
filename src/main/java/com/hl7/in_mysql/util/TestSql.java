package com.hl7.in_mysql.util;

import com.hl7.in_mysql.mapper.PatientMapper;
import com.hl7.manage.SqlOperation;

public class TestSql {
    public static void main(String[] args) {

        MybatisUtils.getMapper(PatientMapper.class, (SqlOperation<PatientMapper>)(knife) -> {
            System.out.println(knife.selectAll());
        });
    }
}
