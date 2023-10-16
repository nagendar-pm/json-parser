/*
 * @author: pagidimarri.nagendar
 * @createdAt: 16/10/23 4:28 pm
 */

package com.nagendar.learning.lexer.tokenizer;

import com.nagendar.learning.constants.Constants;

import java.util.HashMap;
import java.util.Map;

public class TokenizerFactory {
	private Map<String, Tokenizer> tokenizerMap;
	private final StringBuilder stringBuilder;

	public TokenizerFactory(StringBuilder stringBuilder) {
		this.stringBuilder = stringBuilder;
		this.generateTokenizers();
	}

	private void generateTokenizers() {
		this.tokenizerMap = new HashMap<>();
		tokenizerMap.put(Constants.NUMBER_TOKENIZER, new NumberTokenizer(stringBuilder));
		tokenizerMap.put(Constants.BOOLEAN_TOKENIZER, new BooleanTokenizer(stringBuilder));
		tokenizerMap.put(Constants.SEPARATOR_TOKENIZER, new SeparatorTokenizer());
	}

	public Tokenizer getTokenizer(String tokenizerName) {
		return tokenizerMap.get(tokenizerName);
	}
}
