import java.awt.Graphics;
import java.util.ArrayList;

public class Big_Stampy extends Entity {

	public Big_Stampy(boolean PC, int x, int y, Stat ATK, Stat DEF, Stat AJI, Stat DEX, Stat HPMAX) {
		super(PC, x, y, ATK, DEF, AJI, DEX, HPMAX);
		type="Stompy";
		m = new Menu(new MenuItem("ME SMASH!",BasicAttack), new MenuItem("SMASH HARDER",RecklessAttack));
	}

	void draw(Graphics g, ConsolePanel console) {

		if (sPose.equals("")) {
			sPose="Human_Digimancy";

		}

		if (sPose.equals("MeleeAtk")) {
			sPose = "Human_Digimancy";

		}

		super.draw(g, console);
	}
	
public void EnemyAI(){
			ArrayList<String> arlAttackWeight = new ArrayList<String>();

			arlAttackWeight.add("Digitack");
			arlAttackWeight.add("Digitack");
			for(Entity e :main.arlEntities){
				if(!e.isPC&&e.hpMax.getValue()>e.hp)
				arlAttackWeight.add("Heal");}


			sAttackType = arlAttackWeight.get(main.rng.nextInt(arlAttackWeight.size()));

		}
	

}
