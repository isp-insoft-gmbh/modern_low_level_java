package cli;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import parserlib.ParseCsv;

public class Cli {
  private String outputFile;
  private String title;
  private String styleSheetFile;
  private final String csvToParse;
  private String htmlTable;

  Cli(String[] args) {
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
	  if (argsIndex < args.length -1) {
            outputFile = args[++argsIndex].trim();
	  } else {
	    System.err.println("Kein Wert zum Argument gefunden. Argumentliste ausgeschöpft!");
	  }
          break;
        case "-t":
        case "--title":
          if (argsIndex < args.length -1) {
            title = args[++argsIndex].trim();
	  } else {
	    System.err.println("Kein Wert zum Argument gefunden. Argumentliste ausgeschöpft!");
	  }
          break;
        case "-s":
        case "--stylesheet":
          if (argsIndex < args.length -1) {
            styleSheetFile = args[++argsIndex].trim();
	  } else {
	    System.err.println("Kein Wert zum Argument gefunden. Argumentliste ausgeschöpft!");
          }
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

        String htmlSiteContent = csvParser.getHtmlSite(title, styleSheetFile);

        if (outputFile.isEmpty()) {
          System.out.println(htmlSiteContent);
        } else {
          Files.writeString(Paths.get(outputFile), htmlSiteContent, StandardCharsets.UTF_8);
        }
      } catch (IOException e) {
        e.printStackTrace();
	System.exit(1);
      }
    } else {
      System.err.println("keine CSV Datei als letztes Argument angegeben!!!");
    }
  }

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
    """;
    System.out.print(manual);
  }

  public static void main(String[] args) {
    if (args.length > 8) {
      System.err.println("To many Arguments. Programm will be terminated!");
      System.exit(1);
    }

    if (args.length < 1) {
      System.err.println("To few Arguments. Give at minimum the CSV File that should be parsed");
      System.exit(1);
    }

    new Cli(args);
  }
}
