/*
 * @author: pagidimarri.nagendar
 * @createdAt: 17/10/23 9:54 pm
 */

package com.nagendar.learning.parser.analyzer;

import com.nagendar.learning.lexer.tokens.*;

import java.util.HashMap;
import java.util.Map;

public class AnalyzerFactory {
	private Map<Token, Analyzer> tokenAnalyzerMap;

	public AnalyzerFactory() {
		populateAnalyzers();
	}

	private void populateAnalyzers() {
		tokenAnalyzerMap = new HashMap<>();
		tokenAnalyzerMap.put(Brace.LEFT_BRACE, new LeftBraceAnalyzer());
		tokenAnalyzerMap.put(Brace.RIGHT_BRACE, new RightBraceAnalyzer());
		Analyzer valueAnalyzer = new ValueAnalyzer();
		tokenAnalyzerMap.put(DataType.STRING, valueAnalyzer);
		tokenAnalyzerMap.put(DataType.NUMBER, valueAnalyzer);
		tokenAnalyzerMap.put(DataType.BOOLEAN, valueAnalyzer);
		tokenAnalyzerMap.put(Colon.COLON, new ColonAnalyzer());
		tokenAnalyzerMap.put(SquareBracket.LEFT_SQUARE_BRACKET, new LeftBracketAnalyzer());
		tokenAnalyzerMap.put(SquareBracket.RIGHT_SQUARE_BRACKET, new RightBracketAnalyzer());
		tokenAnalyzerMap.put(Comma.COMMA, new CommaAnalyzer());
	}

	public Analyzer getAnalyzerForToken(Token token) {
		return tokenAnalyzerMap.get(token);
	}
}
