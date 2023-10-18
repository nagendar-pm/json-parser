package com.nagendar.learning;

/*
 * @author: pagidimarri.nagendar
 * @createdAt: 03/10/23 8:27 pm
 */

import com.nagendar.learning.lexer.Lexeme;
import com.nagendar.learning.lexer.Lexer;
import com.nagendar.learning.lexer.LexerImpl;
import com.nagendar.learning.lexer.tokenizer.TokenizerFactory;
import com.nagendar.learning.lexer.tokens.DataType;
import com.nagendar.learning.parser.Parser;
import com.nagendar.learning.parser.ParserImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		String inputFile = "/Users/pagidimarrinagendar/Documents/My/java-projects/coding-challenges/json-parser/resources/input.txt";
		Path file = Path.of(inputFile);
		String fileContent = Files.readString(file);
		System.out.println("fileContent = " + fileContent);
		StringBuilder stringBuilder = new StringBuilder();
		TokenizerFactory tokenizerFactory = new TokenizerFactory(stringBuilder);
		Lexer lexer = new LexerImpl(fileContent, tokenizerFactory);
		Parser parser = new ParserImpl();
		List<Lexeme> lexemes = new ArrayList<>();
		while (lexer.hasToken()) {
			Lexeme lexeme = lexer.nextToken();
			if (lexeme.getTokenType() == DataType.WHITE_SPACE) continue;
			lexemes.add(lexeme);
			System.out.println(lexeme);
		}
		boolean parse = parser.parse(lexemes);
		System.out.println("Verdict = " + (parse ? "Valid JSON" : "Invalid JSON"));
	}
}