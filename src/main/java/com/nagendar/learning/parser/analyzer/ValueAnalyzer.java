/*
 * @author: pagidimarri.nagendar
 * @createdAt: 17/10/23 7:22 pm
 */

package com.nagendar.learning.parser.analyzer;

import com.nagendar.learning.lexer.Lexeme;
import com.nagendar.learning.lexer.tokens.*;
import com.nagendar.learning.parser.TokenBase;

import java.util.HashMap;
import java.util.Map;

public class ValueAnalyzer implements Analyzer {
	private final Map<Token, String> nextPotentialCharacters;
	private final Analyzer stringAnalyzer;

	public ValueAnalyzer() {
		this.nextPotentialCharacters = new HashMap<>();
		this.stringAnalyzer = new StringAnalyzer();
		setNextAnalyzer();
	}

	@Override
	public void analyze(TokenBase tokenBase) {
		Lexeme currentLexeme = tokenBase.getLexeme();
		if (currentLexeme.getTokenType() == DataType.STRING) {
			stringAnalyzer.analyze(tokenBase);
		}
		else {

		}
	}

	@Override
	public void setNextAnalyzer() {
		nextPotentialCharacters.put(Comma.COMMA, ",");
		nextPotentialCharacters.put(SquareBracket.RIGHT_SQUARE_BRACKET, "]");
		nextPotentialCharacters.put(Brace.RIGHT_BRACE, "}");
	}
}
