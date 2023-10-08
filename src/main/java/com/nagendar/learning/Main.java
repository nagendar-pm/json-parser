package com.nagendar.learning;

/*
 * @author: pagidimarri.nagendar
 * @createdAt: 03/10/23 8:27 pm
 */

import com.nagendar.learning.lexer.Lexeme;
import com.nagendar.learning.lexer.Lexer;
import com.nagendar.learning.lexer.LexerImpl;
import com.nagendar.learning.lexer.tokens.DataType;

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
		Lexer lexer = new LexerImpl(fileContent);
		List<Lexeme> lexemes = new ArrayList<>();
		while (lexer.hasToken()) {
			Lexeme lexeme = lexer.nextToken();
			if (lexeme.getTokenType() == DataType.WHITE_SPACE) continue;
			lexemes.add(lexeme);
			System.out.println(lexeme);
		}
	}
}