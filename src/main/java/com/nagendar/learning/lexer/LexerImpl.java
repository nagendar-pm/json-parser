/*
 * @author: pagidimarri.nagendar
 * @createdAt: 07/10/23 10:32 pm
 */

package com.nagendar.learning.lexer;

import com.nagendar.learning.constants.Constants;
import com.nagendar.learning.lexer.tokenizer.TokenizerFactory;
import com.nagendar.learning.lexer.tokens.*;

public class LexerImpl implements Lexer {
	private final String inputString;
	private int index;
	private final Input input;
	private final TokenizerFactory tokenizerFactory;

	public LexerImpl(String inputString, Input input, TokenizerFactory tokenizerFactory) {
		this.inputString = inputString;
		this.tokenizerFactory = tokenizerFactory;
		this.index = 0;
		this.input = input;
	}

	@Override
	public Lexeme nextToken() {
		if (index >= inputString.length()) {
			return null;
		}
		char c = inputString.charAt(index);
		StringBuilder sb = new StringBuilder();
		sb.append(c);
		index++;
		if (c == '\"') {
			// TODO: handle quotes inside string
			while (index < inputString.length() && inputString.charAt(index) != '\"') {
				sb.append(inputString.charAt(index));
				index++;
			}
			if (index < inputString.length()) {
				sb.append(inputString.charAt(index));
				index++;
			}
			return new Lexeme(DataType.STRING, sb.toString());
		}
		else if (c == 't' || c == 'f') {
			input.setIndex(index);
			Lexeme lexeme = tokenizerFactory.getTokenizer(Constants.BOOLEAN_TOKENIZER)
					.getToken(input);
			index = input.getIndex();
			return lexeme;
		}
		else if ((c >= '0' && c <= '9') || c == '-') {
			input.setIndex(index);
			Lexeme lexeme = tokenizerFactory.getTokenizer(Constants.NUMBER_TOKENIZER)
					.getToken(input);
			index = input.getIndex();
			return lexeme;
		}
		else if (c == '{' || c == '}' || c == '[' || c == ']' || c == ':' || c == ',') {
			input.setIndex(index);
			Lexeme lexeme = tokenizerFactory.getTokenizer(Constants.SEPARATOR_TOKENIZER)
					.getToken(input);
			index = input.getIndex();
			return lexeme;
		}
		else if (c == '\n' || c == '\t' || c == ' ') {
			return new Lexeme(DataType.WHITE_SPACE, "");
		}
		return new Lexeme(DataType.NULL, null);
	}

	@Override
	public boolean hasToken() {
		return index < inputString.length();
	}
}
