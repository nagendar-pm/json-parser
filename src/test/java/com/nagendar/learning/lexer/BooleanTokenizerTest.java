/*
 * @author: pagidimarri.nagendar
 * @createdAt: 06/05/24 10:37 pm
 */

package com.nagendar.learning.lexer;

import com.nagendar.learning.exception.IllegalBooleanTokenException;
import com.nagendar.learning.lexer.tokenizer.BooleanTokenizer;
import com.nagendar.learning.lexer.tokens.DataType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BooleanTokenizerTest {
	private final StringBuilder stringBuilder = new StringBuilder();
	private final BooleanTokenizer booleanTokenizer = new BooleanTokenizer(stringBuilder);

	@Test
	public void testToValidateValidTrueValueTokenization() {
		Input input = new Input("true");
		Lexeme token = booleanTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), DataType.BOOLEAN);
		Assertions.assertEquals(token.getValue(), "true");
	}

	@Test
	public void testToValidateValidFalseValueTokenization() {
		Input input = new Input("false");
		Lexeme token = booleanTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), DataType.BOOLEAN);
		Assertions.assertEquals(token.getValue(), "false");
	}

	@Test
	public void testToValidateInvalidTrueValueTokenization() {
		Input input = new Input("ture");
		Assertions.assertThrows(IllegalBooleanTokenException.class,
				() -> booleanTokenizer.getToken(input));
	}

	@Test
	public void testToValidateInvalidFalseValueTokenization() {
		Input input = new Input("fasle");
		Assertions.assertThrows(IllegalBooleanTokenException.class,
				() -> booleanTokenizer.getToken(input));
	}
}
