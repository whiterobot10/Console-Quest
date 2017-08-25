package com.gamepsychos.ascii;

import java.awt.Color;

public class ConsoleCharacter {

	private final Color color;
	private final Color background;
	private final char ch;
	
	public ConsoleCharacter(char ch, Color color, Color background){
		this.ch = ch;
		this.color = color;
		this.background = background;
	}

	public Color getColor() {
		return color;
	}

	public boolean hasBackground() {
		return background != null;
	}
	
	public Color getBackground() {
		return background;
	}

	public char getCharacter() {
		return ch;
	}
	
}
