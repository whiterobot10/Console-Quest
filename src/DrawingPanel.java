import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawingPanel extends JPanel {

	public static void drawSetup(Graphics g) {
		//g.setFont(new Font("Monospaced", Font.PLAIN, 20));
		g.setColor(Color.green);
	}

	public static boolean bTargetFriendly = false;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawSetup(g);
		main.Console.clear();

		// TODO Auto-generated method stub

		synchronized (main.arlSkillGames) {
			for (SkillGame s : main.arlSkillGames) {
				s.draw(g, main.Console);

			}
		}
		synchronized (main.arlEntities) {
			for (Entity e : main.arlEntities) {

				if (e.bIsAlive) {

					e.draw(g, main.Console);

				}
			}
		}


		if (main.iDisplayChoice == 1 && main.arlAttackpattern.get(0).isPC) {
			main.Console.setChars("    \\|/", main.arlAttackpattern.get(0).iXpos-4,
					main.arlAttackpattern.get(0).iYpos - 4);
			main.Console.setChars("     |", main.arlAttackpattern.get(0).iXpos-4, main.arlAttackpattern.get(0).iYpos - 5);
//			if (main.arlAttackpattern.get(0).type == "Entity") {
//				Menu m = new Menu(new MenuItem("Basic Attack"),new MenuItem("Reckless Attack"),new MenuItem("Defend"));
//				m.draw(g);
//			}
//			if (main.arlAttackpattern.get(0).type == "Blob") {
//				Menu m = new Menu(new MenuItem("Basic Attack"),new MenuItem("Reckless Attack"));
//				m.draw(g);
//			}
//
//			if (main.arlAttackpattern.get(0).type == "Bat") {
//				Menu m = new Menu(new MenuItem("Basic Attack",null),new MenuItem("Sonic Attack",null));
//				m.draw(g);
//			}
//			if (main.arlAttackpattern.get(0).type == "Digimancer") {
//				Menu m = new Menu(new MenuItem("Digital Attack",null),new MenuItem("Healing",null));
//				m.draw(g);	
//			}
			main.arlAttackpattern.get(0).m.draw(g);
			

		}
		if (main.iDisplayChoice == 2) {
			ArrayList<Entity> arlSelectTarget = new ArrayList<Entity>();
			for (Entity e : main.arlEntities) {
				if (e.isPC == bTargetFriendly && e.bIsAlive) {
					arlSelectTarget.add(e);
				}
			}
			main.Console.setChars("Select Target", 20, 23);
			try {
				main.Console.setChars("    \\|/", arlSelectTarget.get(main.iSelected - 1).iXpos-4,
						arlSelectTarget.get(main.iSelected - 1).iYpos - 4);
				main.Console.setChars("     |", arlSelectTarget.get(main.iSelected - 1).iXpos-4,
						arlSelectTarget.get(main.iSelected - 1).iYpos - 5);
			} catch (java.lang.IndexOutOfBoundsException e) {
				e.printStackTrace();
			}

		}
		//Ascii_Frame.getFrame("New_Sun_Blank").drawFrame(0, 0, main.Console, false);
		main.Console.draw(g);
	}

}
// Abadi MT Condensed Extra Bold
// Abadi MT Condensed Light
// Al Bayan
// Al Nile
// Al Tarikh
// American Typewriter
// Andale Mono
// Apple Braille
// Apple Chancery
// Apple Color Emoji
// Apple SD Gothic Neo
// Apple Symbols
// AppleGothic
// AppleMyungjo
// Arial
// Arial Black
// Arial Hebrew
// Arial Hebrew Scholar
// Arial Narrow
// Arial Rounded MT Bold
// Arial Unicode MS
// Athelas
// Avenir
// Avenir Next
// Avenir Next Condensed
// Ayuthaya
// Baghdad
// Bangla MN
// Bangla Sangam MN
// Baoli SC
// Baskerville
// Baskerville Old Face
// Batang
// Bauhaus 93
// Beirut
// Bell MT
// Bernard MT Condensed
// Big Caslon
// Bodoni 72
// Bodoni 72 Oldstyle
// Bodoni 72 Smallcaps
// Bodoni Ornaments
// Book Antiqua
// Bookman Old Style
// Bookshelf Symbol 7
// Bradley Hand
// Braggadocio
// Britannic Bold
// Brush Script MT
// Calibri
// Calisto MT
// Cambria
// Cambria Math
// Candara
// Century
// Century Gothic
// Century Schoolbook
// Chalkboard
// Chalkboard SE
// Chalkduster
// Charter
// Cochin
// Colonna MT
// Comic Sans MS
// Consolas
// Constantia
// Cooper Black
// Copperplate
// Copperplate Gothic Bold
// Copperplate Gothic Light
// Corbel
// Corsiva Hebrew
// Courier
// Courier New
// Curlz MT
// Damascus
// DecoType Naskh
// Desdemona
// Devanagari MT
// Devanagari Sangam MN
// Dialog
// DialogInput
// Didot
// DIN Alternate
// DIN Condensed
// Diwan Kufi
// Diwan Thuluth
// Edwardian Script ITC
// Engravers MT
// Euphemia UCAS
// Eurostile
// Farah
// Farisi
// Footlight MT Light
// Franklin Gothic Book
// Franklin Gothic Medium
// Futura
// Gabriola
// Garamond
// GB18030 Bitmap
// Geeza Pro
// Geneva
// Georgia
// Gill Sans
// Gill Sans MT
// Gloucester MT Extra Condensed
// Goudy Old Style
// Gujarati MT
// Gujarati Sangam MN
// Gulim
// GungSeo
// Gurmukhi MN
// Gurmukhi MT
// Gurmukhi Sangam MN
// Haettenschweiler
// Hannotate SC
// Hannotate TC
// HanziPen SC
// HanziPen TC
// Harrington
// HeadLineA
// Heiti SC
// Heiti TC
// Helvetica
// Helvetica Neue
// Herculanum
// Hiragino Kaku Gothic Pro
// Hiragino Kaku Gothic ProN
// Hiragino Kaku Gothic Std
// Hiragino Kaku Gothic StdN
// Hiragino Maru Gothic Pro
// Hiragino Maru Gothic ProN
// Hiragino Mincho Pro
// Hiragino Mincho ProN
// Hiragino Sans
// Hiragino Sans GB
// Hoefler Text
// Impact
// Imprint MT Shadow
// InaiMathi
// Iowan Old Style
// ITF Devanagari
// ITF Devanagari Marathi
// Kailasa
// Kaiti SC
// Kaiti TC
// Kannada MN
// Kannada Sangam MN
// Kefa
// Khmer MN
// Khmer Sangam MN
// Kino MT
// Klee
// Kohinoor Bangla
// Kohinoor Devanagari
// Kohinoor Telugu
// Kokonor
// Krungthep
// KufiStandardGK
// Lantinghei SC
// Lantinghei TC
// Lao MN
// Lao Sangam MN
// Libian SC
// LiHei Pro
// LiSong Pro
// Lucida Blackletter
// Lucida Bright
// Lucida Calligraphy
// Lucida Console
// Lucida Fax
// Lucida Grande
// Lucida Handwriting
// Lucida Sans
// Lucida Sans Typewriter
// Lucida Sans Unicode
// Luminari
// Malayalam MN
// Malayalam Sangam MN
// Marion
// Marker Felt
// Marlett
// Matura MT Script Capitals
// Meiryo
// Menlo
// Microsoft Himalaya
// Microsoft Sans Serif
// Microsoft Tai Le
// Microsoft Yi Baiti
// MingLiU
// MingLiU-ExtB
// MingLiU_HKSCS
// MingLiU_HKSCS-ExtB
// Mishafi
// Mishafi Gold
// Mistral
// Modern No. 20
// Monaco
// Mongolian Baiti
// Monospaced
// Monotype Corsiva
// Monotype Sorts
// MS Gothic
// MS Mincho
// MS PGothic
// MS PMincho
// MS Reference Sans Serif
// MS Reference Specialty
// Mshtakan
// MT Extra
// Muna
// Myanmar MN
// Myanmar Sangam MN
// Nadeem
// Nanum Brush Script
// Nanum Gothic
// Nanum Myeongjo
// Nanum Pen Script
// New Peninim MT
// News Gothic MT
// Noteworthy
// Onyx
// Optima
// Oriya MN
// Oriya Sangam MN
// Osaka
// Palatino
// Palatino Linotype
// Papyrus
// PCMyungjo
// Perpetua
// Perpetua Titling MT
// Phosphate
// PilGi
// PingFang HK
// PingFang SC
// PingFang TC
// Plantagenet Cherokee
// Playbill
// PMingLiU
// PMingLiU-ExtB
// Press Start 2P
// PT Mono
// PT Sans
// PT Sans Caption
// PT Sans Narrow
// PT Serif
// PT Serif Caption
// Raanana
// Rockwell
// Rockwell Extra Bold
// Sana
// SansSerif
// Sathu
// Savoye LET
// Seravek
// Serif
// Shree Devanagari 714
// SignPainter
// Silom
// SimHei
// SimSun
// SimSun-ExtB
// Sinhala MN
// Sinhala Sangam MN
// Skia
// Snell Roundhand
// Songti SC
// Songti TC
// Stencil
// STFangsong
// STHeiti
// STIXGeneral
// STIXIntegralsD
// STIXIntegralsSm
// STIXIntegralsUp
// STIXIntegralsUpD
// STIXIntegralsUpSm
// STIXNonUnicode
// STIXSizeFiveSym
// STIXSizeFourSym
// STIXSizeOneSym
// STIXSizeThreeSym
// STIXSizeTwoSym
// STIXVariants
// STKaiti
// STSong
// Sukhumvit Set
// Superclarendon
// Symbol
// Tahoma
// Tamil MN
// Tamil Sangam MN
// Telugu MN
// Telugu Sangam MN
// Thonburi
// Times
// Times New Roman
// Trattatello
// Trebuchet MS
// Tsukushi A Round Gothic
// Tsukushi B Round Gothic
// Tw Cen MT
// Verdana
// Waseem
// Wawati SC
// Wawati TC
// Webdings
// Weibei SC
// Weibei TC
// Wide Latin
// Wingdings
// Wingdings 2
// Wingdings 3
// Xingkai SC
// Yuanti SC
// Yuanti TC
// YuGothic
// YuMincho
// YuMincho +36p Kana
// Yuppy SC
// Yuppy TC
// Zapf Dingbats
// Zapfino

