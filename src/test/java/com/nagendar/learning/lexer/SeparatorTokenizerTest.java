/*
 * @author: pagidimarri.nagendar
 * @createdAt: 06/05/24 10:56 pm
 */

package com.nagendar.learning.lexer;

import com.nagendar.learning.lexer.tokenizer.SeparatorTokenizer;
import com.nagendar.learning.lexer.tokens.Brace;
import com.nagendar.learning.lexer.tokens.Colon;
import com.nagendar.learning.lexer.tokens.Comma;
import com.nagendar.learning.lexer.tokens.SquareBracket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SeparatorTokenizerTest {
	private final SeparatorTokenizer separatorTokenizer = new SeparatorTokenizer();

	@Test
	public void testToValidateLeftBraceTokenization() {
		Input input = new Input("{");
		Lexeme token = separatorTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), Brace.LEFT_BRACE);
		Assertions.assertEquals(token.getValue(), "{");
	}

	@Test
	public void testToValidateRightBraceTokenization() {
		Input input = new Input("}");
		Lexeme token = separatorTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), Brace.RIGHT_BRACE);
		Assertions.assertEquals(token.getValue(), "}");
	}

	@Test
	public void testToValidateLeftSquareBracketTokenization() {
		Input input = new Input("[");
		Lexeme token = separatorTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), SquareBracket.LEFT_SQUARE_BRACKET);
		Assertions.assertEquals(token.getValue(), "[");
	}

	@Test
	public void testToValidateRightSquareBracketTokenization() {
		Input input = new Input("]");
		Lexeme token = separatorTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), SquareBracket.RIGHT_SQUARE_BRACKET);
		Assertions.assertEquals(token.getValue(), "]");
	}

	@Test
	public void testToValidateColonTokenization() {
		Input input = new Input(":");
		Lexeme token = separatorTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), Colon.COLON);
		Assertions.assertEquals(token.getValue(), ":");
	}

	@Test
	public void testToValidateCommaTokenization() {
		Input input = new Input(",");
		Lexeme token = separatorTokenizer.getToken(input);
		Assertions.assertEquals(token.getTokenType(), Comma.COMMA);
		Assertions.assertEquals(token.getValue(), ",");
	}
}
