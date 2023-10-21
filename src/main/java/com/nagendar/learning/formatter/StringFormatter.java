/*
 * @author: pagidimarri.nagendar
 * @createdAt: 21/10/23 1:13 pm
 */

package com.nagendar.learning.formatter;

import com.nagendar.learning.lexer.Lexeme;

public class StringFormatter implements Formatter {
	private final FormatterFactory formatterFactory;

	public StringFormatter(FormatterFactory formatterFactory) {
		this.formatterFactory = formatterFactory;
	}

	@Override
	public String format(Lexeme lexeme, int depth) {
		return lexeme.getValue().toString();
	}
}
