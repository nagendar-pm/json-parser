/*
 * @author: pagidimarri.nagendar
 * @createdAt: 08/10/23 7:31 pm
 */

package com.nagendar.learning.lexer;

import com.nagendar.learning.lexer.tokens.Token;

public class Lexeme {
	private Token tokenType;
	private Object value;

	public Lexeme(Token tokenType, Object value) {
		this.tokenType = tokenType;
		this.value = value;
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

	@Override
	public String toString() {
		return "Lexeme {" +
				"tokenType=" + tokenType +
				", value=" + value +
				'}';
	}
}
