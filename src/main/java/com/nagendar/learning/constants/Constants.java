package com.nagendar.learning.constants;/*
 * @author: pagidimarri.nagendar
 * @createdAt: 16/10/23 4:29 pm
 */

import java.util.Set;

public interface Constants {
	String NUMBER_TOKENIZER = "NUMBER_TOKENIZER";
	String BOOLEAN_TOKENIZER = "BOOLEAN_TOKENIZER";
	String STRING_TOKENIZER = "STRING_TOKENIZER";
	String SEPARATOR_TOKENIZER = "SEPARATOR_TOKENIZER";
	String WHITESPACE_TOKENIZER = "WHITESPACE_TOKENIZER";
	Set<Character> STRING_INITIALIZER = Set.of('\"');
	Set<Character> BOOLEAN_INITIALIZER = Set.of('t', 'f');
	Set<Character> NUMBER_INITIALIZER = Set.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '+');
	Set<Character> SEPARATOR_CHARACTERS = Set.of('[', ']', '{', '}', ':', ',');
	Set<Character> WHITESPACE_CHARACTERS = Set.of(' ', '\n', '\t', '\f', '\b', '\r');
}
