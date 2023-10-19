/*
 * @author: pagidimarri.nagendar
 * @createdAt: 17/10/23 5:45 pm
 */

package com.nagendar.learning.parser;

import com.nagendar.learning.lexer.Lexeme;

import java.util.LinkedHashMap;
import java.util.Map;

public class JsonObject {
	private final Map<String, Lexeme> jsonObjectAsMap;

	public JsonObject() {
		this.jsonObjectAsMap = new LinkedHashMap<>();
	}

	public Map<String, Lexeme> getJsonObjectAsMap() {
		return jsonObjectAsMap;
	}
}
