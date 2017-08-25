package com.gamepsychos.ascii;

import java.awt.Color;

public interface Console extends Observable<Message>{

	public void setChars(String chars, int c, int r);
	public void setChar(char ch, int c, int r);	
	public void setColor(Color color);
	public void setBackgroundColor(Color color);
	public void clear();
	public ConsoleCharacter getChar(int c, int r);
	public int getRows();
	public int getCols();
	public Color getColor();
	public Color getBackgroundColor();
	public void deleteChar(int col, int row);

}
