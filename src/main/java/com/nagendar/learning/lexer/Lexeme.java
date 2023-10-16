/*
 * @author: pagidimarri.nagendar
 * @createdAt: 08/10/23 7:31 pm
 */

package com.nagendar.learning.lexer;

import com.nagendar.learning.lexer.tokens.Token;

public class Lexeme {
	private Token tokenType;
	private Object value;
	private int start, end;

	public Lexeme(Token tokenType, Object value) {
		this.tokenType = tokenType;
		this.value = value;
		this.start = -1;
		this.end = -1;
	}

	public Lexeme(Token tokenType, Object value, int start, int end) {
		this.tokenType = tokenType;
		this.value = value;
		this.start = start;
		this.end = end;
	}

	public Token getTokenType() {
		return tokenType;
	}

	public void setTokenType(Token tokenType) {
		this.tokenType = tokenType;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "Lexeme{" +
				"tokenType=" + tokenType +
				", value=" + value +
				", start=" + start +
				", end=" + end +
				'}';
	}
}
