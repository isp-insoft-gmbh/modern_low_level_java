package cli;

import java.io.IOException;
import parserlib.ParseCsv;

/// convert CSV files to HTML tables
/// @param input_file path and name of File to converted
/// @param -h print manual
/// @param --help print manual
/// @param -o Specify the output HTML file. If not provided, output is written to standard output
/// @param --output Specify the output HTML file. If not provided, output is written to standard output
/// @param -t Set the title of the HTML document. Default is "CSV to HTML Table"
/// @param --title Set the title of the HTML document. Default is "CSV to HTML Table"
/// @param -s Link to an external CSS stylesheet for styling the table
/// @param --stylesheet Link to an external CSS stylesheet for styling the table
/// @returns a HTML Document that contains the CSV Data in a Table
public class cli {
  private String outputFile;
  private String title;
  private String styleSheetFile;
  private final String csvToParse;
  private String htmlTable;

  cli(String[] args) {
    outputFile = "";
    title = "CSV to HTML Table";
    styleSheetFile = "";

    // the last element is the csv file to parse
    // it can be ignored here
    for (int argsIndex = 0; argsIndex < args.length; argsIndex++) {
      switch (args[argsIndex]) {
        case "-h":
        case "--help":
          this.printManualPage();
          System.exit(0);
          break;
        case "-o":
        case "--output":
          outputFile = args[argsIndex + 1].trim();
          break;
        case "-t":
        case "--title":
          title = args[argsIndex + 1].trim();
          break;
        case "-s":
        case "--stylesheet":
          styleSheetFile = args[argsIndex + 1].trim();
          break;
        default:
          break;
      }
    }

    csvToParse = args[args.length - 1];
    
    if (csvToParse.endsWith("csv") || csvToParse.endsWith("CSV")) {
      final var csvFilePath = java.nio.file.Paths.get(csvToParse);
      
      try {
        final var csvContent = java.nio.file.Files.readString(csvFilePath);
        ParseCsv csvParser = new ParseCsv();
        csvParser.parseCsv(csvContent);

	String htmlSite = csvParser.getHtmlSite(title, styleSheetFile);
        
	if (outputFile.isEmpty()) {
	  System.out.println(htmlSite);
	} else {
	
	}
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } else {
      System.out.println("keine CSV Datei als letztes Argument angegeben!!!");
    }
  }

  /// Print the Helpfile
  private void printManualPage() {
    final var manual = """
CLI(1) Manual Page

NAME
cli - convert CSV files to HTML tables

SYNOPSIS
cli [options] input_file

DESCRIPTION
The cli command reads a CSV (Comma-Separated Values) file and outputs an HTML table representation of its contents.
OPTIONS

-h, --help Display this help message and exit.

-o, --output Specify the output HTML file. If not provided, output is written to standard output.

-t, --title Set the title of the HTML document. Default is "CSV to HTML Table".

-s, --stylesheet Link to an external CSS stylesheet for styling the table.

EXAMPLES

Convert data.csv to an HTML table and write to standard output:

cli data.csv

Convert data.csv to table.html with a custom title:

cli -o table.html -t "My Data Table" data.csv

Convert data.csv to table.html with an external stylesheet:

cli -o table.html -s styles.css data.csv
""";
        System.out.print(manual);
  }

  public static void main(String[] args) {
    if (args.length > 8) {
      System.out.println("To many Arguments. Programm will be terminated!");
      System.exit(1);
    }

    if (args.length < 1) {
      System.out.println("To few Arguments. Give at minimum the CSV File that should be parsed");
      System.exit(1);
    }

    new cli(args);
  }
}
