/*
 * @author: pagidimarri.nagendar
 * @createdAt: 17/10/23 5:48 pm
 */

package com.nagendar.learning.parser.analyzer;

import com.nagendar.learning.parser.TokenBase;

public interface Analyzer {
	void parse(TokenBase tokenBase);
	void setNextAnalyzer(Analyzer analyzer);
}
