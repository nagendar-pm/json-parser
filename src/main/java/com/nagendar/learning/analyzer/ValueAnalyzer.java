/*
 * @author: pagidimarri.nagendar
 * @createdAt: 17/10/23 7:22 pm
 */

package com.nagendar.learning.analyzer;

import com.nagendar.learning.lexer.Input;
import com.nagendar.learning.lexer.Lexeme;
import com.nagendar.learning.lexer.tokens.Brace;
import com.nagendar.learning.lexer.tokens.Comma;
import com.nagendar.learning.lexer.tokens.DataType;
import com.nagendar.learning.lexer.tokens.SquareBracket;

import java.util.Objects;

public class ValueAnalyzer extends Analyzer {
	private final Analyzer stringAnalyzer;

	public ValueAnalyzer(Input input, AnalyzerFactory analyzerFactory) {
		super(input, analyzerFactory);
		this.stringAnalyzer = new StringAnalyzer(input, analyzerFactory);
		setNextAnalyzer();
	}

	@Override
	public void analyze(TokenBase tokenBase) {
		Lexeme currentLexeme = tokenBase.getLexeme();
		boolean isIncremented = tokenBase.incrementIndex();
		if (currentLexeme.getTokenType() == DataType.STRING) {
			stringAnalyzer.analyze(tokenBase);
		}
		else {
			Lexeme nextLexeme = tokenBase.getLexeme();
			if (!isIncremented || Objects.isNull(nextLexeme)) {
				super.throwAbruptEndException();
			}
			if (!nextPotentialCharacters.containsKey(nextLexeme.getTokenType())) {
				super.throwUnexpectedCharacterException(nextLexeme);
			}
			analyzerFactory.getAnalyzerForToken(nextLexeme.getTokenType()).analyze(tokenBase);
		}
	}

	@Override
	public void setNextAnalyzer() {
		nextPotentialCharacters.put(Comma.COMMA, ",");
		nextPotentialCharacters.put(SquareBracket.RIGHT_SQUARE_BRACKET, "]");
		nextPotentialCharacters.put(Brace.RIGHT_BRACE, "}");
	}
}
