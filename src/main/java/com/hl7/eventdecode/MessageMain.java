package com.hl7.eventdecode;

import com.hl7.eventdecode.deal.*;
import com.hl7.eventdecode.event.*;
import com.hl7.eventdecode.segment.MSH;
import java.util.Date;

public class MessageMain {

    private Terser terser;

    public MessageMain(String message){
        try{
            // System.out.println(new Date().getTime());
            this.terser = new Terser(message);
            // System.out.println(new Date().getTime());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String createADT(){
        String type = new MSH(this.terser).getEventType();
        if(type.equals("A01")){
            return new A01(this.terser).GetMessage();
        }else if (type.equals("A03")){
            return new A03(this.terser).getMessage();
        }else if (type.equals("A06")){
            return new A06(this.terser).getMessage();
        }else if (type.equals("A07")){
            return new A07(this.terser).getMessage();
        }else if (type.equals("A08")){
            return new A08(this.terser).getMessage();
        }else if(type.equals("A23")){
            new A23(this.terser).opreation();
        }else{

        }
        return "解析错误";
    }

    public static void main(String[] args) {
        MessageMain m = new MessageMain("MSH|^~\\\\&|HIS|RIH|EKG|EKG|199904140038||ADT^A23||P|2.2\r" +
            "PID|0001|000098743234||^^^^0|wuqinggang^吴庆港||1998-01-08|男|||山东省威海市哈尔滨工业大学威海||17863136706|17863136709||未婚|||370832111144447777|||汉族||||||中国||||||威海404医院||||||||||||||||||||||||||||||||||||||||||||||||||\r" +
            "DB1||||双目失明|2019-01-01|2019-01-10\r" +
            "NK1|||母子||||||||||||女|||||||||||||||diqiu^地球|17865412365|山东省威海市哈尔滨工业大学\r" +
            "PV1|||F病区^E病房^1病床^皮肤科|R|||李大嘴|吕秀才|白展堂||||||||莫小贝|||||||||||||||||||健康出院||||||||2019-04-07|2019-04-10||||||||\r" +
            "NTE|||||多休息|佟湘玉|2019-01-08\r" +
            "AL1||DA|青霉素|SV|休克");
        m.createADT();
    }
}
