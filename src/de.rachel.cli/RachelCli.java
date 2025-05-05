package de.rachel.cli;

import aufgabe.zwei.ParseCsv;
import java.util.Arrays;

public class RachelCli {
        public static void main(String[] args) {
            ParseCsv ObjectForParsing = new ParseCsv();
            
            String[][] parsedValue = ObjectForParsing.parseCsv("sp1;sp2;sp3\naksdjfldsaf;askdfjasdfj;sadkfjasdjkf\naksdfjads;askdjfdsakjf;sdkfjsadkf\nalksdjfasdf;asdfd;sadfsadf");
            
            System.out.println(Arrays.deepToString(parsedValue));
        }
    }
