/open PRINTING

final var testCsv = """
name; alter; insanity; profanity; yoyo
oli; 88; 1000; -9; ok
macel; 8; 900; 0; alive
katze; 9999; 9999; 9999; god
""";

final var weatherStationsPath = java.nio.file.Paths.get("weather_stations.csv");
final var weatherStations = java.nio.file.Files.readString(weatherStationsPath);

String[][] parseCsv( String input ) throws IllegalArgumentException {
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

parseCsv(testCsv)
