package com.hl7.eventdecode.deal;


import com.hl7.eventdecode.deal.decollate.Lexer;
import com.hl7.eventdecode.deal.exception.ArrayNullPointException;
import com.hl7.eventdecode.deal.exception.HashNullPointException;
import com.hl7.eventdecode.deal.exception.ProgramQuestion;
import com.hl7.eventdecode.deal.exception.WrongSearchKey;
import com.hl7.eventdecode.deal.struct.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Terser
 */
public class Terser {

    private Lexer lexer;
    public HashMap<String, List<Field>> hashMap = new HashMap<>();

    public Terser(String message){
        this.lexer = new Lexer(message);
        this.build();
    }

    public List<Field> list;

    public void createMessageSegment(String name){
        list = new ArrayList<>();
        int length = contain(name);
        if (length == 0) {
            hashMap.put(name, list);
        } else {
            hashMap.put(name + "(" + length + ")", list);
        }
        if(name.equals("MSH")){
            hashMap.get(name).add(new Field("|"));
            String target = this.lexer.scan();
            StringBuffer s = new StringBuffer("");
            while (!target.equals("|")) {
                s.append(target);
                target = this.lexer.scan();
            }
            this.lastString = s.toString();
            this.lastStatus = 2;
            hashMap.get(name).add(new Field(this.lastString));
        }
    }

    int contain(String target) {
        if (hashMap.containsKey(target)) {
            hashMap.put(target + "(0)", hashMap.get(target));
            hashMap.remove(target);
            return 1;
        } else {
            int i = 0;
            while (hashMap.containsKey(target + "(" + i + ")")) {
                i++;
            }
            return i;
        }
    }

    private void build(){
        String target = this.lexer.scan();

        while(!target.equals("$")){
            statusDeal(target);
            target = this.lexer.scan();
        }
        statusDeal(target);
    }

    // 保存上一次的字符串（为非特殊字符）
    public String lastString = "";
    // 上一次特殊字符的状态码（1为消息段、2为字段、3为组件、4为子组件）
    public int lastStatus = 1;
    

    private void statusDeal(String target) {
        
        if(target.equals("\r")){
            this.judgeStatus(1);
            this.lastStatus = 1;
            return;
        } else if (target.equals("|")){
            this.judgeStatus(2);
            this.lastStatus = 2;
            return;
        } else if(target.equals("~")) {
            if(this.lastStatus != 5)
                this.judgeStatus(2);
            else{
                this.list.get(this.list.size() - 1).setFieldString(this.lastString);
                this.lastString = "";
            }
            this.list.get(this.list.size() - 1).createField();
            this.lastStatus = 5;
            return;
        } else if (target.equals("^")) {
            this.judgeStatus(3);
            this.lastStatus = 3;
            return;
        } else if (target.equals("&")) {
            this.judgeStatus(4);
            this.lastStatus = 4;
            return;
        } else if(target.equals("$")) {
            switch(lastStatus){
                case 1:
                    System.out.println("error");
                    this.lastString = "";
                    break;
                case 2:
                    this.list.add(new Field(this.lastString));
                    this.lastString = "";
                    break;
                case 3:
                    this.list.get(this.list.size() - 1).setComponentString(this.lastString);
                    this.lastString = "";
                    break;
                case 4:
                    this.list.get(this.list.size() - 1).setSubComponentString(this.lastString);
                    this.lastString = "";
                    break;
                default:break;
            }
        }
        else {
            this.lastString = target;
        }
    }

    private void judgeStatus(int status){
        switch (this.lastStatus) {
        case 1:
            if(status == 2)
                createMessageSegment(this.lastString);
            else
                System.out.println("error");
            this.lastString = "";
            break;
        case 2:
            if(status == 1){
                this.list.add(new Field(this.lastString));
            } else if (status == 2) {
                this.list.add(new Field(this.lastString));
            } else if (status == 3){
                Field field = new Field();
                field.setComponentString(this.lastString);
                this.list.add(field);
            }else if(status == 4){
                Field field = new Field();
                field.setNewSubComponentString(this.lastString);
                this.list.add(field);
            }
            this.lastString = "";
            break;
        case 3:
            if(status == 1){
                this.list.get(this.list.size() - 1).setComponentString(this.lastString);
            } else if(status == 2){
                this.list.get(this.list.size() - 1).setComponentString(this.lastString);
            } else if(status == 3){
                this.list.get(this.list.size() - 1).setComponentString(this.lastString);
            } else if(status == 4){
                this.list.get(this.list.size() - 1).setNewSubComponentString(this.lastString);
            }
            this.lastString = "";
            break;
        case 4:
            if (status == 1) {
                this.list.get(this.list.size() - 1).setSubComponentString(this.lastString);
            } else if (status == 2) {
                this.list.get(this.list.size() - 1).setSubComponentString(this.lastString);
            } else if (status == 3) {
                this.list.get(this.list.size() - 1).setSubComponentString(this.lastString);
            } else if (status == 4) {
                this.list.get(this.list.size() - 1).setSubComponentString(this.lastString);
            }
            this.lastString = "";
            break;
        case 5:
            if(status == 1){
                this.list.get(this.list.size() - 1).setFieldString(this.lastString);
            } if(status == 2) {
                this.list.get(this.list.size() - 1).setFieldString(this.lastString);
            } if(status == 3) {
                this.list.get(this.list.size() - 1).setComponentString(this.lastString);
            } if(status == 4) {
                this.list.get(this.list.size() - 1).setNewSubComponentString(this.lastString);
            }
            this.lastString = "";
            break;
        default:
            break;
        }
    }

