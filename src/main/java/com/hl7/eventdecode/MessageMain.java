package com.hl7.eventdecode;

import com.hl7.eventdecode.deal.*;
import com.hl7.eventdecode.event.*;
import com.hl7.eventdecode.segment.MSH;

public class MessageMain {

    private Terser terser;

    public MessageMain(String message){
        try{
            this.terser = new Terser(message);
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
        }
        return "解析错误";
    }

    public static void main(String[] args) {

    }
}
