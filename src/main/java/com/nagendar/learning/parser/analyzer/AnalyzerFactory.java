/*
 * @author: pagidimarri.nagendar
 * @createdAt: 17/10/23 9:54 pm
 */

package com.nagendar.learning.parser.analyzer;

import com.nagendar.learning.lexer.Input;
import com.nagendar.learning.lexer.tokens.*;

import java.util.HashMap;
import java.util.Map;

public class AnalyzerFactory {
	private Map<Token, Analyzer> tokenAnalyzerMap;

	public AnalyzerFactory(Input input) {
		populateAnalyzers(input);
	}

	private void populateAnalyzers(Input input) {
		tokenAnalyzerMap = new HashMap<>();
		tokenAnalyzerMap.put(Brace.LEFT_BRACE, new LeftBraceAnalyzer(input, this));
		tokenAnalyzerMap.put(Brace.RIGHT_BRACE, new RightBraceAnalyzer(input, this));
		Analyzer valueAnalyzer = new ValueAnalyzer(input, this);
		tokenAnalyzerMap.put(DataType.STRING, valueAnalyzer);
		tokenAnalyzerMap.put(DataType.NUMBER, valueAnalyzer);
		tokenAnalyzerMap.put(DataType.BOOLEAN, valueAnalyzer);
		tokenAnalyzerMap.put(Colon.COLON, new ColonAnalyzer(input, this));
		tokenAnalyzerMap.put(SquareBracket.LEFT_SQUARE_BRACKET, new LeftBracketAnalyzer(input, this));
		tokenAnalyzerMap.put(SquareBracket.RIGHT_SQUARE_BRACKET, new RightBracketAnalyzer(input, this));
		tokenAnalyzerMap.put(Comma.COMMA, new CommaAnalyzer(input, this));
	}

	public Analyzer getAnalyzerForToken(Token token) {
		return tokenAnalyzerMap.get(token);
	}
}
