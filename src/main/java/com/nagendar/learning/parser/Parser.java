package com.nagendar.learning.parser;
/*
 * @author: pagidimarri.nagendar
 * @createdAt: 08/10/23 8:46 pm
 */

import com.nagendar.learning.lexer.Lexeme;

import java.util.List;

public interface Parser {
	boolean parse(List<Lexeme> lexemes);
}
