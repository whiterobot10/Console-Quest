import java.awt.Graphics;
import java.util.ArrayList;

public class Digimancer extends Entity {

	public Digimancer(boolean PC, int x, int y, Stat ATK, Stat DEF, Stat AJI, Stat DEX, Stat HPMAX) {
		super(PC, x, y, ATK, DEF, AJI, DEX, HPMAX);
		type="Digimancer";
	}

	void draw(Graphics g, ConsolePanel console) {

		if (sPose.equals("")) {
			sPose="Digimancy";

		}

		if (sPose.equals("MeleeAtk")) {
			sPose = "DigimancyAttack";

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
				sAttackType = "Digitack";
			}

			if (main.iOption == 2) {
				sAttackType = "Heal";
			}

		} else {
			ArrayList<String> arlAttackWeight = new ArrayList<String>();

			arlAttackWeight.add("Digitack");
				arlAttackWeight.add("Heal");


			sAttackType = arlAttackWeight.get(main.rng.nextInt(arlAttackWeight.size()));

		}
	}

}
