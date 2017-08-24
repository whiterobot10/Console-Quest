package com.gamepsychos.ascii;

public class Cursor {
	
	private int row, col;
	private Console console;
	
	
	public Cursor(Console c){
		this.row = 0;
		this.col = 0;
		this.console = c;
	}
	
	public void setLocation(int row, int col){
		this.row = Math.min(console.getRows()-1, Math.max(row, 0));
		this.col = Math.min(console.getCols()-1, Math.max(col, 0));
		this.console.notifyObservers(Message.REPAINT);
	}
	
	public void shiftRow(int i){
		setLocation(row+i, col);
	}
	
	public void incrementRow(){
		shiftRow(1);
	}
	
	public void decrementRow(){
		shiftRow(-1);
	}
	
	public void shiftCol(int i){
		setLocation(row, col+i);
	}

	public void incrementCol(){
		shiftCol(1);
	}
	
	public void decrementCol(){
		shiftCol(-1);
	}
	
	public void setChar(char ch){
		console.setChar(ch, col, row);
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public Console getConsole() {
		return console;
	}

	public void setConsole(Console console) {
		this.console = console;
	}
	
	
}


