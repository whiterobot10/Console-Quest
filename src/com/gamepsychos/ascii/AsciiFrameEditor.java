package com.gamepsychos.ascii;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class AsciiFrameEditor {

	public static void main(String ... args){
		
		JFrame frame = new JFrame("Ascii Editor");
		EditorPanel editorPanel = new EditorPanel(24, 80);
		
		
		
		frame.add(editorPanel, BorderLayout.CENTER);
		frame.addKeyListener(editorPanel);
		
		frame.pack();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.requestFocus();
		
	}
	
}
