String[][] parseCsv(String CsvContent) throws IllegalArgumentException {
    // Argument in Zeilen am Newline zerlegen
    String[] CsvRows = CsvContent.split("\n");

    int maxColumns = 0;

    // maximale Spaltenanzahl ermitteln, falls es Zeilen mit unerschiedlicher Anzahl Spalten gibt
    for (int aktuelleZeile = 0; aktuelleZeile < CsvRows.length; aktuelleZeile++) {
        int ZaehlerAktuell = 0;
        String[] RowColumnValues = CsvRows[aktuelleZeile].split(";");
        ZaehlerAktuell = RowColumnValues.length;

        // wenn letzte maximale Spaltenzahl weniger als die aktuelle ist, die neue maximal Menge merken
        if (ZaehlerAktuell > maxColumns) {
            maxColumns = ZaehlerAktuell;
        }
    }
   // jetzt ist die Größe für die Zweite Dimension des Rückgabearrays bekannt
   // die erste kommt aus der Länge des Arrays CsvRows
   String[][] parsedCsv = new String[CsvRows.length][maxColumns];

   for (int Zeile = 0; Zeile < CsvRows.length; Zeile++) {
        String[] RowColumnValues = CsvRows[Zeile].split(";");

        for (int Spalte = 0; Spalte < RowColumnValues.length; Spalte++) {
            parsedCsv[Zeile][Spalte] = RowColumnValues[Spalte];
        }
    }
    return parsedCsv;
}
