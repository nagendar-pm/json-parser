/*
 * @author: pagidimarri.nagendar
 * @createdAt: 07/10/23 10:32 pm
 */

package com.nagendar.learning.lexer;

import com.nagendar.learning.constants.Constants;
import com.nagendar.learning.lexer.tokenizer.TokenizerFactory;
import com.nagendar.learning.lexer.tokens.*;

public class LexerImpl implements Lexer {
	private Input input;
	private final TokenizerFactory tokenizerFactory;

	public LexerImpl(TokenizerFactory tokenizerFactory) {
		this.tokenizerFactory = tokenizerFactory;
	}

	public LexerImpl(String inputString, TokenizerFactory tokenizerFactory) {
		this.tokenizerFactory = tokenizerFactory;
		this.input = new Input(inputString);
	}

	public void setInput(String inputString) {
		this.input = new Input(inputString);
	}

	@Override
	public Lexeme nextToken() {
		if (!input.hasToken()) {
			return null;
		}
		char c = input.getNextChar();
		Lexeme lexeme;
		if (Constants.STRING_INITIALIZER.contains(c)) {
			lexeme = tokenizerFactory.getTokenizer(Constants.STRING_TOKENIZER)
					.getToken(input);
		}
		else if (Constants.BOOLEAN_INITIALIZER.contains(c)) {
			lexeme = tokenizerFactory.getTokenizer(Constants.BOOLEAN_TOKENIZER)
					.getToken(input);
		}
		else if (Constants.NUMBER_INITIALIZER.contains(c)) {
			lexeme = tokenizerFactory.getTokenizer(Constants.NUMBER_TOKENIZER)
					.getToken(input);
		}
		else if (Constants.SEPARATOR_CHARACTERS.contains(c)) {
			lexeme = tokenizerFactory.getTokenizer(Constants.SEPARATOR_TOKENIZER)
					.getToken(input);
		}
		else if (Constants.WHITESPACE_CHARACTERS.contains(c)) {
			lexeme = tokenizerFactory.getTokenizer(Constants.WHITESPACE_TOKENIZER)
					.getToken(input);
		}
		else {
			lexeme = new Lexeme(DataType.NULL, null, input.getIndex(), input.getIndex());
			input.setIndex(input.getIndex()+1);
		}
		return lexeme;
	}

	@Override
	public boolean hasToken() {
		return input.hasToken();
	}
}
