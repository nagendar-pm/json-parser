package com.nagendar.learning.lexer.tokenizer;/*
 * @author: pagidimarri.nagendar
 * @createdAt: 16/10/23 3:10 pm
 */

import com.nagendar.learning.lexer.Input;
import com.nagendar.learning.lexer.Lexeme;

public interface Tokenizer {
	Lexeme getToken(Input input);
}
