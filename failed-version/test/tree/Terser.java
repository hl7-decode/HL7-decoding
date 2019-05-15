package com.hl7.eventdecode.test.tree;

import java.util.HashMap;

public class Terser {

    private HashMap<String, String[]> hashMap = new HashMap<>();

    public Terser(String message) throws Exception{

    }


//        if(message.contains("\n"))
//            throw new Exception("HL7 数据错误");
//        String[] strings = message.split("\r");
//        for(int i = 0; i < strings.length; i ++ ) {
//            String content = strings[i].substring(3);
////            printArrays(values);
//            String start = strings[i].substring(0,3);
////            System.out.println(start);
//            if(start.equals("MSH")){
//                content = "|" + content;
//            }
//            String[] values = content.split("\\|");
//            int length = contain(start);
//            if( length == 0){
//                hashMap.put(start, values);
//            }else {
//                hashMap.put(start + "(" + length + ")", values);
//            }
//        }
//    }

    public String get(String target) throws Exception {
//        if(target.charAt(0) == '/'){
//            target = target.substring(1);
//        }
//        String[] targets = target.split("-");
//        int length = targets.length;
//        if(hashMap.containsKey(targets[0])){
//            if(targets.length <= 1)
//                throw new Exception("");
//            String[] content = hashMap.get(targets[0]);
//            return getField(content, target);
//            if(length == 2){
////                printArrays(targets);
//                return getTarget(targets[0], targets[1]);
//            }
//            else if(length == 3){
//                String content = getTarget(targets[0], targets[1]);
//                String[] result = content.split("\\^");
//                if(Integer.valueOf(targets[2]) > result.length)
//                    return null;
//                return result[Integer.valueOf(targets[2]) - 1];
////            }
//        }else {
//            throw new Exception("不存在该字段");
//        }

        throw new Exception("未知错误");
    }

//    public String getField(String[] content, String target) throws Exception{
//        if(!target.contains("-"))
//            throw new Exception("查找字段不正确");
//        target = target.substring(target.indexOf('-') + 1);
//        String[] targets = target.split("-");
//        String result = null;
//
//        if(targets[0].contains("(")){
//            int number  = Integer.valueOf(targets[0].substring(0,targets[0].indexOf('(')));
//            if(number >= content.length)
//                return null;
//            result = content[number];
//
//
//            int num = Integer.valueOf(targets[0].substring
//                (targets[0].indexOf("(") + 1, targets[0].indexOf(")")));
//            System.out.println(num);
//            String[] contents = result.split("~");
//            if(num > contents.length)
//                return null;
//            result = contents[num];
//        }else{
//            int number  = Integer.valueOf(targets[0]);
//            if(number >= content.length)
//                return null;
//            result = content[number];
//        }
//        if(targets.length > 1){
//            return getComponent(result, target);
//        }
//        return result;
//    }
//
//    public String getComponent(String content, String target) throws Exception{
//        if(!target.contains("-"))
//            throw new Exception("查找字段错误");
//        target = target.substring(target.indexOf('-') + 1);
//        String[] contents = content.split("\\^");
//        String[] targets = target.split("-");
//        String result = null;
//        int number  = Integer.valueOf(targets[0]);
//        if(number > contents.length)
//            return null;
//        result = contents[number - 1];
//        if(targets.length <= 1){
//            return result;
//        }
//        return getSubComponent(result, target);
//    }
//
//    public String getSubComponent(String content, String target) throws Exception{
//        if(!target.contains("-")){
//            throw new Exception("查找字段错误");
//        }
//        target = target.substring(target.indexOf('-') + 1);
//        String[] contents = content.split("&");
//        if(target == null || target.equals("")){
//            throw new Exception("结构错误");
//        }
//        String result = null;
//        int number = Integer.valueOf(target);
//        if(number > contents.length){
//            return null;
//        }
//        result = contents[number - 1];
//        return result;
//    }
//
//    public String getTarget(String head, String target) throws Exception{
//        if(!target.contains("(")){
//            String[] result = hashMap.get(head);
////            printArrays(result);
//            if(Integer.valueOf(target) >= result.length){
//                return null;
//            }
//            return result[Integer.valueOf(target)];
//        } else {
//            String num = target.substring(target.indexOf('('), target.indexOf(')'));
//            String number = target.substring(0, target.indexOf('('));
//            String[] result = hashMap.get(head);
//            if(Integer.valueOf(number) >= result.length){
//                return null;
//            }
//            String ano = result[Integer.valueOf(number)];
//            String[] last = ano.split("~");
//            if(Integer.valueOf(num) > last.length){
//                return null;
//            }
//            return last[Integer.valueOf(num) - 1];
//        }
//    }

