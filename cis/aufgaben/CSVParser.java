// CSV Parser
// Einen CSV-String zu einer tabellarischen Datenstruktur parsen
// CSV Trenner: Semikolon

import java.util.Arrays;

public class CSVParser {
  public static String[][] parseCSV(String csv) {
	  
	String[] rows = csv.split("\\r?\\n");
    
	String[][] table = new String[rows.length][];

    for (int i = 0; i < rows.length; i++) {
      table[i] = rows[i].split(";");
    }

    for (String[] row : table) {
      System.out.println(Arrays.toString(row));
    } 
    return table;
  }

  public static void main(String[] sample){
    String sampleCSV = sample.length == 0 ? "name;age;city\nAlice;30;Berlin\nBob;25;Paris" : sample[0];
    parseCSV(sampleCSV);
  }
}