    public String get(String target) throws HashNullPointException, WrongSearchKey, ArrayNullPointException, ProgramQuestion {
        int firstPoint = target.indexOf('-');
        if(firstPoint != -1){
            String targets = target.substring(0, firstPoint);
            int secondPoint = targets.indexOf('(');
            if(secondPoint == -1){
                List<Field> list;
                // System.out.println(hashMap.containsKey("AL1") + "  " + hashMap.keySet());
                // System.out.println(targets.equals("MSH") + "   " + targets + "   " + hashMap.containsKey(targets) + "  " + hashMap.containsKey("MSH"));
                if(this.hashMap.containsKey(targets)){
                    list = hashMap.get(targets);
//                }else if(this.hashMap.containsKey(targets + "(0)")){
//                    list = this.hashMap.get(targets+"(0)");
                } else {
                    //抛出异常
                    throw new HashNullPointException();
//                    System.out.println("哈希表中不存在相关键值");
//                    return null;
                }
                target = target.substring(firstPoint + 1);
                int thirdPoint = target.indexOf('-');
                if(thirdPoint != -1){
                    targets = target.substring(0, thirdPoint);
                } else {
                    targets = target;
                }
                int forthPoint = targets.indexOf('(');
                int fifthPoint = target.indexOf('(');
                int fieldPlace = 0;
                if(forthPoint != -1){
                    fieldPlace = Integer.parseInt(targets.substring(0, forthPoint));
                    target = target.substring(fifthPoint);
                } else {
                    fieldPlace = Integer.parseInt(targets);
                    if(thirdPoint == -1){
                        target = "";
                    } else
                        target = target.substring(thirdPoint + 1);
                }
                return list.get(fieldPlace - 1).get(target);
            } else {
                List<Field> list;
                if(this.hashMap.containsKey(targets)){
                    list = hashMap.get(targets);
                } else {
                    //抛出异常
                    throw new HashNullPointException();
//                    System.out.println("哈希表中不存在相关键值");
//                    return null;
                }

                target = target.substring(firstPoint + 1);
                int thirdPoint = target.indexOf('-');
                if(thirdPoint != -1){
                    targets = target.substring(0, thirdPoint);
                } else {
                    targets = target;
                }
                int forthPoint = targets.indexOf('(');
                int fifthPoint = target.indexOf('(');
                int fieldPlace = 0;
                if(forthPoint != -1){
                    fieldPlace = Integer.parseInt(targets.substring(0, forthPoint));
                    target = target.substring(fifthPoint);
                } else {
                    fieldPlace = Integer.parseInt(targets);
                    if(thirdPoint == -1){
                        target = "";
                    } else
                        target = target.substring(thirdPoint + 1);
                }
                return list.get(fieldPlace - 1).get(target);
            }
            
        } else {
//            System.out.println("error");
//            return null;
            throw new WrongSearchKey();
        }
//        return null;
    }


    public static void main(String[] args) throws Exception{
        String message = "MSH|^~\\&|HIS|RIH|EKG|EKG|199904140038||ADT^A01||P|2.2\r"
                + "PID|0001|00009874|00001122|A00977|SMITH^JOHN^M|MOM|19581119|F|NOTREAL^LINDA^M|C|564 SPRING ST^^NEEDHAM^MA^02494^US|0002|(818)565-1551|(425)828-3344|E|S|C|0000444444|252-00-4414||||SA|||SA||||NONE|V1|0001|I|D.ER^50A^M110^01|ER|P00055|11B^M011^02|070615^BATMAN^GEORGE^L|555888^NOTREAL^BOB^K^DR^MD|777889^NOTREAL^SAM^T^DR^MD^PHD|ER|D.WT^1A^M010^01|||ER|AMB|02|070615^NOTREAL^BILL^L|ER|000001916994|D||||||||||||||||GDD|WA|NORM|02|O|02|E.IN^02D^M090^01|E.IN^01D^M080^01|199904072124|199904101200|199904101200||||5555112333|||666097^NOTREAL^MANNY^P\r"
                + "NK1|0222555|NOTREAL~JAMES&R|FA|STREET^OTHER STREET^CITY^ST^55566|(222)111-3333|(888)999-0000|||||||ORGANIZATION\r"
                + "PV1|0001|I|D.ER^1F^M950^01|ER|P000998|11B^M011^02|070615^BATMAN^GEORGE^L|555888^OKNEL^BOB^K^DR^MD|777889^NOTREAL^SAM^T^DR^MD^PHD|ER|D.WT^1A^M010^01|||ER|AMB|02|070615^VOICE^BILL^L|ER|000001916994|D||||||||||||||||GDD|WA|NORM|02|O|02|E.IN^02D^M090^01|E.IN^01D^M080^01|199904072124|199904101200|||||5555112333|||666097^DNOTREAL^MANNY^P\r"
                + "PV2|||0112^TESTING|55555^PATIENT IS NORMAL|NONE|||19990225|19990226|1|1|TESTING|555888^NOTREAL^BOB^K^DR^MD||||||||||PROD^003^099|02|ER||NONE|19990225|19990223|19990316|NONE\r"
                + "AL1||SEV|001^POLLEN\r"
                + "GT1||0222PL|NOTREAL^BOB^B||STREET^OTHER STREET^CITY^ST^77787|(444)999-3333|(222)777-5555||||MO|111-33-5555||||NOTREAL GILL N|STREET^OTHER STREET^CITY^ST^99999|(111)222-3333\r"
                + "IN1||022254P|4558PD|BLUE CROSS|STREET^OTHER STREET^CITY^ST^00990||(333)333-6666||221K|LENIX|||19980515|19990515|||PATIENT01 TEST D||||||||||||||||||02LL|022LP554";
        Terser terser = new Terser(message);
        System.out.println("I have got this:" + terser.get("MSH-9"));
    }
}
