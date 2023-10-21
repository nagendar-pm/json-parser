/*
 * @author: pagidimarri.nagendar
 * @createdAt: 17/10/23 7:23 pm
 */

package com.nagendar.learning.analyzer;

import com.nagendar.learning.exception.IllegalTokenFoundException;
import com.nagendar.learning.lexer.Input;
import com.nagendar.learning.lexer.Lexeme;
import com.nagendar.learning.lexer.tokens.Brace;
import com.nagendar.learning.lexer.tokens.Comma;
import com.nagendar.learning.lexer.tokens.DataType;
import com.nagendar.learning.lexer.tokens.SquareBracket;

import java.util.Objects;

public class CommaAnalyzer extends Analyzer {
	public CommaAnalyzer(Input input, AnalyzerFactory analyzerFactory) {
		super(input, analyzerFactory);
		setNextAnalyzer();
	}

	@Override
	public void analyze(TokenBase tokenBase) {
		Lexeme currentLexeme = tokenBase.getLexeme();
		if (currentLexeme.getTokenType() != Comma.COMMA) {
			throw new IllegalTokenFoundException(String.format("Expected ',', Found %s",
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
		nextPotentialCharacters.put(Brace.LEFT_BRACE, "{");
		nextPotentialCharacters.put(SquareBracket.LEFT_SQUARE_BRACKET, "[");
		nextPotentialCharacters.put(DataType.NUMBER, "[0-9]");
		nextPotentialCharacters.put(DataType.STRING, "\"");
		nextPotentialCharacters.put(DataType.BOOLEAN, "[t|f]");
	}
}
