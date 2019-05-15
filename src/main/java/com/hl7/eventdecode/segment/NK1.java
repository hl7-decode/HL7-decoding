package com.hl7.eventdecode.segment;

import com.hl7.eventdecode.deal.*;
import com.hl7.in_mysql.enuitity.Family;
import com.hl7.in_mysql.util.FormatTime;

import java.util.Date;

/**
 * NK1 字段的消息处理
 * NK1-1 序号
 * NK-2
 * NK-3 关系 （3.1 ID 3.2 名称  例如 01^本人）
 * NK-13 工作单位（13.1 工作单位名称）
 * NK1-15 性别
 * NK-30 联系人姓名（30.1 拼音 30.2 姓名）
 * NK-31 联系人电话 （31.7 联系人电话）
 * NK-32 联系地址 （例如  北京市海淀区开拓路 7 号^^北京^北京市^100085 ）
 */

public class NK1 {
    private Terser terser;

    public NK1(Terser terser){
        this.terser = terser;
    }

    public Family getFamily(){
        Family family = new Family();
        try{
            String family_id = terser.get("NK1-3-1");
            family.family_id = (family_id == null ? null : family_id);
            family.patient_id = null;
            String family_name = terser.get("NK1-30-2");
            family.family_name = (family_name == null ? terser.get("NK1-30") : family_name);

            String relation = terser.get("NK1-3-2");
            family.family_relation = (relation == null ? terser.get("NK1-3") : relation);

            String phone = terser.get("NK1-31");
            family.family_phone = (phone == null ? null : phone);

            family.sex = terser.get("NK1-15");

            String address = terser.get("NK1-32");
            family.family_address = dealAddress(address);

            family.last_update_time = FormatTime.formatTime(new Date());
            family.remark = null;
        }catch (Exception e){
            return null;
        }
        return family;
    }

    public static String dealAddress(String address){
        if(address == null)
            return null;
        return address.replace("^", " ");
    }


    public Family getFamily(int i){
        Family family = new Family();
        try{
            String family_id = terser.get("NK1("+ i +")-3-1");
            family.family_id = (family_id == null ? null : family_id);
            family.patient_id = null;
            String family_name = terser.get("NK1(" + i + ")-30-2");
            family.family_name = (family_name == null ? terser.get("NK1("+ i + ")-30") : family_name);

            String relation = terser.get("NK1(" + i + ")-3-2");
            family.family_relation = (relation == null ? terser.get("NK1(" + i + ")-3") : relation);

            String phone = terser.get("NK1(" + i + ")-31");
            family.family_phone = (phone == null ? null : phone);

            family.sex = terser.get("NK1(" + i + ")-15");

            String address = terser.get("NK1(" + i + ")-32");
            family.family_address = dealAddress(address);

            family.last_update_time = FormatTime.formatTime(new Date());
            family.remark = null;
        }catch (Exception e){
            return null;
        }
        return family;
    }

}
