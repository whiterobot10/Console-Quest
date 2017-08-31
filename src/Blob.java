import java.awt.Graphics;
import java.util.ArrayList;

public class Blob extends Entity {

	public Blob(boolean PC, int x, int y, Stat ATK, Stat DEF, Stat AJI, Stat DEX, Stat HPMAX) {
		super(PC, x, y, ATK, DEF, AJI, DEX, HPMAX);
		type="Blob";
		m = new Menu(new MenuItem("Basic Attack"), new MenuItem("Reckless Attack"));
	}

	void draw(Graphics g, ConsolePanel console) {
		if (sPose.equals("MeleeAtk")) {
			sPose = "BlobAttack";

		} else if (sPose.equals("Hit")) {
			sPose = "BlobHit";
		} else if (sPose.equals("")) {
			sPose = "Blob";
		}
		super.draw(g, console);
	}

	void SelectAttack() {
		if (isPC) {
			main.iOption = 0;
			main.iMax = 2;
			main.iDisplayChoice = 1;
			while (main.iOption == 0) {
				main.necesaryStuff();
			}

			if (main.iOption == 1) {
				sAttackType = "Basic";
			}

			if (main.iOption == 2) {
				sAttackType = "Reckless";
			}

		} else {
			ArrayList<String> arlAttackWeight = new ArrayList<String>();

			arlAttackWeight.add("Basic");
			if (hp >= 35) {
				arlAttackWeight.add("Reckless");

			}
			if (hp <= 20) {
				arlAttackWeight.add("Reckless");
				arlAttackWeight.add("Reckless");
				arlAttackWeight.add("Reckless");
			}
			if (hp <= 10) {
				arlAttackWeight.clear();
				arlAttackWeight.add("Reckless");
			}

			sAttackType = arlAttackWeight.get(main.rng.nextInt(arlAttackWeight.size()));

		}
	}

}
