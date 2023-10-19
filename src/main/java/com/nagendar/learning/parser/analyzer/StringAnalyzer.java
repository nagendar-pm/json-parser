/*
 * @author: pagidimarri.nagendar
 * @createdAt: 18/10/23 3:44 pm
 */

package com.nagendar.learning.parser.analyzer;

import com.nagendar.learning.lexer.Input;
import com.nagendar.learning.lexer.Lexeme;
import com.nagendar.learning.lexer.tokens.Brace;
import com.nagendar.learning.lexer.tokens.Colon;
import com.nagendar.learning.lexer.tokens.Comma;
import com.nagendar.learning.lexer.tokens.SquareBracket;
import com.nagendar.learning.parser.TokenBase;

import java.util.Objects;

public class StringAnalyzer extends Analyzer {

	public StringAnalyzer(Input input, AnalyzerFactory analyzerFactory) {
		super(input, analyzerFactory);
		setNextAnalyzer();
	}

	@Override
	public void analyze(TokenBase tokenBase) {
		Lexeme nextLexeme = tokenBase.getLexeme();
		if (Objects.isNull(nextLexeme)) {
			super.throwAbruptEndException();
		}
		if (!nextPotentialCharacters.containsKey(nextLexeme.getTokenType())) {
			super.throwUnexpectedCharacterException(nextLexeme);
		}
		analyzerFactory.getAnalyzerForToken(nextLexeme.getTokenType()).analyze(tokenBase);
	}

	@Override
	public void setNextAnalyzer() {
		nextPotentialCharacters.put(Colon.COLON, ":");
		nextPotentialCharacters.put(Comma.COMMA, ",");
		nextPotentialCharacters.put(SquareBracket.RIGHT_SQUARE_BRACKET, "]");
		nextPotentialCharacters.put(Brace.RIGHT_BRACE, "}");
	}
}
