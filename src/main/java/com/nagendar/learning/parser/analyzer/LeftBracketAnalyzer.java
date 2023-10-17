/*
 * @author: pagidimarri.nagendar
 * @createdAt: 17/10/23 7:24 pm
 */

package com.nagendar.learning.parser.analyzer;

import com.nagendar.learning.lexer.tokens.Brace;
import com.nagendar.learning.lexer.tokens.DataType;
import com.nagendar.learning.lexer.tokens.SquareBracket;
import com.nagendar.learning.lexer.tokens.Token;
import com.nagendar.learning.parser.TokenBase;

import java.util.HashMap;
import java.util.Map;

public class LeftBracketAnalyzer implements Analyzer {
	private final Map<Token, String> nextPotentialCharacters;

	public LeftBracketAnalyzer() {
		this.nextPotentialCharacters = new HashMap<>();
		setNextAnalyzer();
	}

	@Override
	public void analyze(TokenBase tokenBase) {

	}

	@Override
	public void setNextAnalyzer() {
		nextPotentialCharacters.put(Brace.LEFT_BRACE, "{");
		nextPotentialCharacters.put(SquareBracket.LEFT_SQUARE_BRACKET, "[");
		nextPotentialCharacters.put(DataType.NUMBER, "[0-9]");
		nextPotentialCharacters.put(DataType.STRING, "\"");
		nextPotentialCharacters.put(DataType.BOOLEAN, "[t|f]");
	}
}
