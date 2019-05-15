package com.hl7.in_mysql.util;

import com.hl7.manage.SqlOperation;
import org.apache.ibatis.session.*;

import java.io.InputStream;

public class MybatisUtils {

    public static SqlSessionFactory getFactory(){
        String resource = "sql-config/conf.xml";
        InputStream is = MybatisUtils.class.getClassLoader().getResourceAsStream(resource);
        if(is == null)
            System.out.println("error!");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        return factory;
    }

    public static void getMapper(Class object, SqlOperation sqlOperation){
        SqlSessionFactory factory = MybatisUtils.getFactory();
        SqlSession session = factory.openSession(true);
        Object result = session.getMapper(object);
        sqlOperation.operation(result);
        session.close();
    }
}
