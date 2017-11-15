package com.gamepsychos.ascii;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AsciiFrame {

	private int rows, cols;
	private int offsetX, offsetY;
	private final Map<Position, ConsoleCharacter> characters;

	public AsciiFrame(int rows, int cols, Map<Position, ConsoleCharacter> characters) {
		this.rows = rows;
		this.cols = cols;
		this.characters = new HashMap<>(characters);
	}

	public AsciiFrame(int rows, int cols) {
		this(rows, cols, new HashMap<>());
	}

	public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public void deleteCharacter(int row, int col) {
		characters.remove(new Position(row, col));
	}

	public void setCharacter(int row, int col, ConsoleCharacter ch) {
		characters.put(new Position(row, col), ch);
	}

	public Set<Entry<Position, ConsoleCharacter>> getCharacters() {
		return characters.entrySet();
	}

	public void draw(Graphics2D g2d, int fontWidth, int fontHeight, int fontDescent) {
		for (Entry<Position, ConsoleCharacter> e : characters.entrySet()) {
			Position p = e.getKey();
			int r = p.row;
			int c = p.col;
			ConsoleCharacter ch = e.getValue();
			g2d.translate(c*fontWidth, r*fontHeight);
			ch.draw(g2d, fontWidth, fontHeight, fontDescent);
			g2d.translate(-c*fontWidth, -r*fontHeight);			
		}
	}

}
