package html;

public interface Renderer {

	static String asHtmlTable(
		final String[][] matrix,
		final String caption,
		final boolean pretty
	){
		final var nl = pretty ? System.lineSeparator() : "";
		final var html = new StringBuilder();
		html.append("<table>").append(nl);
		html.append("<caption>").append(escape(caption)).append("</caption>").append(nl);
		for(int col = 0; col < matrix.length; ++col) {
			html.append("<tr>").append(nl);
			for(int row = 0; row < matrix[col].length; row++) {
				final var cell = matrix[col][row];
				final var cellElement = row == 0 ? "th" : "td";
				html.append('<').append(cellElement).append('>').append(nl);
				html.append(escape(cell)).append(nl);
				html.append("</").append(cellElement).append('>').append(nl);
			}
			html.append("</tr>").append(nl);
		}
		html.append("</table>");
		return html.toString();
	}

	static String escape(final String input) {
			if (input == null) {
						return null;
				}
				return input.replace("&", "&amp;")
									.replace("<", "&lt;")
									.replace(">", "&gt;")
									.replace("\"", "&quot;")
									.replace("'", "&#39;");
		}
}
