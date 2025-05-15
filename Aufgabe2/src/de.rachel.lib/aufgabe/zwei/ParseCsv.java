package aufgabe.zwei;

public class ParseCsv {
    public ParseCsv() {}
    
    public String[][] parseCsv(String csvContent) throws IllegalArgumentException {
    // Argument in Zeilen am Newline zerlegen
    String[] csvRows = csvContent.split(System.lineSeparator());

    int maxColumns = 0;

    // maximale Spaltenanzahl ermitteln, falls es Zeilen mit unerschiedlicher Anzahl Spalten gibt
    for (int aktuelleZeile = 0; aktuelleZeile < csvRows.length; aktuelleZeile++) {
        int zaehlerAktuell = 0;
        String[] rowColumnValues = csvRows[aktuelleZeile].split(";");
        zaehlerAktuell = rowColumnValues.length;

        // wenn letzte maximale Spaltenzahl weniger als die aktuelle ist, die neue maximal Menge merken
        if (zaehlerAktuell > maxColumns) {
            maxColumns = zaehlerAktuell;
        }
    }
   // jetzt ist die Größe für die Zweite Dimension des Rückgabearrays bekannt
   // die erste kommt aus der Länge des Arrays csvRows
   String[][] parsedCsv = new String[csvRows.length][maxColumns];

   for (int zeile = 0; zeile < csvRows.length; zeile++) {
        String[] rowColumnValues = csvRows[zeile].split(";");

        for (int spalte = 0; spalte < rowColumnValues.length; spalte++) {
            parsedCsv[zeile][spalte] = rowColumnValues[spalte];
        }
    }
    return parsedCsv;
    }
}
