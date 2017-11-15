import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class main implements KeyListener {

	static boolean bZ,bX,bC, bLeft, bRight, bUp, bDown;
	static int kZ,kX,kC,kUp,kDown,kLeft,kRight;


	public static void main(String[] args) {
		
		while (true) {

	}



	}

	public static void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		bZ = e.getKeyCode() == kZ;
		bX = e.getKeyCode() == kX;
		bC = e.getKeyCode() == kC;
		bLeft = e.getKeyCode() == kLeft;
		bRight = e.getKeyCode() == kRight;
		bUp = e.getKeyCode() == kUp;
		bDown = e.getKeyCode() == kDown;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		bZ = e.getKeyCode() == kZ;
		bX = e.getKeyCode() == kX;
		bC = e.getKeyCode() == kC;
		bLeft = e.getKeyCode() == kLeft;
		bRight = e.getKeyCode() == kRight;
		bUp = e.getKeyCode() == kUp;
		bDown = e.getKeyCode() == kDown;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
