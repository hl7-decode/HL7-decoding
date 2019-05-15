package com.hl7.eventdecode.segment;

import ca.uhn.hl7v2.util.Terser;
import com.hl7.in_mysql.enuitity.Disability;

/**
 * DB1 字段信息处理
 * DB1-4 丧失能力信息
 * DB1-5 丧失能力起始日期
 * DB1-6 丧失能力结束日期
 */

public class DB1 {

    private Terser terser;

    public DB1(Terser terser){
        this.terser = terser;
    }

    public Disability getDisability(){
        Disability disability = new Disability();
        try{
            disability.disability_message = terser.get("/DB1-4");
            disability.disability_start_time = terser.get("/DB1-5");
            disability.disability_end_time = terser.get("/DB1-6");
        }catch (Exception e){
            return null;
        }

        return disability;
    }

    public Disability getDisability(int i){
        Disability disability = new Disability();
        try{
            disability.disability_message = terser.get("/DB1(" + i + ")-4");
            disability.disability_start_time = terser.get("/DB1(" + i + ")-5");
            disability.disability_end_time = terser.get("/DB1(" + i + ")-6");
        }catch (Exception e){
            return null;
        }

        return disability;
    }
}
