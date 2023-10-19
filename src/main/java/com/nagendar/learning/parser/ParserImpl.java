/*
 * @author: pagidimarri.nagendar
 * @createdAt: 08/10/23 8:49 pm
 */

package com.nagendar.learning.parser;

import com.nagendar.learning.exception.IllegalSymbolFoundException;
import com.nagendar.learning.lexer.Lexeme;
import com.nagendar.learning.lexer.tokens.*;

import java.util.*;

public class ParserImpl implements Parser {
	@Override
	public boolean parse(List<Lexeme> lexemes) {
		Stack<Lexeme> symbols = new Stack<>();
		for (int i = 0; i < lexemes.size(); i++) {
			if (lexemes.get(i).getTokenType() == SquareBracket.RIGHT_SQUARE_BRACKET) {
				Lexeme rightSquareBracket = lexemes.get(i);
				LinkedList<Lexeme> list = new LinkedList<>();
				while (!symbols.isEmpty() &&
						symbols.peek().getTokenType() != SquareBracket.LEFT_SQUARE_BRACKET) {
					Lexeme symbol = symbols.pop();
					boolean isValueType = isValueType(symbol.getTokenType());
					if (!isValueType && symbol.getTokenType() != Comma.COMMA) {
						throw new IllegalSymbolFoundException(String.format("JSON cannot be parsed: Expected one of the types %s, Found \"%s\"",
								Arrays.toString(DataType.values()),
								symbol.getValue()));
					}
					if (isValueType) {
						list.addFirst(symbol);
					}
				}
				if (symbols.isEmpty()) {
					throw new IllegalSymbolFoundException("JSON cannot be parsed: Expected [, Found end of the string");
				}
				Lexeme leftSquareBracket = symbols.pop();
				symbols.push(new Lexeme(DataType.ARRAY, list,
						leftSquareBracket.getStart(),
						rightSquareBracket.getEnd()));
			} else if (lexemes.get(i).getTokenType() == Brace.RIGHT_BRACE) {
				Lexeme rightBrace = lexemes.get(i);
				Map<String, Object> lexemeMap = new LinkedHashMap<>();
				while (!symbols.isEmpty() &&
						symbols.peek().getTokenType() != Brace.LEFT_BRACE) {
					if (symbols.peek().getTokenType() == Comma.COMMA) {
						symbols.pop();
					}
					List<Lexeme> pair = handlePair(symbols);
					lexemeMap.put(pair.get(0).getValue().toString(), pair.get(1));
				}
				if (symbols.isEmpty()) {
					throw new IllegalSymbolFoundException("JSON cannot be parsed: Expected {, Found end of the string");
				}
				Lexeme leftBrace = symbols.pop();
				Map<String, Object> correctMap = reverseMap(lexemeMap);
				symbols.push(new Lexeme(DataType.OBJECT, correctMap,
						leftBrace.getStart(),
						rightBrace.getEnd()));
			}
			else {
				symbols.push(lexemes.get(i));
			}
		}
		if (symbols.size() == 1) {
			Lexeme jsonObject = symbols.peek();
			System.out.println("jsonCorrect = " + jsonObject.getValue());
		}
		return symbols.size() == 1 && (
				symbols.get(0).getTokenType() == DataType.OBJECT
				|| symbols.get(0).getTokenType() == DataType.ARRAY
		);
	}

	private List<Lexeme> handlePair (Stack<Lexeme> symbols) {
		if (symbols.size() < 3) {
			throw new IllegalSymbolFoundException(String.format("JSON cannot be parsed: Expected at least 3 params, Found %s",
					symbols.size()));
		}
		Lexeme valueCandidate = symbols.pop();
		if (!isValueType(valueCandidate.getTokenType())) {
			throw new IllegalSymbolFoundException(String.format("JSON cannot be parsed: Expected one of the types %s, Found \"%s\"",
					Arrays.toString(DataType.values()),
					valueCandidate.getValue()));
		}
 		Lexeme colonCandidate = symbols.pop();
		if (colonCandidate.getTokenType() != Colon.COLON) {
			throw new IllegalSymbolFoundException(String.format("JSON cannot be parsed: Expected \"%s\", Found \"%s\"",
					Colon.COLON,
					colonCandidate.getValue()));
		}
		Lexeme keyCandidate = symbols.pop();
		if (keyCandidate.getTokenType() != DataType.STRING) {
			throw new IllegalSymbolFoundException(String.format("JSON cannot be parsed: key should be of the type \"%s\", Found \"%s\"",
					DataType.STRING,
					keyCandidate.getValue()));
		}
		return List.of(keyCandidate, valueCandidate);
	}

	private boolean isValueType(Token token) {
		for (Token dataType : DataType.values()) {
			if (token == dataType) {
				return true;
			}
		}
		return false;
	}

	private Map<String, Object> reverseMap(Map<String, Object> original) {
		List<String> reverseOrderKeys = new ArrayList<>(original.keySet());
		Collections.reverse(reverseOrderKeys);
		Map<String, Object> jsonCorrect = new LinkedHashMap<>();
		reverseOrderKeys.forEach(key -> jsonCorrect.put(key, original.get(key)));
		return jsonCorrect;
	}
}
