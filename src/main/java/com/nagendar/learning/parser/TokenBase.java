/*
 * @author: pagidimarri.nagendar
 * @createdAt: 17/10/23 5:49 pm
 */

package com.nagendar.learning.parser;

import com.nagendar.learning.lexer.Lexeme;

import java.util.List;
import java.util.Stack;

public class TokenBase {
	private final List<Lexeme> lexemes;
	private int index;
	private final Stack<Lexeme> symbols;

	public TokenBase(List<Lexeme> lexemes) {
		this.lexemes = lexemes;
		this.index = 0;
		this.symbols = new Stack<>();
	}

	public Lexeme getLexeme() {
		if (index == lexemes.size()) {
			return null;
		}
		return this.lexemes.get(index);
	}

//	public List<Lexeme> getLexemes() {
//		return lexemes;
//	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Stack<Lexeme> getSymbols() {
		return symbols;
	}
}
