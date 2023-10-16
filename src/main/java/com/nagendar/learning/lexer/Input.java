/*
 * @author: pagidimarri.nagendar
 * @createdAt: 16/10/23 3:43 pm
 */

package com.nagendar.learning.lexer;

public class Input {
	private String input;
	private int index;

	public Input(String input) {
		this.input = input;
		this.index = 0;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getInput() {
		return input;
	}

	public int getIndex() {
		return index;
	}
}
