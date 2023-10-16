/*
 * @author: pagidimarri.nagendar
 * @createdAt: 16/10/23 3:12 pm
 */

package com.nagendar.learning.lexer.tokenizer;

import com.nagendar.learning.lexer.Input;
import com.nagendar.learning.lexer.Lexeme;
import com.nagendar.learning.lexer.tokens.DataType;

public class NumberTokenizer implements Tokenizer{
	private final StringBuilder stringBuilder;

	public NumberTokenizer(StringBuilder stringBuilder) {
		this.stringBuilder = stringBuilder;
	}

	@Override
	public Lexeme getToken(Input input) {
		stringBuilder.delete(0, stringBuilder.length());
		int start = input.getIndex()-1;
		int index = start;
		String inputString = input.getInput();
		index = tokenizeUnaryOperator(inputString, index);
		index = tokenizeDigit(inputString, index);
		index = tokenizeDecimalPoint(inputString, index);
		index = tokenizeDigit(inputString, index);
		index = tokenizeExponentialOperator(inputString, index);
		index = tokenizeUnaryOperator(inputString, index);
		index = tokenizeDigit(inputString, index);
		input.setIndex(index);
		return new Lexeme(DataType.NUMBER, stringBuilder.toString(), start, index-1);
	}

	private int tokenizeDigit(String input, int index) {
		while (index < input.length()
				&& input.charAt(index) >= '0'
				&& input.charAt(index) <= '9') {
			stringBuilder.append(input.charAt(index));
			index++;
		}
		return index;
	}

	private int tokenizeDecimalPoint(String input, int index) {
		if (index < input.length()
				&& input.charAt(index) == '.') {
			stringBuilder.append(input.charAt(index));
			index++;
		}
		return index;
	}

	private int tokenizeUnaryOperator(String input, int index) {
		if (index < input.length()
				&& (input.charAt(index) == '-'
				|| input.charAt(index) == '+')) {
			stringBuilder.append(input.charAt(index));
			index++;
		}
		return index;
	}

	private int tokenizeExponentialOperator(String input, int index) {
		if (index < input.length()
				&& (input.charAt(index) == 'e'
				|| input.charAt(index) == 'E')) {
			stringBuilder.append(input.charAt(index));
			index++;
		}
		return index;
	}
}
