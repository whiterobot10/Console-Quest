package com.gamepsychos.ascii;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeListener;

public class ColorSelectorPanel extends JPanel implements MouseListener, ColorSelectionModel, Observer<Message> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Console console;
	private final JFrame colorSelector;
	private boolean setBackgroundColor = false;
	
	public ColorSelectorPanel(Console console){
		setPreferredSize(new Dimension(100, 100));
		this.setBackground(Color.gray);
		this.console = console;
		this.addMouseListener(this);
		colorSelector = new JFrame("Color Selector");
		
		JColorChooser chooser = new JColorChooser();
		chooser.setPreviewPanel(new JPanel());
		chooser.setSelectionModel(this);
		colorSelector.add(chooser);
		colorSelector.pack();
		console.regiserObserver(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		int size = (this.getWidth()*3)/4;
		
		Rectangle2D colorRect = new Rectangle2D.Double(5, 5, size, size);
		Rectangle2D backgroundRect = new Rectangle2D.Double(this.getWidth() - size - 5, 5+size/2, size, size);
		
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(2.0f));
		g2d.draw(colorRect);
		g2d.setColor(console.getColor());
		g2d.fill(colorRect);
		
		g2d.setColor(Color.BLACK);
		g2d.draw(backgroundRect);
		if(console.getBackgroundColor() == null){
			Line2D line0 = new Line2D.Double(this.getWidth() - size - 5, 5+size/2, this.getWidth() - 5, 5+size/2+size);
			Line2D line1 = new Line2D.Double(this.getWidth() - size - 5, 5+size/2+size, this.getWidth() - 5, 5+size/2);
			g2d.draw(line0);
			g2d.draw(line1);
		}else {
			g2d.setColor(console.getBackgroundColor());
			g2d.fill(backgroundRect);
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int size = (this.getWidth()*3)/4;
		
		Point2D point = new Point2D.Double(e.getX(), e.getY());
		

		// Check the background rectangle
		Rectangle2D backgroundRect = new Rectangle2D.Double(this.getWidth() - size - 5, 5+size/2, size, size);
		if(backgroundRect.contains(point)){
			
			// If we are right clicking, set color to transparent
			if(e.getButton() == MouseEvent.BUTTON3){
				console.setBackgroundColor(null);
				console.notifyObservers(Message.COLOR_CHANGE);
				return;
			}
			setBackgroundColor = true;
			colorSelector.setVisible(true);
			return;
		}
		
		
		// Check the foreground color rectangle
		Rectangle2D colorRect = new Rectangle2D.Double(5, 5, size, size);
		if (colorRect.contains(point)){
			setBackgroundColor = false;
			colorSelector.setVisible(true);
			return;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addChangeListener(ChangeListener arg0) {
		
	}

	@Override
	public Color getSelectedColor() {
		return null;
	}

	@Override
	public void removeChangeListener(ChangeListener arg0) {
		
	}

	@Override
	public void setSelectedColor(Color color) {
		if(setBackgroundColor){
			console.setBackgroundColor(color);
		}else {
			console.setColor(color);
		}
		
	}

	@Override
	public void update(Message message) {
		switch(message){
		case COLOR_CHANGE:
		case REPAINT:
			this.repaint();
			break;
		}
	}


}
