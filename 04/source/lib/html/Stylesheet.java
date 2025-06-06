package html;

public interface Stylesheet {
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