// Exception in thread "AWT-EventQueue-0"
// java.util.ConcurrentModificationException
// at java.util.ArrayList$Itr.checkForComodification(Unknown Source)
// at java.util.ArrayList$Itr.next(Unknown Source)
// at DrawingPanel.paintComponent(DrawingPanel.java:13)
// at javax.swing.JComponent.paint(Unknown Source)
// at javax.swing.JComponent.paintToOffscreen(Unknown Source)
// at javax.swing.RepaintManager$PaintManager.paintDoubleBuffered(Unknown
// Source)
// at javax.swing.RepaintManager$PaintManager.paint(Unknown Source)
// at javax.swing.RepaintManager.paint(Unknown Source)
// at javax.swing.JComponent._paintImmediately(Unknown Source)
// at javax.swing.JComponent.paintImmediately(Unknown Source)
// at javax.swing.RepaintManager$4.run(Unknown Source)
// at javax.swing.RepaintManager$4.run(Unknown Source)
// at java.security.AccessController.doPrivileged(Native Method)
// at
// java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(Unknown
// Source)
// at javax.swing.RepaintManager.paintDirtyRegions(Unknown Source)
// at javax.swing.RepaintManager.paintDirtyRegions(Unknown Source)
// at javax.swing.RepaintManager.prePaintDirtyRegions(Unknown Source)
// at javax.swing.RepaintManager.access$1200(Unknown Source)
// at javax.swing.RepaintManager$ProcessingRunnable.run(Unknown Source)
// at java.awt.event.InvocationEvent.dispatch(Unknown Source)
// at java.awt.EventQueue.dispatchEventImpl(Unknown Source)
// at java.awt.EventQueue.access$500(Unknown Source)
// at java.awt.EventQueue$3.run(Unknown Source)
// at java.awt.EventQueue$3.run(Unknown Source)
// at java.security.AccessController.doPrivileged(Native Method)
// at
// java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(Unknown
// Source)
// at java.awt.EventQueue.dispatchEvent(Unknown Source)
// at java.awt.EventDispatchThread.pumpOneEventForFilters(Unknown Source)
// at java.awt.EventDispatchThread.pumpEventsForFilter(Unknown Source)
// at java.awt.EventDispatchThread.pumpEventsForHierarchy(Unknown Source)
// at java.awt.EventDispatchThread.pumpEvents(Unknown Source)
// at java.awt.EventDispatchThread.pumpEvents(Unknown Source)
// at java.awt.EventDispatchThread.run(Unknown Source)
