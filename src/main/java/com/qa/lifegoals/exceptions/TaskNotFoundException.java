package com.qa.lifegoals.exceptions;

import javax.swing.text.BadLocationException;

public class TaskNotFoundException extends BadLocationException {

	public TaskNotFoundException(String s, int offs) {
		super(s, offs);
		// TODO Auto-generated constructor stub
	}

}
