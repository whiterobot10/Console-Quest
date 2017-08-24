package com.gamepsychos.ascii;

public interface Console extends Observable<Message>{

	public void setChars(String chars, int c, int r);
	public void setChar(char ch, int c, int r);	
	public void clear();
	public char getChar(int c, int r);
	public int getRows();
	public int getCols();

}
