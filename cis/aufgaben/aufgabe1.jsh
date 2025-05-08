// CSV Parser
// Einen CSV-String zu einer tabellarischen Datenstruktur parsen
// CSV Trenner: Semikolon

import java.util.Arrays;

class CSVParser {
  static String[][] parseCSV(String csv) {
	  
    // Zeilen auslesen:
	String[] rows = csv.split("\\r?\\n");
    
	// Spalten auslesen:
	String[][] table = new String[rows.length][];

    // Spalten pro Zeile auslesen:
    for (int i = 0; i < rows.length; i++) {
      table[i] = rows[i].split(";");
    }

    // Optional anzeigen:
    for (String[] row : table) {
      System.out.println(Arrays.toString(row));
    } 
    return table;
  }
}

String sampleCSV = "name;age;city\nAlice;30;Berlin\nBob;25;Paris";
CSVParser.parseCSV(sampleCSV);