package app.test.decollate;

public class Lexer {
    String message = "";
    char peek = ' ';
    String words = "";
    
    public Lexer(String message){
        this.message = message;
        this.length = this.message.length();
    }

    int number = 0;
    int length = 0;

    void readch() {
        if(number < length){
            peek = this.message.charAt(number);
            number ++;
        } else {
            peek = '$';
            return;
        }
    }

    boolean readch(char c){
        readch();
        if (peek != c)
            return false;
        peek = ' ';
        return true;
    }

    public String scan() {

        for (;; readch()) {
            if (peek == ' ' || peek == '\t')
                continue;
            else if (peek == '/') {
            } else {
                break;
            }
        }

        switch (peek) {
            case '$':
                readch();
                return "$";
            case '&':
                readch();
                return "&";
            case '|':
                readch();
                return "|";
            case '^':
                readch();
                return "^";
            case '\r':
                readch();
                return "\r";
            case '~':
            readch();
            return "~";
        }

        if (!isSpecialCharacter(peek)) {
            StringBuffer b = new StringBuffer();
            do {
                b.append(peek);
                readch();
            } while (!isSpecialCharacter(peek));

            String s = b.toString();
            return s;
        }

        if (peek == '\'') {
            if (readch('\'')) {
                
            }
        }

        String token = String.valueOf(peek);
        peek = ' ';
        return token;
    }

    public boolean isSpecialCharacter(char peek){
        switch (peek) {
            case '~':
            case '$':
            case '&':
            case '|':
            case '^':
            case '\r':
                return true;
        default: return false;
        }
    }

    public static void main(String[] argv) {
        String message = "MSH|^~\\&|HIS|RIH|EKG|EKG|199904140038||ADT^A01||P|2.2\r"
                + "PID|0001|00009874|00001122|A00977|SMITH^JOHN^M|MOM|19581119|F|NOTREAL^LINDA^M|C|564 SPRING ST^^NEEDHAM^MA^02494^US|0002|(818)565-1551|(425)828-3344|E|S|C|0000444444|252-00-4414||||SA|||SA||||NONE|V1|0001|I|D.ER^50A^M110^01|ER|P00055|11B^M011^02|070615^BATMAN^GEORGE^L|555888^NOTREAL^BOB^K^DR^MD|777889^NOTREAL^SAM^T^DR^MD^PHD|ER|D.WT^1A^M010^01|||ER|AMB|02|070615^NOTREAL^BILL^L|ER|000001916994|D||||||||||||||||GDD|WA|NORM|02|O|02|E.IN^02D^M090^01|E.IN^01D^M080^01|199904072124|199904101200|199904101200||||5555112333|||666097^NOTREAL^MANNY^P\r"
                + "NK1|0222555|NOTREAL^JAMES&R|FA|STREET^OTHER STREET^CITY^ST^55566|(222)111-3333|(888)999-0000|||||||ORGANIZATION\r"
                + "PV1|0001|I|D.ER^1F^M950^01|ER|P000998|11B^M011^02|070615^BATMAN^GEORGE^L|555888^OKNEL^BOB^K^DR^MD|777889^NOTREAL^SAM^T^DR^MD^PHD|ER|D.WT^1A^M010^01|||ER|AMB|02|070615^VOICE^BILL^L|ER|000001916994|D||||||||||||||||GDD|WA|NORM|02|O|02|E.IN^02D^M090^01|E.IN^01D^M080^01|199904072124|199904101200|||||5555112333|||666097^DNOTREAL^MANNY^P\r"
                + "PV2|||0112^TESTING|55555^PATIENT IS NORMAL|NONE|||19990225|19990226|1|1|TESTING|555888^NOTREAL^BOB^K^DR^MD||||||||||PROD^003^099|02|ER||NONE|19990225|19990223|19990316|NONE\r"
                + "AL1||SEV|001^POLLEN\r"
                + "GT1||0222PL|NOTREAL^BOB^B||STREET^OTHER STREET^CITY^ST^77787|(444)999-3333|(222)777-5555||||MO|111-33-5555||||NOTREAL GILL N|STREET^OTHER STREET^CITY^ST^99999|(111)222-3333\r"
                + "IN1||022254P|4558PD|BLUE CROSS|STREET^OTHER STREET^CITY^ST^00990||(333)333-6666||221K|LENIX|||19980515|19990515|||PATIENT01 TEST D||||||||||||||||||02LL|022LP554";
        Lexer lexer = new Lexer(message);
        String string = lexer.scan();
        int i = 0;
        while(!string.equals("$")){
            if(string.contains("\n") || string.contains("\r"))
                System.out.println("换行符");
            else
                System.out.println((i++) + "    " + string + "   " + (int)string.charAt(0));
            string = lexer.scan();
        }
    }

}