package com.gamepsychos.ascii;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class EditorPanel extends JPanel
		implements Console, MouseListener, MouseMotionListener, KeyListener, Observer<Message> {

	private final Map<Position, AsciiFrame> frames;
	private int rows, cols;
	private final Console console;
	private Font font;
	private FontMetrics fontMetrics;
	private int fontHeight, fontWidth;
	private Cursor cursor;

	private int firstRow, firstCol, secondRow, secondCol;

	private boolean[][] selected;

	private final Set<Integer> pressedKeys;
	private final Set<Position> tentativeSelect;
	
	private final JMenuBar menu;

	
	public JMenuBar getMenuBar(){
		return menu;
		
	}
	public Color getColor() {
		return console.getColor();
	}

	public Color getBackgroundColor() {
		return console.getBackgroundColor();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EditorPanel(int rows, int cols, String fontName) {
		this.rows = rows;
		this.cols = cols;
		this.console = new BasicConsole(rows, cols);
		this.selected = new boolean[rows][cols];
		this.console.regiserObserver(this);
		this.cursor = new Cursor(this);
		this.pressedKeys = new TreeSet<>();
		this.tentativeSelect = new HashSet<>();
		this.frames = new HashMap<>();
		setFont(fontName);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setBackground(Color.BLACK);
		
		menu = initMenu();
	}

	private JMenuBar initMenu(){
		JMenuBar menubar = new JMenuBar();
		
		JMenu file = new JMenu("File");
		menubar.add(file);
		file.setMnemonic('F');
		
		JMenuItem exit = new JMenuItem("Exit");
		file.add(exit);
		exit.setMnemonic('x');
		exit.addActionListener(e -> AsciiFrameEditor.exit(0));
		
		
		JMenu frame = new JMenu("Frame");
		menubar.add(frame);
		frame.setMnemonic('r');
		
		JMenuItem create = new JMenuItem("Create Frame From Selection");
		frame.add(create);
		create.setMnemonic('C');
		create.addActionListener(e -> createFrame());
		
		JMenuItem load = new JMenuItem("Load Frame From File");
		frame.add(load);
		load.setMnemonic('L');
		
		return menubar;
	}
	
	private void createFrame() {
		int minRow = Integer.MAX_VALUE;
		int maxRow = Integer.MIN_VALUE;
		int minCol = Integer.MAX_VALUE;
		int maxCol = Integer.MIN_VALUE;
		Map<Position, ConsoleCharacter> tempCharacters = new HashMap<>();
		for(Position p : getSelected()){
			minRow = Math.min(p.row, minRow);
			maxRow = Math.max(p.row, maxRow);
			minCol = Math.min(p.col, minCol);
			maxCol = Math.max(p.col, maxCol);
			tempCharacters.put(p, console.getChar(p.col, p.row));
		}
		
		int rows = maxRow - minRow + 1;
		int cols = maxCol - minCol + 1;
		Map<Position, ConsoleCharacter> characters = new HashMap<>();
		for(Entry<Position, ConsoleCharacter> e : tempCharacters.entrySet()){
			Position p = e.getKey();
			ConsoleCharacter ch = e.getValue();
			characters.put(new Position(p.row-minRow, p.col-minCol), ch);
			console.deleteChar(p.col, p.row);
		}
		
		AsciiFrame frame = new AsciiFrame(rows, cols, characters);
		frames.put(new Position(minRow, minCol), frame);
	}
	
	private void setFont(String fontName) {
		this.font = new Font(fontName, Font.PLAIN, 20);
		Canvas c = new Canvas();
		fontMetrics = c.getFontMetrics(this.font);
		fontHeight = fontMetrics.getHeight();
		int[] widths = fontMetrics.getWidths();
		Arrays.sort(widths);
		// Takes the Median value... Should probably take the Mode
		fontWidth = widths[widths.length / 2];
		this.setPreferredSize(new Dimension(fontWidth * cols, fontHeight * rows));
	}

	public EditorPanel(int rows, int cols) {
		this(rows, cols, "Monospaced");
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setFont(this.font);

		for (int r = 0; r < this.rows; r++) {
			for (int c = 0; c < this.cols; c++) {
				ConsoleCharacter ch = this.console.getChar(c, r);
				g2d.translate(c*fontWidth, r*fontHeight);
				ch.draw(g2d, fontWidth, fontHeight, fontMetrics.getDescent());
				g2d.translate(-c*fontWidth, -r*fontHeight);
			}
		}

		Set<Position> toDraw = new HashSet<>();
		toDraw.addAll(getSelected());
		toDraw.addAll(tentativeSelect);
		for (Position p : toDraw) {
			int r = p.row;
			int c = p.col;
			g2d.setColor(console.getColor());
			g2d.drawRect(c * this.fontWidth, r * this.fontHeight + this.fontMetrics.getDescent(), this.fontWidth,
					this.fontHeight);
			Composite original = g2d.getComposite();
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.15f));
			g2d.fillRect(c * this.fontWidth, r * this.fontHeight + this.fontMetrics.getDescent(), this.fontWidth,
					this.fontHeight);
			g2d.setComposite(original);
		}
		
		for(Entry<Position, AsciiFrame> e : frames.entrySet()){
			int offsetX = e.getKey().col * fontWidth;
			int offsetY = e.getKey().row * fontHeight;
			AsciiFrame frame = e.getValue();
			int height = frame.getRows() * fontHeight;
			int width =frame.getCols() * fontWidth;
			g2d.translate(offsetX, offsetY);
			frame.draw(g2d, fontWidth, fontHeight, fontMetrics.getDescent());
			g2d.translate(-offsetX, -offsetY);
			Rectangle2D border = new Rectangle2D.Double(offsetX, offsetY + fontMetrics.getDescent(), width, height);
			g2d.draw(border);
		}

	}

	public void setColor(Color color) {
		console.setColor(color);
	}

	public void setBackgroundColor(Color color) {
		console.setBackgroundColor(color);
	}

	public ConsoleCharacter getChar(int c, int r) {
		return console.getChar(c, r);
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

	public int getRows() {
		return console.getRows();
	}

	public int getCols() {
		return console.getCols();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = toRow(e.getY()), col = toCol(e.getX());

		selectPosition(row, col);

		if (e.getClickCount() == 2) {
			ConsoleCharacter ch = this.console.getChar(this.cursor.getCol(), this.cursor.getRow());

			if (e.getButton() == MouseEvent.BUTTON3) {
				this.console.setBackgroundColor(ch.getBackground());
			} else {

				this.console.setColor(ch.getColor());
			}
		}

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
		firstRow = toRow(e.getY());
		firstCol = toCol(e.getX());
		selectPosition(firstRow, firstCol);
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		for (Position p : tentativeSelect) {
			if (p.row < 0 || p.row > selected.length)
				return;
			if (p.col < 0 || p.col > selected[p.row].length)
				return;
			
			if (pressedKeys.contains(KeyEvent.VK_CONTROL)) {
				selected[p.row][p.col] = false;
			} else {
				selected[p.row][p.col] = true;
			}

		}
		tentativeSelect.clear();
		notifyObservers(Message.REPAINT);
	}

	@Override
	public void keyPressed(KeyEvent e) {

		char ch = e.getKeyChar();
		pressedKeys.add(e.getKeyCode());
		switch (e.getKeyCode()) {

		case KeyEvent.VK_LEFT:
			this.cursor.decrementCol();
			selectAtCursor();
			break;

		case KeyEvent.VK_RIGHT:
			this.cursor.incrementCol();
			selectAtCursor();
			break;

		case KeyEvent.VK_UP:
			this.cursor.decrementRow();
			selectAtCursor();
			break;

		case KeyEvent.VK_DOWN:
			this.cursor.incrementRow();
			selectAtCursor();
			break;

		case KeyEvent.VK_BACK_SPACE:
		case KeyEvent.VK_DELETE:
			for (Position p : getSelected()) {
				this.console.deleteChar(p.col, p.row);
			}
			break;

		case KeyEvent.VK_CONTROL:
		case KeyEvent.VK_SHIFT:
		case KeyEvent.VK_WINDOWS:
		case KeyEvent.VK_ALT:
			break;

		default:
			for (Position p : getSelected()) {
				this.console.setChar(ch, p.col, p.row);
			}
		}

	}

	private void selectAtCursor() {
		selectPosition(cursor.getRow(), cursor.getCol());
	}

	public void deleteChar(int col, int row) {
		console.deleteChar(col, row);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		pressedKeys.remove(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Message message) {

		switch (message) {
		case COLOR_CHANGE:
			char ch = console.getChar(cursor.getCol(), cursor.getRow()).getCharacter();
			for (Position p : getSelected()) {
				console.setChar(ch, p.col, p.row);
			}
		case REPAINT:
			this.repaint();
			break;
		}
	}

	private final Set<Position> getSelected() {
		Set<Position> positions = new HashSet<>();
		for (int r = 0; r < selected.length; r++) {
			for (int c = 0; c < selected[r].length; c++) {
				if (selected[r][c]) {
					positions.add(new Position(r, c));
				}
			}
		}
		return positions;
	}

	public void regiserObserver(Observer<Message> observer) {
		console.regiserObserver(observer);
	}

	public void notifyObservers(Message msg) {
		console.notifyObservers(msg);
	}

	private final int toRow(int y) {
		return y / fontHeight;
	}

	private final int toCol(int x) {
		return x / fontWidth;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		secondRow = toRow(e.getY());
		secondCol = toCol(e.getX());

		int left = Math.min(firstCol, secondCol);
		int right = Math.max(firstCol, secondCol);
		int top = Math.min(firstRow, secondRow);
		int bottom = Math.max(firstRow, secondRow);
		tentativeSelect(top, left, right, bottom);
		notifyObservers(Message.REPAINT);
	}

	private void tentativeSelect(int top, int left, int right, int bottom) {
		tentativeSelect.clear();
		for (int r = top; r <= bottom; r++) {
			for (int c = left; c <= right; c++) {
				tentativeSelect.add(new Position(r, c));
			}
		}
	}

	private void selectPosition(int row, int col) {
		if (row > selected.length)
			return;

		if (col > selected[row].length)
			return;

		if (!(pressedKeys.contains(KeyEvent.VK_SHIFT) || pressedKeys.contains(KeyEvent.VK_CONTROL)))
			unselectAll();

		if (pressedKeys.contains(KeyEvent.VK_CONTROL)) {
			selected[row][col] = false;
		} else {
			selected[row][col] = true;
		}
		this.cursor.setLocation(row, col);
	}

	private void unselectAll() {
		selected = new boolean[rows][cols];
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

}
