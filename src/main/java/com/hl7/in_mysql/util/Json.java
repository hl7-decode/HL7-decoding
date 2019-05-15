package com.hl7.in_mysql.util;

import com.google.gson.Gson;

public class Json {
    public static String getJson(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}
