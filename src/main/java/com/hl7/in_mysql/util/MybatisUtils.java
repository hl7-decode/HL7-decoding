package com.hl7.in_mysql.mapper;

import org.apache.ibatis.session.*;

import java.io.InputStream;
import java.sql.Connection;

public class MybatisUtils {

    public static SqlSessionFactory getFactory(){
        String resource = "conf.xml";
        InputStream is = MybatisUtils.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        return factory;
    }
}
