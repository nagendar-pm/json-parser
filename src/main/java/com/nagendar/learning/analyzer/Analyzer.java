/*
 * @author: pagidimarri.nagendar
 * @createdAt: 17/10/23 5:48 pm
 */

package com.nagendar.learning.analyzer;

import com.nagendar.learning.exception.IllegalTokenFoundException;
import com.nagendar.learning.lexer.Input;
import com.nagendar.learning.lexer.Lexeme;
import com.nagendar.learning.lexer.tokens.Token;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Analyzer {
	protected final Input input;
	protected final Map<Token, String> nextPotentialCharacters;
	protected final AnalyzerFactory analyzerFactory;
	protected String nextPotentialCharactersString;

	public Analyzer(Input input, AnalyzerFactory analyzerFactory) {
		this.input = input;
		this.analyzerFactory = analyzerFactory;
		this.nextPotentialCharacters = new HashMap<>();
		this.nextPotentialCharactersString = null;
	}
	public abstract void analyze(TokenBase tokenBase);
	public abstract void setNextAnalyzer();

	public String getNextPotentialCharactersString() {
		if (Objects.isNull(this.nextPotentialCharactersString)) {
			nextPotentialCharactersString = nextPotentialCharacters.values()
					.stream()
					.reduce((a, b) -> a + ", " + b).orElse("NULL");
		}
		return nextPotentialCharactersString;
	}

	protected void throwAbruptEndException() {
		throw new IllegalTokenFoundException(String.format("Expected %s, Found no token",
				getNextPotentialCharactersString()));
	}

	protected void throwUnexpectedCharacterException(Lexeme nextLexeme) {
		throw new IllegalTokenFoundException(String.format("Expected %s, Found %s : At %s-th character \"%s\"",
				getNextPotentialCharactersString(),
				nextLexeme.getValue(),
				nextLexeme.getStart(),
				inputAtUnexpectedCharacter(nextLexeme.getStart())));
	}

	private String inputAtUnexpectedCharacter(int from) {
		return input.getInput().substring(
				Math.max(from-10, 0),
				Math.min(from+10, input.getInput().length()));
	}
}
