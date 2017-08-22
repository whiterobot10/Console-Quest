import java.util.HashMap;
import java.util.Map;

public class Utils {

	private static Map<Character, Character> flipMap = new HashMap<>();

	static {
		addFlipChar('/', '\\');
		addFlipChar('(', ')');
		addFlipChar('{', '}');
		addFlipChar('[', ']');
		addFlipChar('<', '>');
	}

	private static void addFlipChar(char a, char b) {
		flipMap.put(a, b);
		flipMap.put(b, a);
	}

	public static String ImprovedFlip(String input) {
		StringBuilder sb = new StringBuilder();
		for (int i =input.length()-1;i>=0;i--) {
			char c = input.charAt(i);
			if (flipMap.containsKey(c)) {
				c = flipMap.get(c);
			}
			sb.append(c);
		}

		return sb.toString();

	}

}
