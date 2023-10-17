/*
 * @author: pagidimarri.nagendar
 * @createdAt: 17/10/23 7:24 pm
 */

package com.nagendar.learning.parser.analyzer;

import com.nagendar.learning.lexer.tokens.Brace;
import com.nagendar.learning.lexer.tokens.Comma;
import com.nagendar.learning.lexer.tokens.SquareBracket;
import com.nagendar.learning.lexer.tokens.Token;
import com.nagendar.learning.parser.TokenBase;

import java.util.HashMap;
import java.util.Map;

public class RightBracketAnalyzer implements Analyzer {
	private final Map<Token, String> nextPotentialCharacters;

	public RightBracketAnalyzer() {
		this.nextPotentialCharacters = new HashMap<>();
		setNextAnalyzer();
	}

	@Override
	public void analyze(TokenBase tokenBase) {

	}

	@Override
	public void setNextAnalyzer() {
		nextPotentialCharacters.put(Comma.COMMA, ",");
		nextPotentialCharacters.put(SquareBracket.RIGHT_SQUARE_BRACKET, "]");
		nextPotentialCharacters.put(Brace.RIGHT_BRACE, "}");
	}
}
