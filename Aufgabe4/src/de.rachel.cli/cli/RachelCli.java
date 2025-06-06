package cli;

import parserlib.ParseCsv;
import java.util.Arrays;

public class RachelCli {
        public static void main(String[] args) {
            ParseCsv objectForParsing = new ParseCsv();
            
            objectForParsing.parseCsv("sp1;sp2;sp3" + System.lineSeparator() + "aksdjfldsaf;askdfjasdfj;sadkfjasdjkf" + System.lineSeparator() + "aksdfjads;askdjfdsakjf;sdkfjsadkf" + System.lineSeparator() + "alksdjfasdf;asdfd;sadfsadf");
            
            System.out.println(objectForParsing.getHtmlTable());
        }
    }
