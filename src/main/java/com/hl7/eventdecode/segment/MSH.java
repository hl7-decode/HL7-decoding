package com.hl7.eventdecode.segment;

import java.util.ArrayList;
import java.util.List;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.util.Terser;

/**
 * 对MSH段的信息进行处理 MSH-1 字段分隔符 MSH-2 代码字符 MSH-3 发送应用程序（3.1 发送应用程序简称） MSH-4 发送设备
 * MSH-5 接收应用程序 (5.1 接收应用程序简称 5.2 暂无) MSH-6 接收设备 MSH-7 消息创建时间 （格式YYYYMMDDHHMMSS）
 * MSH-8 暂无 MSH-9 消息创建类型 （例如 ADT^A01^ADT_A01 ） MSH-10 消息控制ID MSH-11 消息处理ID
 * MSH-12 HL7版本号 MSH-13 顺序号
 */

public class MSH {
    private Terser terser;
    private String time;
    private String ADT;

    public MSH(Terser terser) {
        this.terser = terser;
    }

    public String getEventType() {
        String result = null;
        for(int i = 1; i < 10; i++)
        {
            result=null;
            try {
                result = terser.get("/MSH-9-" + i);
            } catch (HL7Exception e) {
                // TODO Auto-generated catch block
                break;
            }
            if(result != null)
            {
                if(isADT(result))
                    return result;
                else
                    continue;
            }
            else
                return null;
        }
        return result;
    }

    Boolean isADT(String string){

        for(int i = 1; i < 10; i ++){
            if(string.contains("0" + i))
                return true;
        }

        for(int i = 10; i < 40; i ++){
            if(string.contains("" + i))
                return true;
        }

        return false;
    }
    
}
