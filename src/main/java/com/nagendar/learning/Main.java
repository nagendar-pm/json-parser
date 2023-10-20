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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		String dir = "/Users/pagidimarrinagendar/Documents/My/java-projects/coding-challenges/json-parser/resources";
		Set<String> inputFiles = listFilesUsingFilesList(dir);
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

	private static Set<String> listFilesUsingFilesList(String dir) throws IOException {
		try (Stream<Path> stream = Files.list(Paths.get(dir))) {
			return stream
					.filter(file -> !Files.isDirectory(file))
					.map(Path::toString)
					.collect(Collectors.toSet());
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