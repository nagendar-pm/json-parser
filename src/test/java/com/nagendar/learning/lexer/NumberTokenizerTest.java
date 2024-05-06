/*
 * @author: pagidimarri.nagendar
 * @createdAt: 06/05/24 10:47 pm
 */

package com.nagendar.learning.lexer;

import com.nagendar.learning.lexer.tokenizer.NumberTokenizer;
import com.nagendar.learning.lexer.tokens.DataType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NumberTokenizerTest {
	private final StringBuilder stringBuilder = new StringBuilder();
	private final NumberTokenizer numberTokenizer = new NumberTokenizer(stringBuilder);

	@Test
	public void testToValidateIntegerValueTokenization() {
		Input input = new Input("20898");
		Lexeme token = numberTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), DataType.NUMBER);
		Assertions.assertEquals(token.getValue(), "20898");
	}

	@Test
	public void testToValidatePositiveIntegerValueTokenization() {
		Input input = new Input("+20898");
		Lexeme token = numberTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), DataType.NUMBER);
		Assertions.assertEquals(token.getValue(), "+20898");
	}

	@Test
	public void testToValidateNegativeIntegerValueTokenization() {
		Input input = new Input("-20898");
		Lexeme token = numberTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), DataType.NUMBER);
		Assertions.assertEquals(token.getValue(), "-20898");
	}

	@Test
	public void testToValidateDecimalValueTokenization() {
		Input input = new Input("208.98");
		Lexeme token = numberTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), DataType.NUMBER);
		Assertions.assertEquals(token.getValue(), "208.98");
	}

	@Test
	public void testToValidatePositiveDecimalValueTokenization() {
		Input input = new Input("+208.98");
		Lexeme token = numberTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), DataType.NUMBER);
		Assertions.assertEquals(token.getValue(), "+208.98");
	}

	@Test
	public void testToValidateNegativeDecimalValueTokenization() {
		Input input = new Input("-208.98");
		Lexeme token = numberTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), DataType.NUMBER);
		Assertions.assertEquals(token.getValue(), "-208.98");
	}

	@Test
	public void testToValidateDecimalExponentialValueTokenization() {
		Input input = new Input("208.98e23");
		Lexeme token = numberTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), DataType.NUMBER);
		Assertions.assertEquals(token.getValue(), "208.98e23");
	}

	@Test
	public void testToValidatePositiveDecimalExponentialValueTokenization() {
		Input input = new Input("+208.98E23");
		Lexeme token = numberTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), DataType.NUMBER);
		Assertions.assertEquals(token.getValue(), "+208.98E23");
	}

	@Test
	public void testToValidateNegativeDecimalExponentialValueTokenization() {
		Input input = new Input("-208.98E23");
		Lexeme token = numberTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), DataType.NUMBER);
		Assertions.assertEquals(token.getValue(), "-208.98E23");
	}

	@Test
	public void testToValidateIntegerExponentialValueTokenization() {
		Input input = new Input("20898e23");
		Lexeme token = numberTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), DataType.NUMBER);
		Assertions.assertEquals(token.getValue(), "20898e23");
	}

	@Test
	public void testToValidatePositiveDecimalPositiveExponentialValueTokenization() {
		Input input = new Input("+208.98E+23");
		Lexeme token = numberTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), DataType.NUMBER);
		Assertions.assertEquals(token.getValue(), "+208.98E+23");
	}

	@Test
	public void testToValidateNegativeDecimalNegativeExponentialValueTokenization() {
		Input input = new Input("-208.98E-23");
		Lexeme token = numberTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), DataType.NUMBER);
		Assertions.assertEquals(token.getValue(), "-208.98E-23");
	}
}
