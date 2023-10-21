package com.nagendar.learning.formatter;/*
 * @author: pagidimarri.nagendar
 * @createdAt: 21/10/23 1:10 pm
 */

import com.nagendar.learning.lexer.Lexeme;

public interface Formatter {
	String format(Lexeme lexeme, int depth);
}
