/*
 * @author: pagidimarri.nagendar
 * @createdAt: 15/05/24 8:10pm
 */

package com.nagendar.learning.analyzer;

import com.nagendar.learning.exception.IllegalTokenFoundException;
import com.nagendar.learning.lexer.Input;
import com.nagendar.learning.lexer.Lexeme;
import com.nagendar.learning.lexer.LexerImpl;
import com.nagendar.learning.lexer.tokenizer.TokenizerFactory;
import com.nagendar.learning.lexer.tokens.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AnalyzerTests {
	private final TokenizerFactory factory = new TokenizerFactory(new StringBuilder());
	private final LexerImpl lexer = new LexerImpl(factory);

	@Test
	public void testToAnalyzeMissingDoubleQuote() {
		String inputString = "{\"key1\":\"value1\",\"key2\":\"value2}";
		Input input = new Input(inputString);
		AnalyzerFactory analyzerFactory = new AnalyzerFactory(input);
		lexer.setInput(inputString);
		List<Lexeme> lexemes = new ArrayList<>();
		while (lexer.hasToken()) {
			Lexeme lexeme = lexer.nextToken();
			if (lexeme.getTokenType() == DataType.WHITE_SPACE) continue;
			lexemes.add(lexeme);
		}
		TokenBase tokenBase = new TokenBase(lexemes);
		Analyzer analyzer = analyzerFactory.getAnalyzerForToken(tokenBase.getLexeme().getTokenType());
		Assertions.assertThrows(IllegalTokenFoundException.class ,
				() -> analyzer.analyze(tokenBase),
		"Expected :, }, ,, ], Found no token");
	}

	@Test
	public void testToAnalyzeMissingColon() {
		String inputString = "{\"key1\":\"value1\",\"key2\"\"value2\"}";
		Input input = new Input(inputString);
		AnalyzerFactory analyzerFactory = new AnalyzerFactory(input);
		lexer.setInput(inputString);
		List<Lexeme> lexemes = new ArrayList<>();
		while (lexer.hasToken()) {
			Lexeme lexeme = lexer.nextToken();
			if (lexeme.getTokenType() == DataType.WHITE_SPACE) continue;
			lexemes.add(lexeme);
		}
		TokenBase tokenBase = new TokenBase(lexemes);
		Analyzer analyzer = analyzerFactory.getAnalyzerForToken(tokenBase.getLexeme().getTokenType());
		Assertions.assertThrows(IllegalTokenFoundException.class ,
				() -> analyzer.analyze(tokenBase),
		"Expected :, }, ,, ], Found \"value2} : At 23-th character \"e1\",\"key2\"\"value2}\"");
	}

	@Test
	public void testToAnalyzeMissingComma() {
		String inputString = "{\"key1\":\"value1\"\"key2\":\"value2\"}";
		Input input = new Input(inputString);
		AnalyzerFactory analyzerFactory = new AnalyzerFactory(input);
		lexer.setInput(inputString);
		List<Lexeme> lexemes = new ArrayList<>();
		while (lexer.hasToken()) {
			Lexeme lexeme = lexer.nextToken();
			if (lexeme.getTokenType() == DataType.WHITE_SPACE) continue;
			lexemes.add(lexeme);
		}
		TokenBase tokenBase = new TokenBase(lexemes);
		Analyzer analyzer = analyzerFactory.getAnalyzerForToken(tokenBase.getLexeme().getTokenType());
		Assertions.assertThrows(IllegalTokenFoundException.class ,
				() -> analyzer.analyze(tokenBase),
		"Expected ], ,, }, :, Found \"key2\" : At 16-th character \"\":\"value1\"\"key2\":\"va\"");
	}

	@Test
	public void testToAnalyzeMissingLeftBrace() {
		String inputString = "\"key1\":\"value1\",\"key2\":\"value2\"}";
		Input input = new Input(inputString);
		AnalyzerFactory analyzerFactory = new AnalyzerFactory(input);
		lexer.setInput(inputString);
		List<Lexeme> lexemes = new ArrayList<>();
		while (lexer.hasToken()) {
			Lexeme lexeme = lexer.nextToken();
			if (lexeme.getTokenType() == DataType.WHITE_SPACE) continue;
			lexemes.add(lexeme);
		}
		TokenBase tokenBase = new TokenBase(lexemes);
		Analyzer analyzer = analyzerFactory.getAnalyzerForToken(Brace.LEFT_BRACE);
		Assertions.assertThrows(IllegalTokenFoundException.class ,
				() -> analyzer.analyze(tokenBase),
				"Expected {, Found \"key1\"");
	}

	@Test
	public void testToAnalyzeMissingRightBrace() {
		String inputString = "{\"key1\":\"value1\",\"key2\":\"value2\"";
		Input input = new Input(inputString);
		AnalyzerFactory analyzerFactory = new AnalyzerFactory(input);
		lexer.setInput(inputString);
		List<Lexeme> lexemes = new ArrayList<>();
		while (lexer.hasToken()) {
			Lexeme lexeme = lexer.nextToken();
			if (lexeme.getTokenType() == DataType.WHITE_SPACE) continue;
			lexemes.add(lexeme);
		}
		TokenBase tokenBase = new TokenBase(lexemes);
		Analyzer analyzer = analyzerFactory.getAnalyzerForToken(tokenBase.getLexeme().getTokenType());
		Assertions.assertThrows(IllegalTokenFoundException.class ,
				() -> analyzer.analyze(tokenBase),
				"Expected :, ,, }, ], Found no token");
	}

	@Test
	public void testToAnalyzeMissingLeftSquareBracket() {
		String inputString = "\"key1\",\"value1\",\"key2\",\"value2\"]";
		Input input = new Input(inputString);
		AnalyzerFactory analyzerFactory = new AnalyzerFactory(input);
		lexer.setInput(inputString);
		List<Lexeme> lexemes = new ArrayList<>();
		while (lexer.hasToken()) {
			Lexeme lexeme = lexer.nextToken();
			if (lexeme.getTokenType() == DataType.WHITE_SPACE) continue;
			lexemes.add(lexeme);
		}
		TokenBase tokenBase = new TokenBase(lexemes);
		Analyzer analyzer = analyzerFactory.getAnalyzerForToken(SquareBracket.LEFT_SQUARE_BRACKET);
		Assertions.assertThrows(IllegalTokenFoundException.class ,
				() -> analyzer.analyze(tokenBase),
				"Expected [, Found \"key1\"");
	}

	@Test
	public void testToAnalyzeMissingRightSquareBracket() {
		String inputString = "[\"key1\",\"value1\",\"key2\",\"value2\"";
		Input input = new Input(inputString);
		AnalyzerFactory analyzerFactory = new AnalyzerFactory(input);
		lexer.setInput(inputString);
		List<Lexeme> lexemes = new ArrayList<>();
		while (lexer.hasToken()) {
			Lexeme lexeme = lexer.nextToken();
			if (lexeme.getTokenType() == DataType.WHITE_SPACE) continue;
			lexemes.add(lexeme);
		}
		TokenBase tokenBase = new TokenBase(lexemes);
		Analyzer analyzer = analyzerFactory.getAnalyzerForToken(tokenBase.getLexeme().getTokenType());
		Assertions.assertThrows(IllegalTokenFoundException.class ,
				() -> analyzer.analyze(tokenBase),
				"Expected :, ,, }, ], Found no token");
	}
}
