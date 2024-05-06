/*
 * @author: pagidimarri.nagendar
 * @createdAt: 21/04/24 11:10 pm
 */

package com.nagendar.learning.lexer;

import com.nagendar.learning.lexer.tokenizer.TokenizerFactory;
import com.nagendar.learning.lexer.tokens.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LexerTests {
	private final TokenizerFactory factory = new TokenizerFactory(new StringBuilder());
	private final LexerImpl lexer = new LexerImpl(factory);

	@Test
	public void testToValidateStringDataType() {
		String inputString = "{\"key1\":\"value1\",\"key2\":\"value2\"}";
		lexer.setInput(inputString);
		List<Token> tokens = List.of(
				Brace.LEFT_BRACE,
				DataType.STRING,
				Colon.COLON,
				DataType.STRING,
				Comma.COMMA,
				DataType.STRING,
				Colon.COLON,
				DataType.STRING,
				Brace.RIGHT_BRACE
		);
		int index = 0;
		while (lexer.hasToken()) {
			Lexeme lexeme = lexer.nextToken();
			if (lexeme.getTokenType() == DataType.WHITE_SPACE) continue;
			Assertions.assertEquals(tokens.get(index++), lexeme.getTokenType());
		}
	}

	@Test
	public void testToValidateNumberDataType() {
		String inputString = "{\"key1\":20.89,\"key2\":97}";
		lexer.setInput(inputString);
		List<Token> tokens = List.of(
				Brace.LEFT_BRACE,
				DataType.STRING,
				Colon.COLON,
				DataType.NUMBER,
				Comma.COMMA,
				DataType.STRING,
				Colon.COLON,
				DataType.NUMBER,
				Brace.RIGHT_BRACE
		);
		int index = 0;
		while (lexer.hasToken()) {
			Lexeme lexeme = lexer.nextToken();
			if (lexeme.getTokenType() == DataType.WHITE_SPACE) continue;
			Assertions.assertEquals(tokens.get(index++), lexeme.getTokenType());
		}
	}

	@Test
	public void testToValidateObjectDataType() {
		String inputString = "{\"key1\":{\"key11\":\"value11\"}}";
		lexer.setInput(inputString);
		List<Token> tokens = List.of(
				Brace.LEFT_BRACE,
				DataType.STRING,
				Colon.COLON,
				Brace.LEFT_BRACE,
				DataType.STRING,
				Colon.COLON,
				DataType.STRING,
				Brace.RIGHT_BRACE,
				Brace.RIGHT_BRACE
		);
		int index = 0;
		while (lexer.hasToken()) {
			Lexeme lexeme = lexer.nextToken();
			if (lexeme.getTokenType() == DataType.WHITE_SPACE) continue;
			Assertions.assertEquals(tokens.get(index++), lexeme.getTokenType());
		}
	}

	@Test
	public void testToValidateArrayDataType() {
		String inputString = "{\"key1\":[\"array-value-1\",20]}";
		lexer.setInput(inputString);
		List<Token> tokens = List.of(
				Brace.LEFT_BRACE,
				DataType.STRING,
				Colon.COLON,
				SquareBracket.LEFT_SQUARE_BRACKET,
				DataType.STRING,
				Comma.COMMA,
				DataType.NUMBER,
				SquareBracket.RIGHT_SQUARE_BRACKET,
				Brace.RIGHT_BRACE
		);
		int index = 0;
		while (lexer.hasToken()) {
			Lexeme lexeme = lexer.nextToken();
			if (lexeme.getTokenType() == DataType.WHITE_SPACE) continue;
			Assertions.assertEquals(tokens.get(index++), lexeme.getTokenType());
		}
	}

	@Test
	public void testToValidateBooleanDataType() {
		String inputString = "{\"key1\":true}";
		lexer.setInput(inputString);
		List<Token> tokens = List.of(
				Brace.LEFT_BRACE,
				DataType.STRING,
				Colon.COLON,
				DataType.BOOLEAN,
				Brace.RIGHT_BRACE
		);
		int index = 0;
		while (lexer.hasToken()) {
			Lexeme lexeme = lexer.nextToken();
			if (lexeme.getTokenType() == DataType.WHITE_SPACE) continue;
			Assertions.assertEquals(tokens.get(index++), lexeme.getTokenType());
		}
	}

	@Test
	public void testToValidateArrayJson() {
		String inputString = "[\"value1\",20,true]";
		lexer.setInput(inputString);
		List<Token> tokens = List.of(
				SquareBracket.LEFT_SQUARE_BRACKET,
				DataType.STRING,
				Comma.COMMA,
				DataType.NUMBER,
				Comma.COMMA,
				DataType.BOOLEAN,
				SquareBracket.RIGHT_SQUARE_BRACKET
		);
		int index = 0;
		while (lexer.hasToken()) {
			Lexeme lexeme = lexer.nextToken();
			if (lexeme.getTokenType() == DataType.WHITE_SPACE) continue;
			Assertions.assertEquals(tokens.get(index++), lexeme.getTokenType());
		}
	}

	@Test
	public void testToValidateWhitespaces() {
		String inputString = "{\"key1\":true,    \"key2\":\"value2\"}";
		lexer.setInput(inputString);
		List<Token> tokens = List.of(
				Brace.LEFT_BRACE,
				DataType.STRING,
				Colon.COLON,
				DataType.BOOLEAN,
				Comma.COMMA,
				DataType.WHITE_SPACE,
				DataType.STRING,
				Colon.COLON,
				DataType.STRING,
				Brace.RIGHT_BRACE
		);
		int index = 0;
		while (lexer.hasToken()) {
			Lexeme lexeme = lexer.nextToken();
			Assertions.assertEquals(tokens.get(index++), lexeme.getTokenType());
		}
	}

	@Test
	public void testToValidateTokenizationOfInvalidJson() {
		String inputString = "{\"key1\":\"value1\",\"key2\":\"value2\",[\"value3\",]}";
		lexer.setInput(inputString);
		List<Token> tokens = List.of(
				Brace.LEFT_BRACE,
				DataType.STRING,
				Colon.COLON,
				DataType.STRING,
				Comma.COMMA,
				DataType.STRING,
				Colon.COLON,
				DataType.STRING,
				Comma.COMMA,
				SquareBracket.LEFT_SQUARE_BRACKET,
				DataType.STRING,
				Comma.COMMA,
				SquareBracket.RIGHT_SQUARE_BRACKET,
				Brace.RIGHT_BRACE
		);
		int index = 0;
		while (lexer.hasToken()) {
			Lexeme lexeme = lexer.nextToken();
			if (lexeme.getTokenType() == DataType.WHITE_SPACE) continue;
			Assertions.assertEquals(tokens.get(index++), lexeme.getTokenType());
		}
	}
}
