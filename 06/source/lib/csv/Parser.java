package csv;

/// Ein _sehr_ simpler CSV-Parser!
/// Dieser Parser nimmt einen String entgegen, und liefert eine Matrix von
/// `[Spalte X Reihe]` zurück.
///
/// @see #parseCsv(String)
public interface Parser {

	/// Parst einen String zu einem 2-dimensionalen Array von Strings.
	/// Die Anordnung der Matrix is `[Spalte X Reihe]`.
	///
	/// ## Beispiel
	/// Zum Beispiel, folgende CSV ergibt eine solche Matrix.
	/// ```csv
	/// name, alter, hobby
	/// oli, alt, tippen
	/// marcel, 25, cs-go
	/// christian, ??, japan
	/// ```
	/// |       |        |        |           |
	/// |-------|--------|--------|-----------|
	/// | name  | oli    | marcel | christian |
	/// | alter | alt    | 25     | ??        |
	/// | hobby | tippen | cs-go  | japan     |
	///
	/// Beachte, dass diese Methode einen String entgegen nimmt, und eine volle
	/// Datenstruktur zurückliefert. Das bedeutet, dass __mindestens__ das _doppelte_
	/// der Datenemenge gleichzeitig in den Speicher passen muss, damit diese Methode
	/// keinen OOM produziert.
	///
	/// ## CSV-Format
	///
	/// Diese Methode unterstützt genau eine Variation des CSV-Formats:
	/// - Das Zelltrennzeichen muss `;` (Semicolon) sein
	/// - Newline aller Betriebssystem wird unterstützt (`\n` und `\r\n`)
	/// - Quoting wird __nicht__ unterstützt
	///
	/// @param input CSV-String
	/// @return Eine Spalten-Reihen-Matrix mit den CSV-Daten.
	static String[][] parseCsv( String input ) throws IllegalArgumentException {
			if( input.isEmpty() ) return new String[0][0];
			final char cellDelimiter = ';';
			final char lineDelimiter = '\n';
			int row = 0;
			int column = 0;
			int maxColumn = 1;
			int maxRow = 0;
			final char[] inputChars = input.toCharArray();
			for ( char c : inputChars ) {
					if ( maxRow == 0 && c == cellDelimiter ) maxColumn++;
					if ( c == lineDelimiter ) maxRow++;
			} 
			final var buffer = new String[maxColumn][maxRow];
			final var cellBuffer = new StringBuilder();
			for ( char c : inputChars ) {
					switch (c) {
							case cellDelimiter -> { 
								buffer[column][row] = cellBuffer.toString().strip();
								cellBuffer.delete(0,cellBuffer.length());
								column++;
							}
							case lineDelimiter -> {
								buffer[column][row] = cellBuffer.toString().strip();
								cellBuffer.delete(0, cellBuffer.length());
								column = 0;
								row++;
							}
							case '\r' -> {/* skip. this handles win32 newlines */}
							default -> cellBuffer.append(c);
					}
					if( column > maxColumn ) throw new IllegalArgumentException("Maximum number of columns %d, as defined in header, exceeded in row %d!".formatted(maxColumn, row));
			}
			return buffer;
	}
}
