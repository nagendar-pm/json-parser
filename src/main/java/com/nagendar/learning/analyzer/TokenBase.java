/*
 * @author: pagidimarri.nagendar
 * @createdAt: 17/10/23 5:49 pm
 */

package com.nagendar.learning.analyzer;

import com.nagendar.learning.lexer.Lexeme;

import java.util.List;

public class TokenBase {
	private final List<Lexeme> lexemes;
	private int index;

	public TokenBase(List<Lexeme> lexemes) {
		this.lexemes = lexemes;
		this.index = 0;
	}

	public Lexeme getLexeme() {
		if (index == lexemes.size()) {
			return null;
		}
		return this.lexemes.get(index);
	}

	public boolean incrementIndex() {
		if (index == lexemes.size()) {
			return false;
		}
		this.index++;
		return true;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
