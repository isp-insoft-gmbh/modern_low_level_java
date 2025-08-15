package tool;

import java.nio.file.*;
import java.io.*;

/// Tabbel ist ein Program, um CSV-Daten zu HTML zu wandeln, in der Konsole.
interface Tabbel {

	static void main( final String[] command_line_arguments) {

		// diese Werte können aus den Kommandozeilenparametern kommen
		Path outputPath = null; //optional
		String title = null; //optional
		Path extraStylesheet = null; //optional
		Path inputPath = null; //required

		if (command_line_arguments.length == 0) {
			System.err.println("Es wurden keine Argumente übergeben. Mindestens ein Pfad zu einer CSV-Datei muss gegeben werden.");
			printHelp();
			panic();
		}

		boolean skip = false;
		for (int index = 0; index < command_line_arguments.length; ++index) {
			if(skip) { // Das Argument wurde bereits geparst
				skip = false;
				continue;
			}
			final var argument = command_line_arguments[index];
			if("-h".equals(argument) || "--help".equals(argument)) {
				printHelp();
				exit();
			}
			if("-o".equals(argument) || "--output".equals(argument)) {
				if(command_line_arguments.length <= index + 1) {
					System.err.println("Die Option output benötigt ein Argument!");
					printHelp();
					panic();
				} else {
					outputPath = Path.of(command_line_arguments[index + 1]);
					skip = true;
				}
			}
			if("-t".equals(argument) || "--title".equals(argument)) {
				if(command_line_arguments.length <= index + 1) {
					System.err.println("Die Option title benötigt ein Argument!");
					printHelp();
					panic();
				} else {
					title = command_line_arguments[index + 1];
					skip = true;
				}
			}
			if("-s".equals(argument) || "--stylesheet".equals(argument)) {
				if(command_line_arguments.length <= index + 1) {
					System.err.println("Die Option stylesheet benötigt ein Argument!");
					printHelp();
					panic();
				} else {
					extraStylesheet = Path.of(command_line_arguments[index + 1]);
					skip = true;
				}
			}

			// ignoriere argumente bis auf das erste
			if (inputPath != null) {
				skip = true;
			} else {
				inputPath = Path.of(argument);
			}
		}

		try {
			final var csvData = Files.readString(inputPath);
			final var matrix = csv.Parser.parseCsv(csvData);
			final var caption = title == null ? "" : title;
			final var htmlData = html.Renderer.asHtmlTable(matrix, caption, true);
			//FIXME(okr): add stylesheet support
			if (outputPath == null) {
				System.out.println(htmlData);
			} else {
				Files.writeString(outputPath, htmlData);
			}
		} catch (final IllegalArgumentException | IOException exception) {
			System.err.println("CSV-Daten konnten nicht geparst werden!");
			exception.printStackTrace();
			panic();
		}
	}

	static void printHelp(){

		System.out.println("""
			Usage: tabbel [options] input_file

			Options:
			- `-h, --help`
				Display this help message and exit.

			- `-o, --output`
				Specify the output HTML file. If not provided, output is written to standard output.

			- `-t, --title`
				Set the title of the HTML document. Default is "CSV to HTML Table".

			- `-s, --stylesheet`
				Link to an external CSS stylesheet for styling the table.
			""");
	}

	static void exit() {
		System.exit(0);
	}

	static void panic(){
		System.exit(-1);
	}
}
