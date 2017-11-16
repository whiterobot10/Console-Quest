import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

public class Menu {
	private final ArrayList<MenuItem> arlMenu = new ArrayList<>();

	public Menu(MenuItem... items) {
		Collections.addAll(arlMenu, items);
	}

	public static void main(String args[]) {
		MenuItem item1 = new MenuItem("", null);
		MenuItem item2 = new MenuItem("", null);
		MenuItem item3 = new MenuItem("", null);
		MenuItem item4 = new MenuItem("", null);
		MenuItem item5 = new MenuItem("", null);
		Menu m = new Menu(item1, item2, item3, item4, item5);
	}

	public MenuItem getItem(int i) {
		return arlMenu.get(i);
	}

	public int getLength() {
		return arlMenu.size();
	}

	public void draw(Graphics g) {
		int i = 0;
		for (MenuItem m : arlMenu) {
			main.Console.setChars("(" + (main.iSelected == i + 1 ? "X) " : i+1+") ")+m.getLabel()+(main.iSelected == i + 1 ? " (SELECTED)" : ""), 20, 23 + i++);

		}

	}

}
