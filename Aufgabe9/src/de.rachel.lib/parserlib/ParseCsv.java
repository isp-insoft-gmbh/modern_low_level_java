package parserlib;

import java.util.Arrays;
/// The Parser Class for the isp java exercise
public class ParseCsv {
  private String[][] parsedCsv;

  /// The constructor
  public ParseCsv() {
  }

  /// This method parse a csv string.
  ///
  /// @param csvContent The Content of the CSV String
  public void parseCsv(String csvContent) throws IllegalArgumentException {

    String[] csvRows = csvContent.split(System.lineSeparator());
    parsedCsv = new String[csvRows.length][];
    for (int zeile = 0; zeile < csvRows.length; zeile++) {
      parsedCsv[zeile] = csvRows[zeile].split(";");
    }
  }

  /// Return the parsed CSV String as an HTML Table.
  ///
  /// @return A String that contains the CSV Content as an HTML Table.
  public String getHtmlTable() {
    StringBuilder htmlTable = new StringBuilder();

    htmlTable.append("<table>");
    htmlTable.append(System.lineSeparator());

    for ( int zeilenNummer = 0; zeilenNummer < parsedCsv.length; zeilenNummer++ ) {
      if (zeilenNummer == 0) {
        htmlTable.append("<thead><tr>");
          for ( String zelle : parsedCsv[zeilenNummer]) {
            htmlTable.append("<th>");
            htmlTable.append(zelle);
            htmlTable.append("</th>");
          }
        htmlTable.append("</tr></thead>");
        htmlTable.append(System.lineSeparator());
      } else {
        if (zeilenNummer == 1) { htmlTable.append("<tbody>"); }
        htmlTable.append("<tr>");
          for ( String zelle : parsedCsv[zeilenNummer]) {
            htmlTable.append("<td>");
            htmlTable.append(zelle);
            htmlTable.append("</td>");
          }
        htmlTable.append("</tr>");
        htmlTable.append(System.lineSeparator());
      }
    }

    htmlTable.append("</tbody>" + System.lineSeparator() + "</table>");

    return htmlTable.toString();
  }
  
  /// Function that return a the whole html File as a String
  ///
  /// @param title a String that represented the titel of html site
  /// @param styleSheetFile a String that define the Name of the stylesheetfile
  /// that can contain also pathinformation
  ///
  /// @return Return the a string that contain the whole html file
  public String getHtmlSite(String title, String styleSheetFile) {

    String htmlHead = """
<!DOCTYPE html>
<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">
<html lang=\"de\">
<head>
""";
    String htmlTitle = "<title>" + title + "</title>";
    String htmlStyle = "";

    if (!styleSheetFile.isEmpty()) {
      htmlStyle = "<link rel=\"stylesheet\" href=\"" + styleSheetFile + "\">";
    } else {
      htmlStyle = "";
    }

    String htmlHeadToBody = "</head><body>";
    String htmlBodyToEnd = "</body></html>";

    return htmlHead + System.lineSeparator() + htmlTitle + System.lineSeparator() + htmlStyle + System.lineSeparator() + htmlHeadToBody + System.lineSeparator() + this.getHtmlTable() + System.lineSeparator() + htmlBodyToEnd;
  }
}
