/*
 * @author: pagidimarri.nagendar
 * @createdAt: 17/10/23 7:21 pm
 */

package com.nagendar.learning.parser.analyzer;

import com.nagendar.learning.lexer.tokens.Colon;
import com.nagendar.learning.lexer.tokens.Token;
import com.nagendar.learning.parser.TokenBase;

import java.util.HashMap;
import java.util.Map;

public class KeyStringAnalyzer implements Analyzer {
	private final Map<Token, String> nextPotentialCharacters;

	public KeyStringAnalyzer() {
		this.nextPotentialCharacters = new HashMap<>();
		setNextAnalyzer();
	}

	@Override
	public void analyze(TokenBase tokenBase) {

	}

	@Override
	public void setNextAnalyzer() {
		nextPotentialCharacters.put(Colon.COLON, ":");
	}
}
