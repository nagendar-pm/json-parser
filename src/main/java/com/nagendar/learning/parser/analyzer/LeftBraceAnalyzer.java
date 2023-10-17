/*
 * @author: pagidimarri.nagendar
 * @createdAt: 17/10/23 7:18 pm
 */

package com.nagendar.learning.parser.analyzer;

import com.nagendar.learning.lexer.Lexeme;
import com.nagendar.learning.lexer.tokens.Token;
import com.nagendar.learning.parser.TokenBase;

import java.util.HashMap;
import java.util.Map;

public class LeftBraceAnalyzer implements Analyzer {
	private final Map<Token, Character> nextPotentialCharacters;
//	private final Map<Token, Analyzer>

	public LeftBraceAnalyzer() {
		this.nextPotentialCharacters = new HashMap<>();
	}

	@Override
	public void parse(TokenBase tokenBase) {

	}

	@Override
	public void setNextAnalyzer(Analyzer analyzer) {

	}
}
