/*
 * @author: pagidimarri.nagendar
 * @createdAt: 08/10/23 8:49 pm
 */

package com.nagendar.learning.parser;

import com.nagendar.learning.lexer.Lexeme;
import com.nagendar.learning.lexer.tokens.*;

import java.util.*;

public class ParserImpl implements Parser {
	@Override
	public boolean parse(List<Lexeme> lexemes) {
		Stack<Lexeme> symbols = new Stack<>();
		// replace list of values with array symbol
		// any string : {string, number, boolean etc} with key : value
		for (int i = 0; i < lexemes.size(); i++) {
			if (lexemes.get(i).getTokenType() == SquareBracket.RIGHT_SQUARE_BRACKET) {
				List<Lexeme> list = new ArrayList<>();
				while (!symbols.isEmpty() &&
						symbols.peek().getTokenType() != SquareBracket.LEFT_SQUARE_BRACKET) {
					Lexeme symbol = symbols.pop();
					if (symbol.getTokenType() != Comma.COMMA) {
						// TODO: last one shouldn't be a comma
						list.add(symbol);
					}
				}
				symbols.pop();
				symbols.push(new Lexeme(DataType.ARRAY, list));
			} else if (lexemes.get(i).getTokenType() == Brace.RIGHT_BRACE) {
				Map<Lexeme, Lexeme> lexemeMap = new LinkedHashMap<>();
				while (!symbols.isEmpty() &&
						symbols.peek().getTokenType() != Brace.LEFT_BRACE) {
					// TODO: handle this
					symbols.pop();
				}
				symbols.pop();
				symbols.push(new Lexeme(DataType.OBJECT, lexemeMap));
			} else if (lexemes.get(i).getTokenType() == Comma.COMMA) {
				if (symbols.size() < 3) {
					// TODO: throw an exception here
					System.out.println("invalid json");
				}
				Lexeme valueCandidate = symbols.pop();
				Lexeme colonCandidate = symbols.pop();
				if (colonCandidate.getTokenType() != Colon.COLON) {
					// Comma possibly belongs to an array
					symbols.push(colonCandidate);
					symbols.push(valueCandidate);
				}
				Lexeme keyCandidate = symbols.pop();
				// TODO: we can push these lexemes into a map kind
			}
			else {
				symbols.push(lexemes.get(i));
			}
		}
		return symbols.size() == 1 && symbols.get(0).getTokenType() == DataType.OBJECT;
	}
}
