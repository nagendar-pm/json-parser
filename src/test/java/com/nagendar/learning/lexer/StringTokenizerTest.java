/*
 * @author: pagidimarri.nagendar
 * @createdAt: 07/05/24 8:21 pm
 */

package com.nagendar.learning.lexer;

import com.nagendar.learning.lexer.tokenizer.StringTokenizer;
import com.nagendar.learning.lexer.tokens.DataType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringTokenizerTest {
	private final StringBuilder stringBuilder = new StringBuilder();
	private final StringTokenizer stringTokenizer = new StringTokenizer(stringBuilder);

	@Test
	public void testToValidateStringTokenization() {
		Input input = new Input("value");
		Lexeme token = stringTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), DataType.STRING);
		Assertions.assertEquals(token.getValue(), "value");
	}

	@Test
	public void testToValidateStringWithQuotesTokenization() {
		Input input = new Input("\"value\"");
		Lexeme token = stringTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), DataType.STRING);
		Assertions.assertEquals(token.getValue(), "\"value\"");
	}
}
