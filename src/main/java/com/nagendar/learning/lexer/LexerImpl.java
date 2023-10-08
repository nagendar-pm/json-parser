/*
 * @author: pagidimarri.nagendar
 * @createdAt: 07/10/23 10:32 pm
 */

package com.nagendar.learning.lexer;

public class LexerImpl implements Lexer {
	private final String input;
	private int index;

	public LexerImpl(String input) {
		this.input = input;
		this.index = 0;
	}

	@Override
	public Object nextToken() {
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
			return sb.toString();
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
			return sb.toString();
		}
		else if (c == '{' || c == '}' || c == '[' || c == ']' || c == ':' || c == ',') {
			return sb.toString();
		}
		else if (c == '\n' || c == '\t' || c == ' ') {
			return "WHITE_SPACE";
		}
		return null;
	}

	@Override
	public boolean hasToken() {
		return index < input.length();
	}
}
