package com.hl7.eventdecode.deal.exception;

public class WrongSearchKey extends Exception {

    public WrongSearchKey(){
        super("您输入消息位置不明确");
    }

    public WrongSearchKey(String place){
        super("您输入" + place + "的相关位置不明确");
    }
}
