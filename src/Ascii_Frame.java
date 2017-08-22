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

	private static Map<String, Ascii_Frame> frames = new HashMap<>();
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(name+"\n");
		for(String s: Lines){
			sb.append(s+"\n");
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
					String Lines[] = new String[rows];
					scanner.nextLine();
					for (int i = 0; i < rows; i++) {
						Lines[i] = scanner.nextLine();
					}
					frames.add(new Ascii_Frame(Name, Lines));
				}

			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return frames;

	}

	public Ascii_Frame(String _Name, String _Lines[]) {
		name = _Name;
		Lines = Arrays.copyOf(_Lines, _Lines.length);
		frames.put(name, this);
	}

	public String getLine(int i) {
		return Lines[i];
	}

	public void drawFrame(int x, int y, ConsolePanel console, boolean isFlipped) {
		String printList[] = Arrays.copyOf(Lines, Lines.length);

		for (String s : printList) {
			console.setChars(isFlipped ? Utils.ImprovedFlip(s) : s, x, y++);
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
