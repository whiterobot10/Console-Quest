import java.awt.Graphics;
import java.util.ArrayList;

public class Bat extends Entity {
	int flap = 0;

	public Bat(boolean PC, int x, int y, Stat ATK, Stat DEF, Stat AJI, Stat DEX, Stat HPMAX) {
		super(PC, x, y, ATK, DEF, AJI, DEX, HPMAX);
		type="Bat";
	}

	void draw(Graphics g, ConsolePanel console) {
		flap++;
		if (flap > 100) {
			flap = 0;
		}
		if (sPose.equals("")||sPose.equals("BatA")||sPose.equals("BatB")) {
			if (flap < 50) {
				sPose = "BatA";
			} else {
				sPose = "BatB";
			}
		}
		if(sPose.equals("Hit")){
			sPose="BatHit";
		}
		if(sPose.equals("MeleeAtk")){
			sPose="BatAttack";
			if(sAttackEffect.equals("Disoreant")){
				sPose="BatSonic";
			}
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
				sAttackType = "Screech";
			}

		} else {
			ArrayList<String> arlAttackWeight = new ArrayList<String>();

			arlAttackWeight.add("Basic");
				arlAttackWeight.add("Screech");


			sAttackType = arlAttackWeight.get(main.rng.nextInt(arlAttackWeight.size()));

		}
	}

}
