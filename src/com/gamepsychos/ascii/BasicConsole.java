package com.gamepsychos.ascii;

import java.util.HashSet;
import java.util.Set;

public class BasicConsole implements Console, Observable<Message> {

	private final int rows, cols;
	
	private final char[][] console;
	
	private final Set<Observer<Message>> observers;
	
	public BasicConsole(int rows, int cols){
		this.rows = rows;
		this.cols = cols;
		this.console = new char[this.rows][this.cols];
		this.observers = new HashSet<>();
		this.clear();
	}
	
	@Override
	public void setChars(String chars, int c, int r) {
		for(char ch : chars.toCharArray())
			setChar(ch, c++, r, false);
		notifyObservers(Message.REPAINT);
	}

	private void setChar(char ch, int c, int r, boolean notify){
		if(c > this.cols || c < 0 || r > this.rows || r < 0) return;
		console[r][c] = ch;
		notifyObservers(Message.REPAINT);
	}
	
	@Override
	public void setChar(char ch, int c, int r) {
		setChar(ch, c, r, true);
	}

	public void notifyObservers(Message m) {
		for(Observer<Message> o : observers){
			o.update(m);
		}
	}

	@Override
	public void clear() {
		for(int r = 0; r < this.rows; r++){
			for(int c = 0; c < this.cols; c++){
				this.console[r][c] = ' ';
			}
		}
	}

	@Override
	public char getChar(int c, int r) {
		if(c > this.cols || c < 0 || r > this.rows || r < 0) throw new ArrayIndexOutOfBoundsException();
		return this.console[r][c];
	}

	@Override
	public int getRows() {
		return rows;
	}

	@Override
	public int getCols() {
		return cols;
	}

	@Override
	public void regiserObserver(Observer<Message> observer) {
		observers.add(observer);
	}

}
