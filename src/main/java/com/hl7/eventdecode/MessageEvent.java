package com.hl7.eventdecode;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.EncodingNotSupportedException;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.util.Terser;
import com.hl7.eventdecode.event.*;
import com.hl7.eventdecode.segment.MSH;
import com.sun.xml.internal.bind.marshaller.MinimumEscapeHandler;

/**
 * 拟先筛选出事件
 * A01 接收病人
 * A03 病人出院
 * A06 门诊病人转住院病人
 * A07 住院病人转门诊病人
 * A08 更新病人信息
 */

public class MessageEvent{

//    public HapiContext context;
    public Terser terser;
    public Message msg;

    public MessageEvent(String message){
//        this.context = new DefaultHapiContext();
//        this.parser = this.context.getGenericParser();
        try{
            PipeParser pipeParser = new PipeParser();
            this.msg = pipeParser.parse(message);
            this.terser = new Terser(msg);

//            createADT(type);
//            this.hapiMsg = this.parser.parse(message);
        }catch(EncodingNotSupportedException e){
            e.printStackTrace();
            return;
        } catch(HL7Exception e){
            e.printStackTrace();
            return;
        }
        System.out.println("结束");
    }

    public String createADT(){
        String type = new MSH(this.terser).getEventType();
        if(type.equals("A01")){
            return new A01(this.terser).GetMessage();
        }else if (type.equals("A03")){
            return new A03(this.terser).getMessage();
        }else if (type.equals("A06")){
            return new A06(this.terser).getMessage();
        }else if (type.equals("A07")){
            return new A07(this.terser).getMessage();
        }else if (type.equals("A08")){
            return new A08(this.terser).getMessage();
        }
        return "解析错误";
    }

    public static void main(String[] args) {
        MessageEvent m = new MessageEvent("MSH|^~\\&|HIS|RIH|EKG|EKG|199904140038||ADT^A01||P|2.2\r"
                + "PID|0001|00009874|00001122|A00977|SMITH^JOHN^M|MOM|19581119|F|NOTREAL^LINDA^M|C|564 SPRING ST^^NEEDHAM^MA^02494^US|0002|(818)565-1551|(425)828-3344|E|S|C|0000444444|252-00-4414||||SA|||SA||||NONE|V1|0001|I|D.ER^50A^M110^01|ER|P00055|11B^M011^02|070615^BATMAN^GEORGE^L|555888^NOTREAL^BOB^K^DR^MD|777889^NOTREAL^SAM^T^DR^MD^PHD|ER|D.WT^1A^M010^01|||ER|AMB|02|070615^NOTREAL^BILL^L|ER|000001916994|D||||||||||||||||GDD|WA|NORM|02|O|02|E.IN^02D^M090^01|E.IN^01D^M080^01|199904072124|199904101200|199904101200||||5555112333|||666097^NOTREAL^MANNY^P\r"
                + "NK1|0222555|NOTREAL^JAMES^R|FA|STREET^OTHER STREET^CITY^ST^55566|(222)111-3333|(888)999-0000|||||||ORGANIZATION\r"
                + "PV1|0001|I|D.ER^1F^M950^01|ER|P000998|11B^M011^02|070615^BATMAN^GEORGE^L|555888^OKNEL^BOB^K^DR^MD|777889^NOTREAL^SAM^T^DR^MD^PHD|ER|D.WT^1A^M010^01|||ER|AMB|02|070615^VOICE^BILL^L|ER|000001916994|D||||||||||||||||GDD|WA|NORM|02|O|02|E.IN^02D^M090^01|E.IN^01D^M080^01|199904072124|199904101200|||||5555112333|||666097^DNOTREAL^MANNY^P\r"
                + "PV2|||0112^TESTING|55555^PATIENT IS NORMAL|NONE|||19990225|19990226|1|1|TESTING|555888^NOTREAL^BOB^K^DR^MD||||||||||PROD^003^099|02|ER||NONE|19990225|19990223|19990316|NONE\r"
                + "AL1||SEV|001^POLLEN\r"
                + "GT1||0222PL|NOTREAL^BOB^B||STREET^OTHER STREET^CITY^ST^77787|(444)999-3333|(222)777-5555||||MO|111-33-5555||||NOTREAL GILL N|STREET^OTHER STREET^CITY^ST^99999|(111)222-3333\r"
                + "IN1||022254P|4558PD|BLUE CROSS|STREET^OTHER STREET^CITY^ST^00990||(333)333-6666||221K|LENIX|||19980515|19990515|||PATIENT01 TEST D||||||||||||||||||02LL|022LP554");
        System.out.println(m.createADT());
        MessageEvent msh = new MessageEvent("MSH|^~\\&|HIS|RIH|EKG|EKG|199904140038||ADT^A01||P|2.2\r" +
            "PID|0001|000098743234||^^^^0|wuqinggang^吴庆港||19980108|男|||山东省威海市哈尔滨工业大学威海|||||未婚|||370832111144447777|||汉族||||||中国||||||威海404医院||||||||||||||||||||||||||||||||||||||||||||||||||\r" +
            "DB1||||双目失明|20190101|20190110\r" +
            "NK1|||母子||||||||||||女|||||||||||||||diqiu^地球|17865412365|山东省威海市哈尔滨工业大学\r" +
            "PV1|||F病区^E病房^1病床^皮肤科|R|||李大嘴|吕秀才|白展堂||||||||莫小贝|||||||||||||||||||健康出院||||||||20190407|20190410||||||||\r" +
            "NTE|||||多休息|佟湘玉|20190108\r" +
            "AL1||DA|青霉素|SV|休克");
        System.out.println(msh.createADT());
    }
}
