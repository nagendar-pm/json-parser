/*
 * @author: pagidimarri.nagendar
 * @createdAt: 21/10/23 1:15 pm
 */

package com.nagendar.learning.formatter;

import com.nagendar.learning.lexer.Lexeme;
import com.nagendar.learning.lexer.tokens.Token;

import java.util.List;

public class ArrayFormatter implements Formatter {
	private final FormatterFactory formatterFactory;

	public ArrayFormatter(FormatterFactory formatterFactory) {
		this.formatterFactory = formatterFactory;
	}

	@Override
	public String format(Lexeme lexeme, int depth) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append('[');
		List<Lexeme> list = (List<Lexeme>) lexeme.getValue();
		int lengthOfArrayString = lexeme.getEnd() - lexeme.getStart();
		boolean inSeparateLine = lengthOfArrayString > 50;
		int size = list.size(), index = 0;
		for (Lexeme element : list) {
			index++;
			Token tokenType = element.getTokenType();
			Formatter formatter = formatterFactory.getFormatterFromToken(tokenType);
			String elementAsString = formatter.format(element, depth+1);
			if (inSeparateLine) {
				stringBuilder.append("\n").append("\t".repeat(depth));
			}
			stringBuilder.append(elementAsString);
			if (index <= size-1) {
				stringBuilder.append(", ");
			}
		}
		if (inSeparateLine) {
			stringBuilder.append("\n").append("\t".repeat(depth-1));
		}
		stringBuilder.append(']');
		return stringBuilder.toString();
	}
}
