/*
 * @author: pagidimarri.nagendar
 * @createdAt: 16/10/23 5:31 pm
 */

package com.nagendar.learning.lexer.tokenizer;

import com.nagendar.learning.lexer.Input;
import com.nagendar.learning.lexer.Lexeme;
import com.nagendar.learning.lexer.tokens.DataType;

public class StringTokenizer implements Tokenizer {
	private final StringBuilder stringBuilder;

	public StringTokenizer(StringBuilder stringBuilder) {
		this.stringBuilder = stringBuilder;
	}
	
	@Override
	public Lexeme getToken(Input input) {
		// TODO: handle quotes inside string
		stringBuilder.setLength(0);
		int start = input.getIndex();
		int index = start;
		String inputString = input.getInput();

		do {
			stringBuilder.append(inputString.charAt(index));
			index++;
		} while (index < inputString.length() && inputString.charAt(index) != '\"');
		if (index < inputString.length()) {
			stringBuilder.append(inputString.charAt(index));
			index++;
		}
		input.setIndex(index);
		return new Lexeme(DataType.STRING, stringBuilder.toString(), start, index-1);
	}
}
