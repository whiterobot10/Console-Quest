import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ConsolePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2816644692496128732L;
	char Console[][];

	public ConsolePanel(int _Rows, int _Cols) {
		Console = new char[_Rows][_Cols];
		clear();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (char[] c : Console) {
			for (char c2 : c) {
				sb.append(c2);
			}
			sb.append("\n");
		}
		return sb.toString();

	}

	public void setChars(String chars, int c, int r) {
		if (chars.equals("")) {
			return;
		}
		if (chars.length() == 1) {
			setChar(chars.charAt(0), c, r);
		} else {
			setChar(chars.charAt(0), c, r);
			setChars(chars.substring(1), ++c, r);
		}
	}

	public void setChar(char ch, int c, int r) {
		if (r < Console.length && c < Console[r].length && ch != ' ') {
			if (ch == 'º') {
				if (main.rng.nextInt(3) == 0) {
					ch = '¡';
				} else {
					ch = ' ';
				}
			}
			if (ch == '¡') {
				if (main.rng.nextBoolean()) {
					ch = '1';
				} else {
					ch = '0';
				}
			}
			if (ch == 'ƒ') {
				int i = main.rng.nextInt(15);
				ch = Integer.toHexString(i).charAt(0);
			}
			Console[r][c] = ch;
			if (ch == '–') {
				Console[r][c] = ' ';
			}

		}
	}

	public static void main(String[] args) {
		ConsolePanel test = new ConsolePanel(10, 10);
		test.setChars("pie", 0, 0);
		test.setChars("pie", 1, 0);
		test.setChars("pie", 0, 3);
		test.setChars("pie", 9, 0);
		test.setChars("pie", 0, 10);
		test.setChars("pie", 0, 9);
		JFrame f = new JFrame("test");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Make everything visible

		f.setVisible(true);
		f.add(test);
		test.setPreferredSize(new Dimension(12 * test.Console[0].length + 10, 20 * test.Console.length + 10));
		f.pack();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);

	}

	public void clear() {
		for (int i = 0; i < Console.length; i++) {
			for (int i2 = 0; i2 < Console[i].length; i2++) {
				Console[i][i2] = ' ';
			}
		}
	}

	public void draw(Graphics g) {
		g.setFont(new Font("Monaco", Font.PLAIN, 20));
		g.setColor(Color.green);
		int i = 20;
		for (char[] c : Console) {

			g.drawString(new String(c), 5, i);
			i += 20;
		}

	}

}
