/*
 * @author: pagidimarri.nagendar
 * @createdAt: 16/10/23 4:54 pm
 */

package com.nagendar.learning.lexer.tokenizer;

import com.nagendar.learning.lexer.Input;
import com.nagendar.learning.lexer.Lexeme;
import com.nagendar.learning.lexer.tokens.Brace;
import com.nagendar.learning.lexer.tokens.Colon;
import com.nagendar.learning.lexer.tokens.Comma;
import com.nagendar.learning.lexer.tokens.SquareBracket;

import java.util.HashSet;
import java.util.Set;

public class SeparatorTokenizer implements Tokenizer {
	private Set<Character> validSeparators;

	public SeparatorTokenizer() {
		populateValidSeparators();
	}

	private void populateValidSeparators() {
		validSeparators = new HashSet<>();
		validSeparators.add('{');
		validSeparators.add('}');
		validSeparators.add('[');
		validSeparators.add(']');
		validSeparators.add(':');
		validSeparators.add(',');
	}

	@Override
	public Lexeme getToken(Input input) {
		int start = input.getIndex();
		int index = start;
		String inputString = input.getInput();

		char c = inputString.charAt(index);
		String operator = Character.toString(c);
		Lexeme lexeme = null;
		if (c == '{') {
			lexeme = new Lexeme(Brace.LEFT_BRACE, operator, start, index);
			index++;
			input.setIndex(index);
		}
		else if (c == '}') {
			lexeme = new Lexeme(Brace.RIGHT_BRACE, operator, start, index);
			index++;
			input.setIndex(index);
		}
		else if (c == '[') {
			lexeme = new Lexeme(SquareBracket.LEFT_SQUARE_BRACKET, operator, start, index);
			index++;
			input.setIndex(index);
		}
		else if (c == ']') {
			lexeme = new Lexeme(SquareBracket.RIGHT_SQUARE_BRACKET, operator, start, index);
			index++;
			input.setIndex(index);
		}
		else if (c == ':') {
			lexeme = new Lexeme(Colon.COLON, operator, start, index);
			index++;
			input.setIndex(index);
		}
		else if (c == ',') {
			lexeme = new Lexeme(Comma.COMMA, operator, start, index);
			index++;
			input.setIndex(index);
		}
		return lexeme;
	}
}
