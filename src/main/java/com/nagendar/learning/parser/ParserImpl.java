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
				Map<String, Object> lexemeMap = new LinkedHashMap<>();
				while (!symbols.isEmpty() &&
						symbols.peek().getTokenType() != Brace.LEFT_BRACE) {
					if (symbols.peek().getTokenType() == Comma.COMMA) {
						symbols.pop();
					}
					List<Lexeme> pair = handlePair(symbols);
					if (Objects.isNull(pair)) {
						// throw exception here
						continue;
					}
					lexemeMap.put(pair.get(0).getValue().toString(), pair.get(1));
				}
				symbols.pop();
				symbols.push(new Lexeme(DataType.OBJECT, lexemeMap));
			}
//			else if (lexemes.get(i).getTokenType() == Comma.COMMA) {
//				List<Lexeme> pair = handlePair(symbols);
//				if (Objects.isNull(pair)) continue;
//				json.put(pair.get(0).getValue().toString(), pair.get(1));
//			}
			else {
				symbols.push(lexemes.get(i));
			}
		}
		if (symbols.size() == 1) {
			Lexeme jsonObject = symbols.peek();
			Map<String, Object> json = (Map<String, Object>) jsonObject.getValue();
			List<String> reverseOrderKeys = new ArrayList<>(json.keySet());
			Collections.reverse(reverseOrderKeys);
			Map<String, Object> jsonCorrect = new LinkedHashMap<>();
			reverseOrderKeys.forEach(key -> jsonCorrect.put(key, json.get(key)));
			System.out.println("jsonCorrect = " + jsonCorrect);
		}
		return symbols.size() == 1 && symbols.get(0).getTokenType() == DataType.OBJECT;
	}

	private List<Lexeme> handlePair (Stack<Lexeme> symbols) {
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
			return null;
		}
		Lexeme keyCandidate = symbols.pop();
		return List.of(keyCandidate, valueCandidate);
	}
}
