/*
 * @author: pagidimarri.nagendar
 * @createdAt: 07/10/23 10:30 pm
 */

package com.nagendar.learning.lexer;

public interface Lexer {
	Lexeme nextToken();
	boolean hasToken();
}
