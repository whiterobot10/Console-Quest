import java.awt.Graphics;
import java.util.ArrayList;

public class Big_Stampy extends Entity {

	public Big_Stampy(boolean PC, int x, int y, Stat ATK, Stat DEF, Stat AJI, Stat DEX, Stat HPMAX) {
		super(PC, x, y, ATK, DEF, AJI, DEX, HPMAX);
		type="Stompy";
		m = new Menu(new MenuItem("ME SMASH",BasicAttack), new MenuItem("ME SMASH!",RecklessAttack));
	}

	void draw(Graphics g, ConsolePanel console) {

		if (sPose.equals("Human_Still")) {
			sPose="Stompy_Still";
		}
		if (sPose.equals("Human_Hit")) {
			sPose="Stompy_Still";
		}
		if (sPose.equals("Human_Walk")) {
			sPose="Stompy_Walk";
		}
		if (sPose.equals("Human_Melee_Attack")) {
			sPose = "Stompy_Melee_Atk";

		}

		super.draw(g, console);
	}
	
public void EnemyAI(){
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
