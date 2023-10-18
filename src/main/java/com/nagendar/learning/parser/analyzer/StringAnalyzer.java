/*
 * @author: pagidimarri.nagendar
 * @createdAt: 18/10/23 3:44 pm
 */

package com.nagendar.learning.parser.analyzer;

import com.nagendar.learning.lexer.Lexeme;
import com.nagendar.learning.lexer.tokens.*;
import com.nagendar.learning.parser.TokenBase;

import java.util.HashMap;
import java.util.Map;

public class StringAnalyzer implements Analyzer {
	private final Map<Token, String> nextPotentialCharacters;

	public StringAnalyzer() {
		this.nextPotentialCharacters = new HashMap<>();
		setNextAnalyzer();
	}

	@Override
	public void analyze(TokenBase tokenBase) {
		Lexeme currentLexeme = tokenBase.getLexeme();
		Lexeme nextLexeme = tokenBase.getLexeme();
	}

	@Override
	public void setNextAnalyzer() {
		nextPotentialCharacters.put(Colon.COLON, ":");
		nextPotentialCharacters.put(Comma.COMMA, ",");
		nextPotentialCharacters.put(SquareBracket.RIGHT_SQUARE_BRACKET, "]");
		nextPotentialCharacters.put(Brace.RIGHT_BRACE, "}");
	}
}
