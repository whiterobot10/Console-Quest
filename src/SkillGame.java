import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class SkillGame {

	int iSkill = 0;
	int iOriginX, iOriginY;
	int iSkillRequired;

	public SkillGame(int originX, int originY, int delay, int speed) {
		iOriginX = originX;
		iOriginY = originY;
		iSkill = delay * -1;
		iSkillRequired = speed;
	}

	public void draw(Graphics g, ConsolePanel console) {
		// g.setColor(Color.red);
		// g.fillRect(iOriginX - 100, iOriginY - 10, 200, 20);
		// g.setColor(Color.orange);
		// g.fillRect(iOriginX - 65, iOriginY - 10, 130, 20);
		// g.setColor(Color.yellow);
		// g.fillRect(iOriginX - 35, iOriginY - 10, 70, 20);
		// g.setColor(Color.green);
		// g.fillRect(iOriginX - 10, iOriginY - 10, 20, 20);
		// g.setColor(Color.WHITE);
		// if (iSkill > -1) {
		// g.fillRect(iOriginX + (iSkill/100) - 100, iOriginY - 15, 5, 30);
		// } else {
		// g.fillRect(iOriginX - 100, iOriginY - 15, 5, 30);
		// }
		String sToDraw = "[";
		for (int i = 1; i <= 10; i++) {
			if (iSkill > (i - 1) * 2000 && iSkill <= 2000 * i) {
				sToDraw += "|";
			} else {
				sToDraw += "_";
			}
		}
		sToDraw += "]";


		console.setChars(sToDraw, iOriginX, iOriginY);
	}

	public void main() {
		if (iSkill < -10000) {
			iSkill++;
		} else {
			iSkill += iSkillRequired;
		}
		if (main.bEnter) {
			main.bEnter = false;
			if (iSkill > 10000) {
				iSkill = 20000 - iSkill;
			}

			main.SkillTesterOutput = (float) (iSkill + 5000) / 10000;
			main.arlSkillGames.remove(0);
		} else if (iSkill > 19999) {
			main.SkillTesterOutput = 0.5;
			try {
				main.arlSkillGames.remove(0);
			} catch (java.util.ConcurrentModificationException e) {
				e.printStackTrace();
			}
		}

	}

}
