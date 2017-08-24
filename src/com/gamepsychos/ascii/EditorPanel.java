package com.gamepsychos.ascii;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.JPanel;

public class EditorPanel extends JPanel implements Console, MouseListener, KeyListener, Observer<Message> {

	private int rows, cols;
	private final Console console;
	private Font font;
	private FontMetrics fontMetrics;
	private int fontHeight, fontWidth;
	
	private Cursor cursor;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public EditorPanel(int rows, int cols, String fontName){
		this.rows = rows;
		this.cols = cols;
		this.console = new BasicConsole(rows, cols);
		this.console.regiserObserver(this);
		this.cursor = new Cursor(this);
		setFont(fontName);
		this.addMouseListener(this);
	}
	
	private void setFont(String fontName){
		this.font = new Font("Monaco", Font.PLAIN, 20);
		Canvas c = new Canvas();
		fontMetrics = c.getFontMetrics(this.font);
		fontHeight = fontMetrics.getHeight();
		int[] widths = fontMetrics.getWidths();
		Arrays.sort(widths);
		fontWidth = widths[widths.length/2]+2;
		this.setPreferredSize(new Dimension(fontWidth * cols, fontHeight * rows));
	}
	
	public EditorPanel(int rows, int cols){
		this(rows, cols, "Monaco");	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(this.font);
		for(int r = 0; r < this.rows; r++){
			for(int c = 0; c < this.cols; c++){
				g.drawString(""+this.console.getChar(c, r), c*this.fontWidth, (r+1)*this.fontHeight);
			}
		}
		
		g.drawRect(this.cursor.getCol() * this.fontWidth, this.cursor.getRow()* this.fontHeight + this.fontMetrics.getDescent(), this.fontWidth, this.fontHeight);
		
	}

	public void setChars(String chars, int c, int r) {
		console.setChars(chars, c, r);
	}

	public void setChar(char ch, int c, int r) {
		console.setChar(ch, c, r);
	}

	public void clear() {
		console.clear();
	}

	public char getChar(int c, int r) {
		return console.getChar(c, r);
	}
	
	

	public int getRows() {
		return console.getRows();
	}

	public int getCols() {
		return console.getCols();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.cursor.setLocation(e.getY()/this.fontHeight, e.getX()/this.fontWidth);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		char ch = e.getKeyChar();
		switch(e.getKeyCode()){
		
		case KeyEvent.VK_LEFT:
			this.cursor.decrementCol();
			break;
		
		case KeyEvent.VK_RIGHT:
			this.cursor.incrementCol();
			break;
			
		case KeyEvent.VK_UP:
			this.cursor.decrementRow();
			break;
			
		case KeyEvent.VK_DOWN:
			this.cursor.incrementRow();
			break;
		
			default:
			this.cursor.setChar(ch);
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(Message message) {

		switch(message){
		case REPAINT:
			this.repaint();
			break;
		}
	}

	public void regiserObserver(Observer<Message> observer) {
		console.regiserObserver(observer);
	}

	public void notifyObservers(Message msg) {
		console.notifyObservers(msg);
	}
	
}
