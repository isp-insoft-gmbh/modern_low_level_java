/open PRINTING

final var testCsv = """
name; alter; insanity; profanity; yoyo
oli; 88; 1000; -9; ok
macel; 8; 900; 0; alive
katze; 9999; 9999; 9999; god
""";

final var weatherStations = java.

String[][] parseCsv( String input ) throws IllegalArgumentException {
    if( input.isEmpty() ) return new String[0][0];
    final char cellDelimiter = ';';
    final char lineDelimiter = '\n';
    int row = 0;
    int column = 0;
    int maxRow = 1;
    int maxColumn = 1;
    final char[] inputChars = input.toCharArray();
    for ( char c : inputChars ) {
        if ( maxRow == 1 && c == cellDelimiter ) maxColumn++;
        if ( c == lineDelimiter ) maxRow++;   
    } 
    // println("creating buffer %sx%s (column X row)".formatted(maxColumn, maxRow));
    final var buffer = new String[maxColumn][maxRow];
    final var cellBuffer = new StringBuilder();
    for ( char c : inputChars ) {
        switch (c) {
            case cellDelimiter -> { 
              // println("new column found, adding cell to %sx%s (column X row)".formatted(column, row));
              buffer[column][row] = cellBuffer.toString().strip();
              cellBuffer.delete(0,cellBuffer.length());
              column++;
            }
            case lineDelimiter -> {
              // println("new row found, adding cell to %sx%s (column X row)".formatted(column, row));
              buffer[column][row] = cellBuffer.toString().strip();
              cellBuffer.delete(0, cellBuffer.length());
              column = 0;
              row++;
            }
            case '\r' -> {/* skip. this handles win32 newlines */}
            default -> cellBuffer.append(c);
        }
    }
    return buffer;
}

parseCsv(testCsv)
