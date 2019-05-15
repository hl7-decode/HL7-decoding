package com.hl7.eventdecode.segment;

import ca.uhn.hl7v2.util.Terser;
import com.hl7.in_mysql.enuitity.Allergy;
import com.hl7.in_mysql.util.Json;

/**
 * AL1 患者过敏信息字段解析
 *
 * AL1-2 过敏类型代码
 * AL1-3 过敏原代码/记忆/描述
 * AL1-4 过敏严重程度代码
 * AL1-5 过敏反应
 */

public class AL1 {
    private Terser terser;

    public AL1(Terser terser){
        this.terser = terser;
    }

    public Allergy getAllergy(){
        Allergy allergy = new Allergy();
        try{
            allergy.allergy_id = null;
            allergy.patient_id = null;
            allergy.allergy_reaction_code = terser.get("/AL1-5");
            allergy.allergy_type_code = terser.get("/AL1-2");
            allergy.allergy_severity_code = terser.get("Al1-4");
            String allergyMessage = terser.get("/AL1-3-3");
            allergy.allergy_type_message = (allergyMessage == null ? terser.get("AL1-3") : allergyMessage);
            allergy.remark = null;
        }catch (Exception e){
            return null;
        }
        return allergy;
    }


    public Allergy getAllergy(int i){
        Allergy allergy = new Allergy();
        try{
            allergy.allergy_id = null;
            allergy.patient_id = null;
            allergy.allergy_reaction_code = terser.get("/AL1(" + i +")-5");
            allergy.allergy_type_code = terser.get("/AL1(" + i + ")-2");
            allergy.allergy_severity_code = terser.get("Al1(" + i + ")-4");
            String allergyMessage = terser.get("/AL1(" + i + ")-3-3");
            allergy.allergy_type_message = (allergyMessage == null ? terser.get("AL1(" + i + ")-3") : allergyMessage);
            allergy.remark = null;
        }catch (Exception e){
            return null;
        }
        return allergy;

    }


}
