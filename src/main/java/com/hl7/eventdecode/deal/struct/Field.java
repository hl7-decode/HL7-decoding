package com.hl7.eventdecode.deal.struct;

import com.hl7.eventdecode.deal.exception.ArrayNullPointException;
import com.hl7.eventdecode.deal.exception.ProgramQuestion;
import com.hl7.eventdecode.deal.exception.WrongSearchKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Field
 */
public class Field {
    public List<String> fieldString = new ArrayList<>();
    public HashMap<Integer, List<Component>> components = new HashMap<>();
    public int FieldNum = 0;
    public StringBuffer currentString = new StringBuffer("");

    public void createField(){
        FieldNum++;
        components.put(FieldNum, new ArrayList<>());
        if(FieldNum > 1){
            fieldString.add(currentString.toString());
            currentString = new StringBuffer("");
        }
    }

    public Field(){
        createField();
    }

    public Field(String string){
        setFieldString(string);
        createField();
    }

    public void setFieldString(String string){
        fieldString.add(string);
    }

    public void setComponentString(String content){
        setComponents(1, content);
    }

    public void setSubComponentString(String content){
        setComponents(2, content);
    }

    public void setNewSubComponentString(String content) {
        setComponents(3, content);
    }

    public void setComponents(int status, String content) {
        List<Component> list = components.get(FieldNum);
        switch (status) {
            case 0:
                setFieldString(content);
                break;
            case 1:
                addStringOnCurrent(1, content);
                list.add(new Component(content));
                break;
            case 2:
                addStringOnCurrent(2, content);
                list.get(list.size() - 1).setSubComponentString(content);
                break;
            case 3:
                Component component = new Component();
                component.setSubComponentString(content);
                addStringOnCurrent(3, content);
                list.add(component);
            default:
                break;
        }
    }

    private void addStringOnCurrent(int i, String string){
        if(!currentString.toString().equals("")){
            if(i == 1)
                currentString.append("^");
            else if (i == 2)
                currentString.append("&");
            else if (i == 3)
                currentString.append("^");
        }
        currentString.append(string);
        if(fieldString.size() != 0){
            this.fieldString.remove(this.fieldString.size() - 1);
            this.fieldString.add(currentString.toString());
        } else {
            this.fieldString.add(currentString.toString());
        }
    }

    public String get(String target) throws WrongSearchKey, ArrayNullPointException, ProgramQuestion {
        String fieldResult = this.fieldString.get(0);
        List<Component> list;
        if(target.length() != 0 && target.charAt(0) == '('){
            int firstPoint = target.indexOf(')');
            if(firstPoint == -1){
//                //抛出异常
//                System.out.print("输入的访问地址有错，请检查");
                throw new WrongSearchKey();
            }
            int number = Integer.parseInt(target.substring(1, firstPoint)) + 1;
            if(number <= 0 || number > this.components.size()){
                //抛出异常
//                System.out.print("您的访问地址超出范围，请检查");
                throw new ArrayNullPointException();
            }

            fieldResult = this.fieldString.get(number);
            list = this.components.get(number);

            if(target.charAt(firstPoint + 1) == '-'){
                firstPoint++;
            }
            if(target.length() > firstPoint + 1){
                target = target.substring(firstPoint + 1);
            } else {
                target = "";
            }
        } else {
            list = this.components.get(1);
        }

        if(target.length() == 0){
            return fieldResult;
        }

        int secondPoint = target.indexOf('-');
        int componentPlace = 0;
        String targets;

        if(secondPoint == -1){
            targets = target;
            target = "";
        } else {
            targets = target.substring(0, secondPoint);
            target = target.substring(secondPoint + 1);
        }

        componentPlace = Integer.parseInt(targets) - 1;

        if(list.size() == 0 && list.size() <= componentPlace){
            return null;
        } else if (componentPlace < 0) {
            //抛出异常
            throw new WrongSearchKey("组件");
//            System.out.print("您输入的组件范围值不正确");
//            return null;
        } else {
            if(target.length() == 0){
                return list.get(componentPlace).componentString;
            } else {
                return list.get(componentPlace).get(target);
            }
        }
    }
}
