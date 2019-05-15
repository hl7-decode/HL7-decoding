package com.hl7.eventdecode.deal.struct;

import com.hl7.eventdecode.deal.exception.ArrayNullPointException;
import com.hl7.eventdecode.deal.exception.ProgramQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Component
 */
public class Component {
    public String componentString = "";
    public List<SubComponent> subComponents = new ArrayList<>();
    public Component(){

    }

    public Component(String string){
        this.componentString = string;
    }

    public void setComponentString(String string){
        this.componentString = string;
    }

    public void setSubComponentString(String string){
        if(!componentString.equals(""))
            componentString += "&";
        componentString += string;
        subComponents.add(new SubComponent(string));
    }

    public String get(){
        return componentString;
    }

    public String get(int number){
        return subComponents.get(number).get();
    }

    public String get(String target) throws ProgramQuestion, ArrayNullPointException {
        int firstPoint = target.indexOf('-');
        if(firstPoint != -1){
            //抛出异常
            throw new ProgramQuestion();
        }
        int number = Integer.parseInt(target);
        if(number > subComponents.size() || number < 0){
            //异常
            throw new ArrayNullPointException();
        }
        return this.subComponents.get(number).get();
    }
    
}
