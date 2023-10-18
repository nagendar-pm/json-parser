/*
 * @author: pagidimarri.nagendar
 * @createdAt: 17/10/23 7:21 pm
 */

package com.nagendar.learning.parser.analyzer;

import com.nagendar.learning.lexer.Input;
import com.nagendar.learning.lexer.tokens.Colon;
import com.nagendar.learning.parser.TokenBase;

public class KeyStringAnalyzer extends Analyzer {

	public KeyStringAnalyzer(Input input, AnalyzerFactory analyzerFactory) {
		super(input, analyzerFactory);
		setNextAnalyzer();
	}

	@Override
	public void analyze(TokenBase tokenBase) {

	}

	@Override
	public void setNextAnalyzer() {
		nextPotentialCharacters.put(Colon.COLON, ":");
	}
}
