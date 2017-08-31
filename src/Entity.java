import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Entity {
	Stat atk, def, aji, dex, hpMax;
	int hp, iXpos, iYpos, iHealthOffset, iXAnchor, iYAnchor, iXMark, iYMark, attackOffset;
	boolean isPC, bAttackInProcess = false, bIsAlive = true, bAttackThisTurn, bTargetFriendly, meleeAttack = false;
	Entity eOpponent = null;
	String type;
	String sPose = "Human_Still", sAttackType, sAttackEffect;
	Menu m = new Menu();
	Action BasicAttack= new Action() {
		public void performAction() {
			sAttackType = "Basic";
		}
	};
	Action RecklessAttack= new Action() {
		public void performAction() {
			sAttackType = "Reckless";
		}
	};
	Action SonicAttack= new Action() {
		public void performAction() {
			sAttackType = "Sonic";
		}
	};
	Action Defend= new Action() {
		public void performAction() {
			sAttackType = "Defend";
		}
	};
	Action Healz= new Action() {
		public void performAction() {
			sAttackType = "Heal";
		}
	};
	Action DigitalAttack= new Action() {
		public void performAction() {
			sAttackType = "Digitack";
		}
	};

	public Entity(boolean PC, int x, int y, Stat ATK, Stat DEF, Stat AJI, Stat DEX, Stat HPMAX) {
		atk = ATK;
		def = DEF;
		aji = AJI;
		dex = DEX;
		hpMax = HPMAX;
		hp = HPMAX.getValue();
		isPC = PC;
		iXpos = x;
		iYpos = y;
		iXAnchor = iXpos;
		iYAnchor = iYpos;
		type = "Entity";
		attackOffset = 4;

		m = new Menu(new MenuItem("Basic Attack"), new MenuItem("Reckless Attack"), new MenuItem("Defend"));

	}

	public Entity(boolean PC, int x, int y, Stat ATK, Stat DEF, Stat AJI, Stat DEX, Stat HPMAX, int remainingHealth) {
		this(PC, x, y, ATK, DEF, AJI, DEX, HPMAX);

		hp = remainingHealth;

	}
	
	public void EnemyAI(){
		ArrayList<String> arlAttackWeight = new ArrayList<String>();

		arlAttackWeight.add("Basic");
		if (def.iTempChange <= 0) {
			arlAttackWeight.add("ArmorUp");
		}
		// if (hp <= 20) {
		// arlAttackWeight.add("Heal");
		// }
		// if (hp <= 10) {
		// arlAttackWeight.add("Heal");
		// arlAttackWeight.add("Heal");
		// }
		if (hp >= 40) {
			arlAttackWeight.add("Reckless");
			arlAttackWeight.add("Reckless");
		}

		sAttackType = arlAttackWeight.get(main.rng.nextInt(arlAttackWeight.size()));

		
	}

	public void attack() {
		if (main.arlAttackpattern.isEmpty() == false) {
			if (main.arlAttackpattern.get(0) == this && eOpponent != null && bIsAlive) {

				main.SkillTesterOutput = -1;
				main.arlSkillGames.add(new SkillGame(iXpos - 4, iYpos - 5, 10000, eOpponent.aji.getValue()));

				while (main.SkillTesterOutput == -1) {
					main.necesaryStuff();
				}
				if (meleeAttack) {
					moveToPos(isPC ? eOpponent.iXpos - attackOffset : eOpponent.iXpos + attackOffset, eOpponent.iYpos,
							200);
				}
				sPose = "Human_Melee_Attack";

				if (sAttackType != null && sAttackType.equals("Digitack")) {
					sPose = "Human_Digimancer_Action";
				}
				if (isPC) {
					if (dex.getValue() * main.SkillTesterOutput > eOpponent.aji.getValue()
							&& atk.getValue() - eOpponent.def.getValue() > 0) {

						onHit();

					}
					attackAnim();

					// System.out.println(this + " " + hp);
					// System.out.println(eOpponent + " " + eOpponent.hp);

				} else {
					if (dex.getValue() > eOpponent.aji.getValue() * main.SkillTesterOutput
							&& atk.getValue() - eOpponent.def.getValue() > 0) {

						onHit();
					}
					attackAnim();

					// System.out.println(this + " " + hp);
					// System.out.println(eOpponent + " " + eOpponent.hp);

				}

				sPose = "Human_Still";
				bAttackInProcess = false;
				eOpponent = null;
				main.arlAttackpattern.remove(0);
				moveToPos(iXAnchor, iYAnchor, 200);
			}
		}

	}

	void clearStats() {
		for (Entity e : main.arlEntities) {
			Stat stats[] = { e.atk, e.aji, e.def, e.dex, e.hpMax };
			for (Stat s : stats) {
				for (int i = 0; i < s.TempModifiers.size(); i++) {
					if (s.TempModifiers.get(i).inflicter.equals(this)) {
						s.TempModifiers.get(i).turns--;
						if (s.TempModifiers.get(i).turns <= 0) {
							s.TempModifiers.remove(i);
							i--;
						}
					}

				}
			}
		}
		if (atk.iTempChange > 0) {
			atk.iTempChange--;
		} else if (atk.iTempChange < 0) {
			atk.iTempChange++;
		}
		if (dex.iTempChange > 0) {
			dex.iTempChange--;
		} else if (dex.iTempChange < 0) {
			dex.iTempChange++;
		}
		if (aji.iTempChange > 0) {
			aji.iTempChange--;
		} else if (atk.iTempChange < 0) {
			aji.iTempChange++;
		}
		if (def.iTempChange > 0) {
			def.iTempChange--;
		} else if (atk.iTempChange < 0) {
			def.iTempChange++;
		}

	}

	public String toString() {
		return "" + bIsAlive;

	}

	void SelectAttack() {
		if (isPC) {
			main.iOption = 0;
			main.iMax = m.getLength();
			main.iDisplayChoice = 1;
			while (main.iOption == 0) {
				main.necesaryStuff();
			}

			m.getItem(main.iOption-1).performAction();

		} else {
			EnemyAI();
		}
	}

	private void moveToPos(int x, int y, int time) {
		iXMark = x;
		iYMark = y;
		x = iXpos;
		y = iYpos;
		for (int i = 0; i <= time; i++) {
			iXpos = x + (int) ((iXMark - x) * ((float) i / time));
			iYpos = y + (int) ((iYMark - y) * ((float) i / time));
			main.necesaryStuff();
		}
	}

	void SetAttack() {
		meleeAttack = false;

		if (sAttackType.equals("Basic")) {
			bAttackThisTurn = true;
			meleeAttack = true;
		}
		if (sAttackType.equals("Heal")) {
			DrawingPanel.bTargetFriendly = true;
			ArrayList<Entity> arlTargets = new ArrayList<Entity>();
			for (Entity e : main.arlEntities) {
				if (e.isPC == isPC && e.bIsAlive) {
					arlTargets.add(e);
				}
			}
			if (arlTargets.isEmpty()) {
				System.exit(0);
			}
			if (isPC) {
				main.iOption = 0;
				main.iMax = arlTargets.size();
				main.iDisplayChoice = 2;
				if (arlTargets.size() == 1) {
					main.iOption = 1;
				}
				while (main.iOption == 0 || main.iOption > arlTargets.size()) {
					main.necesaryStuff();
				}
			} else {
				for (int i = 0; i < arlTargets.size(); i++) {
					if (arlTargets.get(i).hp == arlTargets.get(i).hpMax.getValue()) {
						arlTargets.remove(i--);
					}
				}
			}
			eOpponent = (arlTargets.get(main.iOption - 1));

			eOpponent.hp += 10;
			if (eOpponent.hp > eOpponent.hpMax.getValue()) {
				eOpponent.hp = eOpponent.hpMax.getValue();
			}
			eOpponent.iHealthOffset += 10;
			sPose = "Human_Digimancer_Action";
			for (int i = 0; i < 500; i++) {
				main.necesaryStuff();
			}
			DrawingPanel.bTargetFriendly = false;
			main.arlAttackpattern.remove(0);
		}
		if (sAttackType.equals("ArmorUp")) {
			def.iTempChange += 5;
			def.TempModifiers.add(new StatChange(this, 5, 1));
			main.arlAttackpattern.remove(0);
		}
		if (sAttackType.equals("Reckless")) {
			meleeAttack = true;
			atk.TempModifiers.add(new StatChange(this, atk.getValue(), 1));
			aji.TempModifiers.add(new StatChange(this, aji.getValue() / -2, 1));
			bAttackThisTurn = true;
		}
		if (sAttackType.equals("Screech")) {
			dex.TempModifiers.add(new StatChange(this, aji.getValue(), 1));
			bAttackThisTurn = true;
			sAttackEffect = "Disoreant";
		}
		if (sAttackType.equals("Digitack")) {
			dex.TempModifiers.add(new StatChange(this, aji.getValue(), 1));
			sAttackEffect = "Digitack";
			bAttackThisTurn = true;

		}
		if (!bAttackThisTurn) {
			eOpponent = null;
		}

	}

	void setTargetAndAttackType() {
		clearStats();
		for (Entity e : main.arlEntities) {
			e.sPose = "Human_Still";
		}
		sAttackEffect = "";
		if (bIsAlive) {
			sPose = "Human_Still";
			for (Entity e : main.arlEntities) {
				e.iHealthOffset = 0;
			}
			bAttackThisTurn = false;
			if (main.arlAttackpattern.isEmpty() == false && main.arlAttackpattern.get(0) == this && bIsAlive) {

				if (isPC) {
					sAttackType = "";
					SelectAttack();
					SetAttack();
					if (bAttackThisTurn) {
						ArrayList<Entity> arlTargets = new ArrayList<Entity>();
						for (Entity e : main.arlEntities) {
							if (e.isPC == false && e.bIsAlive) {
								arlTargets.add(e);
							}
						}
						if (arlTargets.isEmpty()) {
							System.exit(0);
						}

						main.iOption = 0;
						main.iMax = arlTargets.size();
						main.iDisplayChoice = 2;
						if (arlTargets.size() == 1) {
							main.iOption = 1;
						}
						while (main.iOption == 0 || main.iOption > arlTargets.size()) {
							main.necesaryStuff();
						}

						eOpponent = (arlTargets.get(main.iOption - 1));

					}

				} else {
					ArrayList<Entity> arlTargets = new ArrayList<Entity>();
					for (Entity e : main.arlEntities) {
						if (e.isPC && e.bIsAlive) {
							arlTargets.add(e);
						}
					}
					if (arlTargets.size() == 0) {
						System.exit(0);
					}

					eOpponent = (arlTargets.get(main.rng.nextInt(arlTargets.size())));
					SelectAttack();
					SetAttack();
				}

			}
		}
	}

	private void attackAnim() {

		for (int i = 0; i < 500; i++) {
			main.necesaryStuff();
		}
		eOpponent.sPose = "Human_Still";

	}

	private void onHit() {
		if (sAttackEffect.equals("")) {
			eOpponent.hp -= atk.getValue() - eOpponent.def.getValue();
			eOpponent.iHealthOffset -= atk.getValue() - eOpponent.def.getValue();
		} else if (sAttackEffect.equals("Disoreant")) {
			eOpponent.def.TempModifiers.add(new StatChange(this, -10, 1));
			eOpponent.dex.TempModifiers.add(new StatChange(this, -10, 1));

		} else if (sAttackEffect.equals("Digitack")) {
			for (int i = 0; i < atk.getValue(); i++) {
				if (main.rng.nextBoolean()) {
					eOpponent.hp -= 1;
					eOpponent.iHealthOffset -= 1;
				}
				main.necesaryStuff();

			}
		}
		eOpponent.sPose = "Human_Hit";

	}

	void draw(Graphics g, ConsolePanel console) {
		DrawingPanel.drawSetup(g);
		if (sPose.equals("Human_Still") && !def.TempModifiers.isEmpty()) {
			sPose = "Human_Block";
		}
		Ascii_Frame.getFrame(sPose).drawFrame(iXpos, iYpos, console, !isPC);

		// if (sPose.equals("")) {
		// console.setChars(" () ", iXpos, iYpos - 1);
		// console.setChars(" /||\\", iXpos, iYpos);
		// console.setChars(" || ", iXpos, iYpos + 1);
		// }
		// if (sPose.equals("MeleeAtk")) {
		// if (isPC) {
		// console.setChars(" () \\", eOpponent.iXpos - 5, eOpponent.iYpos - 1);
		// console.setChars(" /||\\,||", eOpponent.iXpos - 5, eOpponent.iYpos);
		// console.setChars(" /|'\\//", eOpponent.iXpos - 5, eOpponent.iYpos +
		// 1);
		// } else {
		// console.setChars(" / ()", eOpponent.iXpos + 5, eOpponent.iYpos - 1);
		// console.setChars("||,/||\\", eOpponent.iXpos + 5, eOpponent.iYpos);
		// console.setChars("\\\\/'|\\", eOpponent.iXpos + 5, eOpponent.iYpos +
		// 1);
		// }
		// }
		// if (sPose.equals("Hit")) {
		// if (isPC) {
		// console.setChars(" ()", iXpos, iYpos - 1);
		// console.setChars(" /||", iXpos, iYpos);
		// console.setChars(" |\\", iXpos, iYpos + 1);
		// } else {
		// console.setChars(" ()", iXpos, iYpos - 1);
		// console.setChars(" ||\\", iXpos, iYpos);
		// console.setChars(" /|", iXpos, iYpos + 1);
		// }
		// }
		// if (sPose.equals("") && def.getValue() > def.iBase) {
		// if (isPC) {
		// console.setChars(" ()/\\ ", iXpos, iYpos - 1);
		// console.setChars(" /||\\/", iXpos, iYpos);
		// console.setChars(" || ", iXpos, iYpos + 1);
		// } else {
		// console.setChars(" /\\() ", iXpos, iYpos - 1);
		// console.setChars(" \\/||\\", iXpos, iYpos);
		// console.setChars(" || ", iXpos, iYpos + 1);
		// }
		// }
		// if (sPose.equals("Digimancy")) {
		// if (isPC) {
		// console.setChars(" ", iXpos, iYpos - 2);
		// console.setChars(" ()_¡ ", iXpos, iYpos - 1);
		// console.setChars(" /|| |", iXpos, iYpos);
		// console.setChars(" || ", iXpos, iYpos + 1);
		// } else {
		// console.setChars(" ¡_() ", iXpos, iYpos - 1);
		// console.setChars(" | ||\\", iXpos, iYpos);
		// console.setChars(" || ", iXpos, iYpos + 1);
		// }
		// }
		// if (sPose.equals("DigimancyHeal")) {
		// if (isPC) {
		// console.setChars(" ¡", iXpos, iYpos - 2);
		// console.setChars(" ()/| ", iXpos, iYpos - 1);
		// console.setChars(" /||", iXpos, iYpos);
		// console.setChars(" || ", iXpos, iYpos + 1);
		// } else {
		// console.setChars(" ¡", iXpos, iYpos - 2);
		// console.setChars(" |\\() ", iXpos, iYpos - 1);
		// console.setChars(" ||\\", iXpos, iYpos);
		// console.setChars(" || ", iXpos, iYpos + 1);
		// }
		// }
		// if (sPose.equals("DigimancyAttack")) {
		// if (isPC) {
		// console.setChars(" ¡ºººººººººººººººº", eOpponent.iXpos - 8,
		// eOpponent.iYpos - 2);
		// console.setChars(" ()/|ºººººººººººººººº", eOpponent.iXpos - 8,
		// eOpponent.iYpos - 1);
		// console.setChars(" /|| ºººººººººººººººº", eOpponent.iXpos - 8,
		// eOpponent.iYpos);
		// console.setChars(" || ºººººººººººººººº", eOpponent.iXpos - 8,
		// eOpponent.iYpos + 1);
		// console.setChars(" ºººººººººººººººº", eOpponent.iXpos - 8,
		// eOpponent.iYpos + 2);
		// } else {
		// console.setChars("ºººººººººººººººº¡", eOpponent.iXpos-4,
		// eOpponent.iYpos - 2);
		// console.setChars("ºººººººººººººººº\\() ", eOpponent.iXpos-4,
		// eOpponent.iYpos - 1);
		// console.setChars("ºººººººººººººººº ||\\", eOpponent.iXpos-4,
		// eOpponent.iYpos);
		// console.setChars("ºººººººººººººººº || ", eOpponent.iXpos-4,
		// eOpponent.iYpos + 1);
		// console.setChars("ºººººººººººººººº", eOpponent.iXpos - 4,
		// eOpponent.iYpos + 2);
		// }
		// }
		if (sPose.equals("Blob")) {
			if (isPC) {
				console.setChars("   ____", iXpos, iYpos - 1);
				console.setChars("  /  OO\\", iXpos, iYpos);
				console.setChars("  |____|", iXpos, iYpos + 1);
			} else {
				console.setChars("   ____", iXpos, iYpos - 1);
				console.setChars("  /OO  \\", iXpos, iYpos);
				console.setChars("  |____|", iXpos, iYpos + 1);
			}

		}
		if (sPose.equals("BlobHit")) {
			if (isPC) {
				console.setChars(" ____", iXpos, iYpos - 1);
				console.setChars("/  XX\\", iXpos, iYpos);
				console.setChars("\\_____\\", iXpos, iYpos + 1);
			} else {
				console.setChars("     ____", iXpos, iYpos - 1);
				console.setChars("    /XX  \\", iXpos, iYpos);
				console.setChars("   /_____/", iXpos, iYpos + 1);
			}

		}
		if (sPose.equals("BlobAttack")) {
			if (isPC) {
				console.setChars("  __ ____", eOpponent.iXpos - 6, eOpponent.iYpos - 1);
				console.setChars(" __ /  OO\\", eOpponent.iXpos - 6, eOpponent.iYpos);
				console.setChars("__ /_____/", eOpponent.iXpos - 6, eOpponent.iYpos + 1);
			} else {
				console.setChars(" ____ __", eOpponent.iXpos + 6, eOpponent.iYpos - 1);
				console.setChars("/OO  \\ __", eOpponent.iXpos + 6, eOpponent.iYpos);
				console.setChars("\\_____\\ __", eOpponent.iXpos + 6, eOpponent.iYpos + 1);
			}

		}
		if (sPose.equals("BatA")) {
			console.setChars("  \\\\  //", iXpos, iYpos - 1);
			console.setChars("   '()'", iXpos, iYpos);
		}
		if (sPose.equals("BatB")) {
			console.setChars("   .().", iXpos, iYpos);
			console.setChars("  //  \\\\", iXpos, iYpos + 1);
		}

		if (sPose.equals("BatHit")) {
			if (isPC) {
				console.setChars("    \\\\", iXpos, iYpos - 1);
				console.setChars("   \\\\()", iXpos, iYpos);

			} else {
				console.setChars("      //", iXpos, iYpos - 1);
				console.setChars("     ()//", iXpos, iYpos);

			}

		}
		if (sPose.equals("BatAttack")) {
			if (isPC) {
				console.setChars("  \\\\", eOpponent.iXpos - 4, eOpponent.iYpos - 1);
				console.setChars(" \\\\()", eOpponent.iXpos - 4, eOpponent.iYpos);

			} else {
				console.setChars("  //", eOpponent.iXpos + 4, eOpponent.iYpos - 1);
				console.setChars(" ()//", eOpponent.iXpos + 4, eOpponent.iYpos);

			}
		}
		if (sPose.equals("BatSonic")) {
			if (isPC) {
				console.setChars("  \\\\", eOpponent.iXpos - 6, eOpponent.iYpos - 1);
				console.setChars(" \\\\()", eOpponent.iXpos - 6, eOpponent.iYpos);

			} else {
				console.setChars("         //", eOpponent.iXpos + 3, eOpponent.iYpos - 1);
				console.setChars("))))))))()//", eOpponent.iXpos + 3, eOpponent.iYpos);

			}
		}

		boolean flip = false;
		String sOutput = "";
		for (int i = 0; i < 10; i += 1) {
			if (i < ((float) hp / hpMax.getValue()) * 10) {
				if (flip) {
					sOutput += "]";
					flip = false;
				} else {
					sOutput += "[";
					flip = true;
				}

			} else {
				sOutput += "_";
			}
		}
		sOutput += " " + hp;
		if (iHealthOffset != 0) {
			if (iHealthOffset > 0) {
				sOutput += " +" + iHealthOffset;
			} else {
				sOutput += " " + iHealthOffset;
			}
		}
		console.setChars(sOutput, iXAnchor - 4, iYAnchor + 3);

	}

}
// | |
// + +
// ()/ \()
// /|| ||\
// || ||
// Rally

// () \\ // ()
// /||\,|| ||,/||\
// /|'\// \\/'|\
// Slash

// ()/\
// /||\/
// /|
// Block

// () ()
// /|| ||\
// |\ /|
// Hurt/Dodge

// () ()
// \/==} {==\/
// /| |\
// Shoot
// >--> <--<
// Bolt

// ()_O O_()
// /|| | | ||\
// /\ /\
// Magic Attack
// -<o o>-
// Fire-ball
// _ _ _
// / \_/ \_/ \
// Lightning

// ()_, ,_()
// /|| ||\
// /| |\
// Hand

// ____ ____
// / OO\ /OO \
// |____| |____|
// Ooze

// \\ //
// '()' ,(),
// // \\
// Bat
