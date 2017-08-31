import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class main implements KeyListener {

	static boolean bEnter, bLeft, bRight, bUp, bDown;
	static int iOption, iSelected = 1, iMax, iDisplayChoice;
	static double SkillTesterOutput = 0;
	static List<SkillGame> arlSkillGames = Collections.synchronizedList(new ArrayList<SkillGame>());
	static List<Entity> arlEntities = Collections.synchronizedList(new ArrayList<Entity>());
	static List<Entity> arlAttackpattern = Collections.synchronizedList(new ArrayList<Entity>());
	static Random rng = new Random();
	static int kLeftKey = KeyEvent.VK_LEFT, kRightKey = KeyEvent.VK_RIGHT, kUpKey = KeyEvent.VK_UP,
			kDownKey = KeyEvent.VK_DOWN, kEnterKey = KeyEvent.VK_ENTER, kEnterAltKey = KeyEvent.VK_SHIFT;
	static ConsolePanel Console =new ConsolePanel(30,80);

	public static void main(String[] args) {
		Ascii_Frame.readFrames("new sun");
		Ascii_Frame.readFrames("human");
		Ascii_Frame.readFrames("stompy");
		
		
		

		
		arlEntities
				.add(new Big_Stampy(true, 8, 7, new Stat(15), new Stat(5), new Stat(50), new Stat(50), new Stat(50)));

		arlEntities
				.add(new Digimancer(true, 5, 17, new Stat(15), new Stat(5), new Stat(50), new Stat(50), new Stat(40)));
		
		arlEntities.add(new Blob(false, 40, 7, new Stat(15), new Stat(5), new Stat(50), new Stat(50), new Stat(50)));
		arlEntities.add(new Digimancer(false, 60, 7, new Stat(15), new Stat(5), new Stat(50), new Stat(50), new Stat(40)));
		arlEntities.add(new Blob(false, 35, 17, new Stat(15), new Stat(5), new Stat(50), new Stat(50), new Stat(50)));
		arlEntities.add(new Bat(false, 55, 17, new Stat(15), new Stat(5), new Stat(50), new Stat(50), new Stat(20)));
		GUIFunStuff.init();
		GUIFunStuff.j.setBackground(new Color(0, 0, 0));

		iOption = 0;
		// while (iOption == 0) {
		// necesaryStuff();
		// }
		// arlEntities.get(0).clearStats();
		//
		// if (iOption == 1) {
		// arlEntities.get(0).attack(arlEntities.get(1));
		// }
		// if (iOption == 2) {
		// arlEntities.get(0).atk.iThisTurnChange +=
		// arlEntities.get(0).atk.getValue();
		// arlEntities.get(0).dex.iThisTurnChange -=
		// arlEntities.get(0).dex.getValue() / 4;
		// arlEntities.get(0).attack(arlEntities.get(1));
		// }
		// if (iOption == 3) {
		// arlEntities.get(0).atk.iThisTurnChange +=
		// arlEntities.get(0).atk.getValue();
		// arlEntities.get(0).aji.iThisTurnChange -=
		// arlEntities.get(0).aji.getValue() / 2;
		// arlEntities.get(0).attack(arlEntities.get(1));
		// }
		// if (iOption == 4) {
		// arlEntities.get(0).def.iTempChange += 5;
		// arlEntities.get(0).def.iThisTurnChange += 5;
		// }
		// if (iOption == 5) {
		// arlEntities.get(0).hp+=5;
		// if(arlEntities.get(0).hp>arlEntities.get(0).hpMax.getValue()){
		// arlEntities.get(0).hp=arlEntities.get(0).hpMax.getValue();
		// }
		// }
		//
		// arlEntities.get(1).onTurn();
		while (true) {

			if (arlAttackpattern.isEmpty()) {

				for (Entity e : arlEntities) {
					// e.setTargetAndAttackType();
					arlAttackpattern.add(e);
				}
			}

			for (int i = 0; i < main.arlEntities.size(); i++) {
				if (main.arlEntities.get(i).bIsAlive == false) {
					main.arlAttackpattern.remove(main.arlEntities.get(i));
					main.arlEntities.remove(i);
					i--;
				}
			}

			for (Entity e : arlEntities) {
				e.setTargetAndAttackType();
				e.attack();

			}

			necesaryStuff();

		}

	}

	static void necesaryStuff() {

		if (!(arlSkillGames.isEmpty())) {
			arlSkillGames.get(0).main();
		}
		for (Entity e : arlEntities) {
			if (e.hp <= 0) {
				e.bIsAlive = false;
			}
		}

		if (iOption == 0) {
			if (iDisplayChoice == 1) {
				if (bUp) {
					iSelected -= 1;
					bUp = false;
				}
				if (bDown) {
					iSelected += 1;
					bDown = false;
				}
			}
			if (iDisplayChoice == 2) {
				if (bLeft) {
					iSelected -= 1;
					bLeft = false;
				}
				if (bRight) {
					iSelected += 1;
					bRight = false;
				}
			}
			if (iSelected == 0) {
				iSelected = iMax;
			}
			if (iSelected > iMax) {
				iSelected = 1;
			}
			if (bEnter) {
				iOption = iSelected;
				iSelected = 1;
				bEnter = false;
				iDisplayChoice = 0;
			}

		}
		GUIFunStuff.j.repaint();
		sleep(2);

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
		bEnter = e.getKeyCode() == kEnterKey;
		bLeft = e.getKeyCode() == kLeftKey;
		bRight = e.getKeyCode() == kRightKey;
		bUp = e.getKeyCode() == kUpKey;
		bDown = e.getKeyCode() == kDownKey;

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
