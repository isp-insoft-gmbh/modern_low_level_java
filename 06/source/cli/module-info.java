/// Das Kommandozeilen-Interface zu diesem Projekt.
///
/// Es nimmt Daten im CSV-Format entgegen, und gibt diese als HTML
/// Konsole oder einer Datei aus.
///
/// Features:
/// - Zeigt Hilfe an
/// - Ausgabe von HTML in eine bestimmte Datei
/// - Setzen des Titels im HTML
/// - Hinzufügen von CSS-Stylesheets im HTML
///
/// @see tool.Tabbel
module cli {
	exports tool;

	requires lib;
}
