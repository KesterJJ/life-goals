package com.qa.lifegoals.exceptions;

import javax.swing.text.BadLocationException;

public class GoalNotFoundException extends BadLocationException {

	public GoalNotFoundException(String s, int offs) {
		super(s, offs);
		// TODO Auto-generated constructor stub
	}
}
