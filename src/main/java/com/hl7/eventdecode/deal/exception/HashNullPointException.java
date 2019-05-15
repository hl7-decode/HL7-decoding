package com.hl7.eventdecode.deal.exception;

import java.util.HashMap;

public class HashNullPointException extends Exception {
    public HashNullPointException(){
        super("哈希表中不存在相关键值");
    }
}
