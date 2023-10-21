package com.nagendar.learning;

/*
 * @author: pagidimarri.nagendar
 * @createdAt: 03/10/23 8:27 pm
 */

import com.nagendar.learning.lexer.Input;
import com.nagendar.learning.lexer.Lexeme;
import com.nagendar.learning.lexer.Lexer;
import com.nagendar.learning.lexer.LexerImpl;
import com.nagendar.learning.lexer.tokenizer.TokenizerFactory;
import com.nagendar.learning.lexer.tokens.DataType;
import com.nagendar.learning.parser.Parser;
import com.nagendar.learning.parser.ParserImpl;
import com.nagendar.learning.parser.TokenBase;
import com.nagendar.learning.parser.analyzer.Analyzer;
import com.nagendar.learning.parser.analyzer.AnalyzerFactory;
import com.nagendar.learning.utils.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws IOException {
		String dir = "resources";
		Set<String> inputFiles = FileUtils.listFilesUsingFilesList(FileUtils.toAbsolutePath(dir));
		for (String inputFile : inputFiles) {
			Path file = Path.of(inputFile);
			String fileContent = Files.readString(file);
			System.out.println("Working on " + inputFile);
			System.out.println("fileContent = " + fileContent);
			List<Lexeme> lexemes = tokenize(fileContent);
			analyze(lexemes, new Input(fileContent));
			parse(lexemes);
		}
	}

	private static List<Lexeme> tokenize(String fileContent) {
		StringBuilder stringBuilder = new StringBuilder();
		TokenizerFactory tokenizerFactory = new TokenizerFactory(stringBuilder);
		Lexer lexer = new LexerImpl(fileContent, tokenizerFactory);
		List<Lexeme> lexemes = new ArrayList<>();
		while (lexer.hasToken()) {
			Lexeme lexeme = lexer.nextToken();
			if (lexeme.getTokenType() == DataType.WHITE_SPACE) continue;
			lexemes.add(lexeme);
			System.out.println(lexeme);
		}
		return lexemes;
	}

	private static void analyze(List<Lexeme> lexemes, Input input) {
		TokenBase tokenBase = new TokenBase(lexemes);
		AnalyzerFactory analyzerFactory = new AnalyzerFactory(input);
		Analyzer analyzer = analyzerFactory.getAnalyzerForToken(tokenBase.getLexeme().getTokenType());
		analyzer.analyze(tokenBase);
	}

	private static void parse(List<Lexeme> lexemes) {
		Parser parser = new ParserImpl();
		boolean parse = parser.parse(lexemes);
		System.out.println("Verdict = " + (parse ? "Valid JSON" : "Invalid JSON"));
	}
}