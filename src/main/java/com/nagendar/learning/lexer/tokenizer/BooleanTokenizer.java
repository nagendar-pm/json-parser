/*
 * @author: pagidimarri.nagendar
 * @createdAt: 16/10/23 4:16 pm
 */

package com.nagendar.learning.lexer.tokenizer;

import com.nagendar.learning.lexer.Input;
import com.nagendar.learning.lexer.Lexeme;
import com.nagendar.learning.lexer.exception.IllegalBooleanTokenException;
import com.nagendar.learning.lexer.tokens.DataType;

import java.util.HashMap;
import java.util.Map;

public class BooleanTokenizer implements Tokenizer {
	private final StringBuilder stringBuilder;
	private Map<Character, String> booleanStrings;

	public BooleanTokenizer(StringBuilder stringBuilder) {
		this.stringBuilder = stringBuilder;
		populateBooleanStrings();
	}

	private void populateBooleanStrings() {
		booleanStrings = new HashMap<>();
		booleanStrings.put('t', "true");
		booleanStrings.put('f', "false");
	}

	@Override
	public Lexeme getToken(Input input) {
		stringBuilder.setLength(0);
		int start = input.getIndex()-1;
		int index = start;
		String inputString = input.getInput();

		String comparingBooleanString = booleanStrings.get(inputString.charAt(index));
		int matchIndex = 0;
		while (index < inputString.length()
				&& matchIndex < comparingBooleanString.length()
				&& inputString.charAt(index) == comparingBooleanString.charAt(matchIndex)) {
			stringBuilder.append(inputString.charAt(index));
			index++;
			matchIndex++;
		}
		if (matchIndex >= comparingBooleanString.length()) {
			input.setIndex(index);
			return new Lexeme(DataType.BOOLEAN, stringBuilder.toString(), start, index-1);
		}
		throw new IllegalBooleanTokenException(String.format("Expected %s but encountered %s mismatch at %s",
				comparingBooleanString, inputString.substring(start, start + matchIndex + 1), matchIndex));
	}
}
