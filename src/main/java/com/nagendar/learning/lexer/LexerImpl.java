/*
 * @author: pagidimarri.nagendar
 * @createdAt: 07/10/23 10:32 pm
 */

package com.nagendar.learning.lexer;

import com.nagendar.learning.lexer.tokens.*;

public class LexerImpl implements Lexer {
	private final String input;
	private int index;

	public LexerImpl(String input) {
		this.input = input;
		this.index = 0;
	}

	@Override
	public Lexeme nextToken() {
		if (index >= input.length()) {
			return null;
		}
		char c = input.charAt(index);
		StringBuilder sb = new StringBuilder();
		sb.append(c);
		index++;
		if (c == '\"') {
			// TODO: handle quotes inside string
			while (index < input.length() && input.charAt(index) != '\"') {
				sb.append(input.charAt(index));
				index++;
			}
			if (index < input.length()) {
				sb.append(input.charAt(index));
				index++;
			}
			return new Lexeme(DataType.STRING, sb.toString());
		}
		else if ((c >= '0' && c <= '9') || c == '-') {
			// TODO: think how we are representing this number with special chars
			while (index < input.length()
					&& input.charAt(index) >= '0'
					&& input.charAt(index) <= '9') {
				sb.append(input.charAt(index));
				index++;
			}
			if (index < input.length()
					&& input.charAt(index) == '.') {
				sb.append(input.charAt(index));
				index++;
			}
			while (index < input.length()
					&& input.charAt(index) >= '0'
					&& input.charAt(index) <= '9') {
				sb.append(input.charAt(index));
				index++;
			}
			if (index < input.length()
					&& (input.charAt(index) == 'e'
					|| input.charAt(index) == 'E')) {
				sb.append(input.charAt(index));
				index++;
			}
			if (index < input.length()
					&& (input.charAt(index) == '-'
					|| input.charAt(index) == '+')) {
				sb.append(input.charAt(index));
				index++;
			}
			while (index < input.length()
					&& input.charAt(index) >= '0'
					&& input.charAt(index) <= '9') {
				sb.append(input.charAt(index));
				index++;
			}
			return new Lexeme(DataType.NUMBER, sb.toString());
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
		return index < input.length();
	}
}
