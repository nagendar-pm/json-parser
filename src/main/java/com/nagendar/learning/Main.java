package com.nagendar.learning;

/*
 * @author: pagidimarri.nagendar
 * @createdAt: 03/10/23 8:27 pm
 */

import com.nagendar.learning.lexer.Lexer;
import com.nagendar.learning.lexer.LexerImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
	public static void main(String[] args) throws IOException {
		String inputFile = "/Users/pagidimarrinagendar/Documents/My/java-projects/coding-challenges/json-parser/resources/input.txt";
		Path file = Path.of(inputFile);
		String fileContent = Files.readString(file);
		System.out.println("fileContent = " + fileContent);
		Lexer lexer = new LexerImpl(fileContent);
		while (lexer.hasToken()) {
			Object token = lexer.nextToken();
			if (token.equals("WHITE_SPACE")) continue;
			System.out.println("Token: " + token);
		}
	}
}