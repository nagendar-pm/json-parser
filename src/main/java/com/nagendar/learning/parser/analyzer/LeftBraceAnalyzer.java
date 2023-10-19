/*
 * @author: pagidimarri.nagendar
 * @createdAt: 17/10/23 7:18 pm
 */

package com.nagendar.learning.parser.analyzer;

import com.nagendar.learning.exception.IllegalTokenFoundException;
import com.nagendar.learning.lexer.Input;
import com.nagendar.learning.lexer.Lexeme;
import com.nagendar.learning.lexer.tokens.Brace;
import com.nagendar.learning.lexer.tokens.DataType;
import com.nagendar.learning.parser.TokenBase;

import java.util.Objects;

public class LeftBraceAnalyzer extends Analyzer {
	public LeftBraceAnalyzer(Input input, AnalyzerFactory analyzerFactory) {
		super(input, analyzerFactory);
		setNextAnalyzer();
	}

	@Override
	public void analyze(TokenBase tokenBase) {
		// This is the top most lexeme to be visited
		// so we need to validate it which unlikely happens in other's case
		Lexeme currentLexeme = tokenBase.getLexeme();
		if (currentLexeme.getTokenType() != Brace.LEFT_BRACE) {
			throw new IllegalTokenFoundException(String.format("Expected {, Found %s",
					currentLexeme.getValue()));
		}
		boolean isIncremented = tokenBase.incrementIndex();
		Lexeme nextLexeme = tokenBase.getLexeme();
		if (!isIncremented || Objects.isNull(nextLexeme)) {
			super.throwAbruptEndException();
		}
		if (!nextPotentialCharacters.containsKey(nextLexeme.getTokenType())) {
			super.throwUnexpectedCharacterException(nextLexeme);
		}
		analyzerFactory.getAnalyzerForToken(nextLexeme.getTokenType()).analyze(tokenBase);
	}

	@Override
	public void setNextAnalyzer() {
		nextPotentialCharacters.put(DataType.STRING, "\"");
	}
}
