/*
 * @author: pagidimarri.nagendar
 * @createdAt: 21/10/23 1:26 pm
 */

package com.nagendar.learning.formatter;

import com.nagendar.learning.lexer.Lexeme;
import com.nagendar.learning.lexer.tokens.Token;

import java.util.Map;

public class ObjectFormatter implements Formatter {
	private final FormatterFactory formatterFactory;

	public ObjectFormatter(FormatterFactory formatterFactory) {
		this.formatterFactory = formatterFactory;
	}
	@Override
	public String format(Lexeme lexeme, int depth) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append('{');
		Map<String, Lexeme> map = (Map<String, Lexeme>) lexeme.getValue();
		int size = map.size(), index = 0;
		for (Map.Entry<String, Lexeme> entry : map.entrySet()) {
			String key = entry.getKey();
			Lexeme value = entry.getValue();
			Token tokenType = value.getTokenType();
			Formatter formatter = formatterFactory.getFormatterFromToken(tokenType);
			String valueAsString = formatter.format(value, depth+1);
			index++;
			stringBuilder.append("\n")
					.append("\t".repeat(depth))
					.append(key)
					.append(" : ")
					.append(valueAsString);
			if (index <= size-1) {
				stringBuilder.append(", ");
			}
		}
		stringBuilder.append("\n").append("\t".repeat(depth-1)).append('}');
		return stringBuilder.toString();
	}
}
