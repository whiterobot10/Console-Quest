import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Ascii_Frame {
	private final String name;
	private final String Lines[];
	private final int xOff, yOff;

	private static Map<String, Ascii_Frame> frames = new HashMap<>();

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name + "\n");
		for (String s : Lines) {
			sb.append(s + "\n");
		}
		return sb.toString();

	}

	static List<Ascii_Frame> readFrames(String fileName) {
		List<Ascii_Frame> frames = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(new File(fileName));
			while (scanner.hasNext()) {
				if (scanner.next().equals("Frame")) {
					String Name = scanner.next();
					int rows = scanner.nextInt();
					int colls = scanner.nextInt();
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < colls; i++) {
						sb.append(" ");
					}
					String whiteSpace = sb.toString();
					int x = scanner.nextInt();
					int y = scanner.nextInt();
					String Lines[] = new String[rows];
					scanner.nextLine();
					for (int i = 0; i < rows; i++) {
						String hold = scanner.nextLine() + whiteSpace;
						Lines[i] = hold.substring(0, colls);
					}
					frames.add(new Ascii_Frame(Name, Lines, x, y));
				}

			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return frames;

	}

	public Ascii_Frame(String _Name, String _Lines[], int x, int y) {
		name = _Name;
		Lines = Arrays.copyOf(_Lines, _Lines.length);
		frames.put(name, this);
		xOff = x;
		yOff = y;
	}

	public String getLine(int i) {
		return Lines[i];
	}

	public void drawFrame(int x, int y, ConsolePanel console, boolean isFlipped) {
		String printList[] = Arrays.copyOf(Lines, Lines.length);
		

		for (String s : printList) {
			console.setChars(isFlipped ? Utils.ImprovedFlip(s) : s, x - (isFlipped ? (Lines[0].length()-2) - xOff : xOff), y++ - yOff);
		}
	}

	public static Ascii_Frame getFrame(String key) {
		Ascii_Frame test = frames.get(key);
		if (test == null) {
			throw new InvalidFrameExeption(key);
		} else {
			return test;
		}

	};

}
