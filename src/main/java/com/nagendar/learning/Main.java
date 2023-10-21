package com.nagendar.learning;

/*
 * @author: pagidimarri.nagendar
 * @createdAt: 03/10/23 8:27 pm
 */

import com.nagendar.learning.formatter.FormatterFactory;
import com.nagendar.learning.lexer.Input;
import com.nagendar.learning.lexer.Lexeme;
import com.nagendar.learning.lexer.Lexer;
import com.nagendar.learning.lexer.LexerImpl;
import com.nagendar.learning.lexer.tokenizer.TokenizerFactory;
import com.nagendar.learning.lexer.tokens.DataType;
import com.nagendar.learning.parser.Parser;
import com.nagendar.learning.parser.ParserImpl;
import com.nagendar.learning.analyzer.TokenBase;
import com.nagendar.learning.analyzer.Analyzer;
import com.nagendar.learning.analyzer.AnalyzerFactory;
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
			System.out.println("fileContent\n" + fileContent);
			List<Lexeme> lexemes = tokenize(fileContent);
			analyze(lexemes, new Input(fileContent));
			Lexeme parsedJson = parse(lexemes);
			format(parsedJson);
			System.out.println("\n\n\n");
		}
	}

	private static List<Lexeme> tokenize(String fileContent) {
		boxedPrint("Lexical Analysis");
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
		System.out.println("Analyzing the character pattern...");
		TokenBase tokenBase = new TokenBase(lexemes);
		AnalyzerFactory analyzerFactory = new AnalyzerFactory(input);
		Analyzer analyzer = analyzerFactory.getAnalyzerForToken(tokenBase.getLexeme().getTokenType());
		analyzer.analyze(tokenBase);
	}

	private static Lexeme parse(List<Lexeme> lexemes) {
		boxedPrint("Syntactical Analysis");
		Parser parser = new ParserImpl();
		Lexeme parsedJson = parser.parse(lexemes);
		System.out.println("parsed Json = " + parsedJson.getValue());
		return parsedJson;
	}

	private static void format(Lexeme lexeme) {
		boxedPrint("Formatted Json");
		FormatterFactory formatterFactory = new FormatterFactory();
		String formattedJson = formatterFactory.getFormatterFromToken(lexeme.getTokenType()).format(lexeme, 1);
		System.out.println(formattedJson);
	}

	private static void boxedPrint(String printStatement) {
		printStatement = "  " + printStatement + "  ";
		StringBuilder stringBuilder = new StringBuilder();
		int length = printStatement.length();
		stringBuilder.append('+')
				.append("-".repeat(length))
				.append('+');
		stringBuilder.append("\n")
				.append('|')
				.append(printStatement)
				.append('|');
		stringBuilder.append("\n")
				.append('+')
				.append("-".repeat(length))
				.append('+');
		System.out.println(stringBuilder);
	}
}