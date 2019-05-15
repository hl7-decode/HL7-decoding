package com.hl7.in_mysql.util;

public class DealSpecialSymbol {

    public static String deal(String target){
        return target.replaceAll("^", "");
    }
}
