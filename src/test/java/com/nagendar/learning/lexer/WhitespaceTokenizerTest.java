/*
 * @author: pagidimarri.nagendar
 * @createdAt: 07/05/24 8:38 pm
 */

package com.nagendar.learning.lexer;

import com.nagendar.learning.lexer.tokenizer.WhitespaceTokenizer;
import com.nagendar.learning.lexer.tokens.DataType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WhitespaceTokenizerTest {
	private final WhitespaceTokenizer whitespaceTokenizer = new WhitespaceTokenizer();

	@Test
	public void testToValidateWhitespaceTokenization() {
		Input input = new Input("     ");
		Lexeme token = whitespaceTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), DataType.WHITE_SPACE);
		Assertions.assertEquals(token.getValue(), "");
	}

	@Test
	public void testToValidateTabCharacterTokenization() {
		Input input = new Input("\t");
		Lexeme token = whitespaceTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), DataType.WHITE_SPACE);
		Assertions.assertEquals(token.getValue(), "");
	}

	@Test
	public void testToValidateNewlineCharacterTokenization() {
		Input input = new Input("\n");
		Lexeme token = whitespaceTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), DataType.WHITE_SPACE);
		Assertions.assertEquals(token.getValue(), "");
	}

	@Test
	public void testToValidateCarriageReturnCharacterTokenization() {
		Input input = new Input("\r");
		Lexeme token = whitespaceTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), DataType.WHITE_SPACE);
		Assertions.assertEquals(token.getValue(), "");
	}

	@Test
	public void testToValidateBackspaceCharacterTokenization() {
		Input input = new Input("\b");
		Lexeme token = whitespaceTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), DataType.WHITE_SPACE);
		Assertions.assertEquals(token.getValue(), "");
	}
}
