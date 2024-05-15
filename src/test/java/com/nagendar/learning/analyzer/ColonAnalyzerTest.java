/*
 * @author: pagidimarri.nagendar
 * @createdAt: 15/05/24 9:50 pm
 */

package com.nagendar.learning.analyzer;

import com.nagendar.learning.exception.IllegalTokenFoundException;
import com.nagendar.learning.lexer.Input;
import com.nagendar.learning.lexer.Lexeme;
import com.nagendar.learning.lexer.LexerImpl;
import com.nagendar.learning.lexer.tokenizer.TokenizerFactory;
import com.nagendar.learning.lexer.tokens.Colon;
import com.nagendar.learning.lexer.tokens.DataType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ColonAnalyzerTest {
	private final TokenizerFactory factory = new TokenizerFactory(new StringBuilder());
	private final LexerImpl lexer = new LexerImpl(factory);

	@Test
	public void testToAnalyzeReplacedColonCharacter() {
		String inputString = "\"key1\",\"value1\"";
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
		tokenBase.incrementIndex();
		Analyzer colonAnalyzer = analyzerFactory.getAnalyzerForToken(Colon.COLON);
		Throwable exception = Assertions.assertThrows(IllegalTokenFoundException.class ,
				() -> colonAnalyzer.analyze(tokenBase));
		Assertions.assertTrue(exception.getMessage().startsWith("Expected :, Found ,"));
	}

	@Test
	public void testToAnalyzeWrongCharacterPostColonRightBrace() {
		String inputString = "\"key1\":}";
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
		tokenBase.incrementIndex();
		Analyzer colonAnalyzer = analyzerFactory.getAnalyzerForToken(Colon.COLON);
		Throwable exception = Assertions.assertThrows(IllegalTokenFoundException.class ,
				() -> colonAnalyzer.analyze(tokenBase));
		Assertions.assertTrue(exception.getMessage().startsWith("Expected {, [t|f], [, \", [0-9], Found }"));
	}

	@Test
	public void testToAnalyzeWrongCharacterPostColonRightSquareBracket() {
		String inputString = "\"key1\":]";
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
		tokenBase.incrementIndex();
		Analyzer colonAnalyzer = analyzerFactory.getAnalyzerForToken(Colon.COLON);
		Throwable exception = Assertions.assertThrows(IllegalTokenFoundException.class ,
				() -> colonAnalyzer.analyze(tokenBase));
		Assertions.assertTrue(exception.getMessage().startsWith("Expected {, [t|f], [, \", [0-9], Found ]"));
	}

	@Test
	public void testToAnalyzeCorrectCharacterPostColonLeftBrace() {
		String inputString = "\"key1\":{\"key2\":\"value2\"}";
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
		tokenBase.incrementIndex();
		Analyzer colonAnalyzer = analyzerFactory.getAnalyzerForToken(Colon.COLON);
		Assertions.assertDoesNotThrow(() -> colonAnalyzer.analyze(tokenBase));
	}

	@Test
	public void testToAnalyzeCorrectCharacterPostColonLeftSquareBracket() {
		String inputString = "\"key1\":[\"key2\"]";
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
		tokenBase.incrementIndex();
		Analyzer colonAnalyzer = analyzerFactory.getAnalyzerForToken(Colon.COLON);
		Assertions.assertDoesNotThrow(() -> colonAnalyzer.analyze(tokenBase));
	}

	@Test
	public void testToAnalyzeCorrectCharacterPostColonNumberValue() {
		String inputString = "\"key1\":20}";
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
		tokenBase.incrementIndex();
		Analyzer colonAnalyzer = analyzerFactory.getAnalyzerForToken(Colon.COLON);
		Assertions.assertDoesNotThrow(() -> colonAnalyzer.analyze(tokenBase));
	}

	@Test
	public void testToAnalyzeCorrectCharacterPostColonStringValue() {
		String inputString = "\"key1\":\"value1\"}";
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
		tokenBase.incrementIndex();
		Analyzer colonAnalyzer = analyzerFactory.getAnalyzerForToken(Colon.COLON);
		Assertions.assertDoesNotThrow(() -> colonAnalyzer.analyze(tokenBase));
	}

	@Test
	public void testToAnalyzeCorrectCharacterPostColonBooleanValue() {
		String inputString = "\"key1\":true}";
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
		tokenBase.incrementIndex();
		Analyzer colonAnalyzer = analyzerFactory.getAnalyzerForToken(Colon.COLON);
		Assertions.assertDoesNotThrow(() -> colonAnalyzer.analyze(tokenBase));
	}

	@Test
	public void testToAnalyzeCorrectCharacterPostColonRandomValue() {
		String inputString = "\"key1\":True}";
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
		tokenBase.incrementIndex();
		Analyzer colonAnalyzer = analyzerFactory.getAnalyzerForToken(Colon.COLON);
		Throwable exception = Assertions.assertThrows(IllegalTokenFoundException.class ,
				() -> colonAnalyzer.analyze(tokenBase));
		Assertions.assertTrue(exception.getMessage().startsWith("Expected \", [t|f], [, {, [0-9], Found null"));
	}
}
