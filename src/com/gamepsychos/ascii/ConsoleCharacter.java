package com.gamepsychos.ascii;

import java.awt.Color;
import java.awt.Graphics2D;

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

	public void draw(Graphics2D g2d, int fontWidth, int fontHeight, int fontDescent) {
		if (hasBackground()) {
			g2d.setColor(getBackground());
			g2d.fillRect(0, fontDescent, fontWidth, fontHeight);
		}

		g2d.setColor(getColor());
		g2d.drawString("" + getCharacter(), 0, fontHeight);	
	}
	
}