    void printArrays(String[] target){
        for(String str : target)
            System.out.println(str);
    }

    int contain(String target){
        if(hashMap.containsKey(target)){
            hashMap.put(target + "(0)", hashMap.get(target));
            hashMap.remove(target);
            return 1;
        } else {
            int i = 0;
            while(hashMap.containsKey(target + "(" + i + ")")){
                i ++;
            }
            return i;
        }
    }

    public static void main(String[] args) {
        try{
            Terser terser = new Terser("MSH|^~\\&|HIS|RIH|EKG|EKG|199904140038||ADT^A01||P|2.2\r"
                + "PID|0001|00009874|00001122|A00977|SMITH^JOHN^M|MOM|19581119|F|NOTREAL^LINDA^M|C|564 SPRING ST^^NEEDHAM^MA^02494^US|0002|(818)565-1551|(425)828-3344|E|S|C|0000444444|252-00-4414||||SA|||SA||||NONE|V1|0001|I|D.ER^50A^M110^01|ER|P00055|11B^M011^02|070615^BATMAN^GEORGE^L|555888^NOTREAL^BOB^K^DR^MD|777889^NOTREAL^SAM^T^DR^MD^PHD|ER|D.WT^1A^M010^01|||ER|AMB|02|070615^NOTREAL^BILL^L|ER|000001916994|D||||||||||||||||GDD|WA|NORM|02|O|02|E.IN^02D^M090^01|E.IN^01D^M080^01|199904072124|199904101200|199904101200||||5555112333|||666097^NOTREAL^MANNY^P\r"
                + "NK1|0222555|NOTREAL^JAMES&R|FA|STREET^OTHER STREET^CITY^ST^55566|(222)111-3333|(888)999-0000|||||||ORGANIZATION\r"
                + "PV1|0001|I|D.ER^1F^M950^01|ER|P000998|11B^M011^02|070615^BATMAN^GEORGE^L|555888^OKNEL^BOB^K^DR^MD|777889^NOTREAL^SAM^T^DR^MD^PHD|ER|D.WT^1A^M010^01|||ER|AMB|02|070615^VOICE^BILL^L|ER|000001916994|D||||||||||||||||GDD|WA|NORM|02|O|02|E.IN^02D^M090^01|E.IN^01D^M080^01|199904072124|199904101200|||||5555112333|||666097^DNOTREAL^MANNY^P\r"
                + "PV2|||0112^TESTING|55555^PATIENT IS NORMAL|NONE|||19990225|19990226|1|1|TESTING|555888^NOTREAL^BOB^K^DR^MD||||||||||PROD^003^099|02|ER||NONE|19990225|19990223|19990316|NONE\r"
                + "AL1||SEV|001^POLLEN\r"
                + "GT1||0222PL|NOTREAL^BOB^B||STREET^OTHER STREET^CITY^ST^77787|(444)999-3333|(222)777-5555||||MO|111-33-5555||||NOTREAL GILL N|STREET^OTHER STREET^CITY^ST^99999|(111)222-3333\r"
                + "IN1||022254P|4558PD|BLUE CROSS|STREET^OTHER STREET^CITY^ST^00990||(333)333-6666||221K|LENIX|||19980515|19990515|||PATIENT01 TEST D||||||||||||||||||02LL|022LP554");
            System.out.println(terser.get("NK1-2-2-2"));

        }catch (Exception e){

        }
    }
}
