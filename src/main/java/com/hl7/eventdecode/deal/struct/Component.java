package app.test.struct;

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

    public String get(String target){
        int firstPoint = target.indexOf('-');
        if(firstPoint != -1){
            //抛出异常
        }
        int number = Integer.parseInt(target);
        if(number > subComponents.size() || number < 0){
            //异常
        }
        return this.subComponents.get(number).get();
    }
    
}