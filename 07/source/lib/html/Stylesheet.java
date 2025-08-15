package html;

/// CSS-Klassen für HTML-Elemente.
public interface Stylesheet {
	/// CSS-Klasse für eine HTML-Tabelle, die schick ist.
	/// Tabelle nimmt die gesamte Breite ein, hat einen dünnen Rahmen und Spaltentitel
	/// haben einen dezenten Hintergrund.
	/// Zellenhintergründe wechseln alternierend ihre Farbe, damit Tabellen leicht
	/// mit den Augen gescannt werden können.
	String FANCY_TABLE_STYLE = """
	table {
		width: 100%;
		border: 1px solid black
	}
	th {
		background-color: #eee;
	}
	tr {
		border-bottom: 1px solid #eee;
	}
	table caption {
		font-size: x-large;
		font-family: fantasy;
	}
	tr:nth-child(even) {
		background-color: rgba(150,212,212,0.4);
	}
	th:nth-child(even),td:nth-child(even) {
		background-color: rgba(150,212,212,0.4);
	}
	""";
}
