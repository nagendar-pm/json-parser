/*
 * @author: pagidimarri.nagendar
 * @createdAt: 17/10/23 7:24 pm
 */

package com.nagendar.learning.parser.analyzer;

import com.nagendar.learning.exception.IllegalTokenFoundException;
import com.nagendar.learning.lexer.Input;
import com.nagendar.learning.lexer.Lexeme;
import com.nagendar.learning.lexer.tokens.Brace;
import com.nagendar.learning.lexer.tokens.Comma;
import com.nagendar.learning.lexer.tokens.SquareBracket;
import com.nagendar.learning.parser.TokenBase;

import java.util.Objects;

public class RightBracketAnalyzer extends Analyzer {
	public RightBracketAnalyzer(Input input, AnalyzerFactory analyzerFactory) {
		super(input, analyzerFactory);
		setNextAnalyzer();
	}

	@Override
	public void analyze(TokenBase tokenBase) {
		Lexeme currentLexeme = tokenBase.getLexeme();
		if (currentLexeme.getTokenType() != SquareBracket.RIGHT_SQUARE_BRACKET) {
			throw new IllegalTokenFoundException(String.format("Expected ], Found %s",
					currentLexeme.getValue()));
		}
		boolean isIncremented = tokenBase.incrementIndex();
		Lexeme nextLexeme = tokenBase.getLexeme();
		if (!isIncremented || Objects.isNull(nextLexeme)) {
			return;
		}
		if (!nextPotentialCharacters.containsKey(nextLexeme.getTokenType())) {
			super.throwUnexpectedCharacterException(nextLexeme);
		}
		analyzerFactory.getAnalyzerForToken(nextLexeme.getTokenType()).analyze(tokenBase);
	}

	@Override
	public void setNextAnalyzer() {
		nextPotentialCharacters.put(Comma.COMMA, ",");
		nextPotentialCharacters.put(SquareBracket.RIGHT_SQUARE_BRACKET, "]");
		nextPotentialCharacters.put(Brace.RIGHT_BRACE, "}");
	}
}
