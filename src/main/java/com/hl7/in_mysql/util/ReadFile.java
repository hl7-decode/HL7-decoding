package com.hl7.in_mysql.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class ReadFile {
    public static String readFile(String path){
        StringBuffer stringBuffer = new StringBuffer("");
        File file = new File(path);
        Scanner scanner;
        try{
            scanner = new Scanner(new FileInputStream(file));
            while(scanner.hasNextLine()){
                stringBuffer.append(scanner.nextLine());
                if(scanner.hasNextLine()){
                    stringBuffer.append("\n");
                }
            }
        }catch (Exception e){

        }

        return stringBuffer.toString();
    }

    public static String replaceEnter(String target){
        String stringBuffer = "";
        String string[] = target.split("\n");
        for(int i = 0; i < string.length; i ++){
            stringBuffer +=string[i];
            stringBuffer += "\r";
        }
        System.out.println("palce " + string.length + " " + stringBuffer);

        return stringBuffer;
    }
}
