/*
 * @author: pagidimarri.nagendar
 * @createdAt: 21/10/23 1:18 pm
 */

package com.nagendar.learning.formatter;

import com.nagendar.learning.lexer.tokens.DataType;
import com.nagendar.learning.lexer.tokens.Token;

import java.util.HashMap;
import java.util.Map;

public class FormatterFactory {
	private final Map<Token, Formatter> formatterMap;

	public FormatterFactory() {
		this.formatterMap = new HashMap<>();
		populateFormatters();
	}

	private void populateFormatters() {
		formatterMap.put(DataType.STRING, new StringFormatter(this));
		formatterMap.put(DataType.NUMBER, new NumberFormatter(this));
		formatterMap.put(DataType.BOOLEAN, new BooleanFormatter(this));
		formatterMap.put(DataType.ARRAY, new ArrayFormatter(this));
		formatterMap.put(DataType.OBJECT, new ObjectFormatter(this));
	}

	public Formatter getFormatterFromToken(Token type) {
		return formatterMap.get(type);
	}
}
