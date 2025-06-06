package parserlib;

import java.util.Arrays;

public class ParseCsv {
  private String[][] parsedCsv;
   
  public ParseCsv() {
  }

  public void parseCsv(String csvContent) throws IllegalArgumentException {

    String[] csvRows = csvContent.split(System.lineSeparator());
    parsedCsv = new String[csvRows.length][];
    for (int zeile = 0; zeile < csvRows.length; zeile++) {
      parsedCsv[zeile] = csvRows[zeile].split(";");
    }
  }
  
  public String getHtmlTable() {
    StringBuilder htmlTable = new StringBuilder();
    
    htmlTable.append("<table>");
    
    for ( int zeilenNummer = 0; zeilenNummer < parsedCsv.length; zeilenNummer++ ) {
      if (zeilenNummer == 0) {
        htmlTable.append("<thead><tr>");
          for ( String zelle : parsedCsv[zeilenNummer]) {
            htmlTable.append("<th>");
            htmlTable.append(zelle);
            htmlTable.append("</th>");
          }
        htmlTable.append("</tr></thead>");
      } else {
        if (zeilenNummer == 1) { htmlTable.append("<tbody>"); }
        htmlTable.append("<tr>");
          for ( String zelle : parsedCsv[zeilenNummer]) {
            htmlTable.append("<td>");
            htmlTable.append(zelle);
            htmlTable.append("</td>");
          }
        htmlTable.append("</tr>");
      }
    }
    
    htmlTable.append("</tbody></table>");
    
    return htmlTable.toString();
  }
}
