import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIFunStuff {
	static JFrame f = new JFrame("...");
	static JPanel j = new DrawingPanel();
	public static void init() {

		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Make everything visible
		f.add(j, BorderLayout.CENTER);
				f.setVisible(true);
				f.setResizable(false);
				
				f.addKeyListener((KeyListener) new main());		
				// Makes the frame listen to the keyboard
				f.requestFocus();
		
				j.setPreferredSize(new Dimension(970,610));
		
		// Tell the frame to resize itself to the most convenient size
		f.pack();
		
		
	}

}
