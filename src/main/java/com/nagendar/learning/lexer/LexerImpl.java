/*
 * @author: pagidimarri.nagendar
 * @createdAt: 07/10/23 10:32 pm
 */

package com.nagendar.learning.lexer;

import com.nagendar.learning.lexer.tokenizer.NumberTokenizer;
import com.nagendar.learning.lexer.tokens.*;

public class LexerImpl implements Lexer {
	private final String inputString;
	private int index;
	private Input input;
	private NumberTokenizer numberTokenizer;

	public LexerImpl(String inputString, Input input) {
		this.inputString = inputString;
		this.index = 0;
		this.input = input;
		this.numberTokenizer = new NumberTokenizer(new StringBuilder());
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
		else if (c == 't') {
			// check for true here
			String trueString = "true";
			int matchIndex = 1;
			while (index < inputString.length()
					&& matchIndex < trueString.length()
					&& inputString.charAt(index) == trueString.charAt(matchIndex)) {
				sb.append(inputString.charAt(index));
				index++;
				matchIndex++;
			}
			if (matchIndex >= trueString.length()) {
				return new Lexeme(DataType.BOOLEAN, sb.toString());
			}
			return new Lexeme(DataType.NULL, null);
			// throw an exception here
		}
		else if (c == 'f') {
			// check for false
			String falseString = "false";
			int matchIndex = 1;
			while (index < inputString.length()
					&& matchIndex < falseString.length()
					&& inputString.charAt(index) == falseString.charAt(matchIndex)) {
				sb.append(inputString.charAt(index));
				index++;
				matchIndex++;
			}
			if (matchIndex >= falseString.length()) {
				return new Lexeme(DataType.BOOLEAN, sb.toString());
			}
			return new Lexeme(DataType.NULL, null);
			// throw an exception here
		}
		else if ((c >= '0' && c <= '9') || c == '-') {
			// TODO: think how we are representing this number with special chars
			input.setIndex(index);
			Lexeme lexeme = numberTokenizer.getToken(input);
			index = input.getIndex();
			return lexeme;
		}
		else if (c == '{' || c == '}' || c == '[' || c == ']' || c == ':' || c == ',') {
			String operator = sb.toString();
			Lexeme lexeme;
			if (c == '{') {
				lexeme = new Lexeme(Brace.LEFT_BRACE, operator);
			}
			else if (c == '}') {
				lexeme = new Lexeme(Brace.RIGHT_BRACE, operator);
			}
			else if (c == '[') {
				lexeme = new Lexeme(SquareBracket.LEFT_SQUARE_BRACKET, operator);
			}
			else if (c == ']') {
				lexeme = new Lexeme(SquareBracket.RIGHT_SQUARE_BRACKET, operator);
			}
			else if (c == ':') {
				lexeme = new Lexeme(Colon.COLON, operator);
			}
			else {
				lexeme = new Lexeme(Comma.COMMA, operator);
			}
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
