package com.hl7.in_mysql.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatTime {

    public static String formatTime(Date date){
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
