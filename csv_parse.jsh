var rawData = Files.readString(Paths.get("data.csv"));
String[][] parseCsv( String input ) throws IllegalArgumentException
{
    final var lines = input.split("\r?\n");
    final String[][] table = new String[lines.length][];
    for (int i = 0; i < lines.length; i++) {
        final var line = lines[i];
        table[i] = line.split(",");
    }
    return table;
}
parseCsv(rawData)