/*
 * @author: pagidimarri.nagendar
 * @createdAt: 17/10/23 7:25 pm
 */

package com.nagendar.learning.parser.analyzer;

import com.nagendar.learning.lexer.tokens.Brace;
import com.nagendar.learning.lexer.tokens.Comma;
import com.nagendar.learning.lexer.tokens.SquareBracket;
import com.nagendar.learning.lexer.tokens.Token;
import com.nagendar.learning.parser.TokenBase;

import java.util.HashMap;
import java.util.Map;

public class RightBraceAnalyzer implements Analyzer {
	private final Map<Token, String> nextPotentialCharacters;

	public RightBraceAnalyzer() {
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
