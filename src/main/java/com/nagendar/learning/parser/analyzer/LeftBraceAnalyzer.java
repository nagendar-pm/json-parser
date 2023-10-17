/*
 * @author: pagidimarri.nagendar
 * @createdAt: 17/10/23 7:18 pm
 */

package com.nagendar.learning.parser.analyzer;

import com.nagendar.learning.lexer.tokens.DataType;
import com.nagendar.learning.lexer.tokens.Token;
import com.nagendar.learning.parser.TokenBase;

import java.util.HashMap;
import java.util.Map;

public class LeftBraceAnalyzer implements Analyzer {
	private final Map<Token, String> nextPotentialCharacters;

	public LeftBraceAnalyzer() {
		this.nextPotentialCharacters = new HashMap<>();
		setNextAnalyzer();
	}

	@Override
	public void analyze(TokenBase tokenBase) {

	}

	@Override
	public void setNextAnalyzer() {
		nextPotentialCharacters.put(DataType.STRING, "\"");
	}
}
