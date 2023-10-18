/*
 * @author: pagidimarri.nagendar
 * @createdAt: 16/10/23 5:48 pm
 */

package com.nagendar.learning.lexer.tokenizer;

import com.nagendar.learning.lexer.Input;
import com.nagendar.learning.lexer.Lexeme;
import com.nagendar.learning.lexer.tokens.DataType;

import java.util.HashSet;
import java.util.Set;

public class WhitespaceTokenizer implements Tokenizer {
	private Set<Character> validWhiteSpaces;

	public WhitespaceTokenizer() {
		populateAllowedWhiteSpaceCharacters();
	}

	private void populateAllowedWhiteSpaceCharacters() {
		validWhiteSpaces = new HashSet<>();
		validWhiteSpaces.add(' ');
		validWhiteSpaces.add('\n');
		validWhiteSpaces.add('\t');
		validWhiteSpaces.add('\b');
		validWhiteSpaces.add('\f');
		validWhiteSpaces.add('\r');
		// TODO: adding \\uXXXX unicode characters
	}

	@Override
	public Lexeme getToken(Input input) {
		int start = input.getIndex();
		int index = start;
		String inputString = input.getInput();
		while (validWhiteSpaces.contains(inputString.charAt(index))) {
			index++;
		}
		input.setIndex(index);
		return new Lexeme(DataType.WHITE_SPACE, "", start, index);
	}
}
