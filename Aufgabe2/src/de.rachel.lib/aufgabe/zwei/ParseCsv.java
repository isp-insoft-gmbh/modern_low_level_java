package aufgabe.zwei;

public class ParseCsv {
  public ParseCsv() {
  }

  public String[][] parseCsv(String csvContent) throws IllegalArgumentException {

    String[] csvRows = csvContent.split(System.lineSeparator());
    String[][] parsedCsv = new String[csvRows.length][];
    for (int zeile = 0; zeile < csvRows.length; zeile++) {
      parsedCsv[zeile] = csvRows[zeile].split(";");
    }
    return parsedCsv;
  }
}
