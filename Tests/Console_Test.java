import static org.junit.Assert.*;

import org.junit.Test;

public class Console_Test {

	@Test
	public void test() {
		ConsolePanel test = new ConsolePanel(10, 10);
		test.setChars("pie", 0, 0);
		test.setChars("pie", 1, 0);
		test.setChars("pie", 0, 3);
		test.setChars("pie", 9, 0);
		test.setChars("pie", 0, 10);
		assertTrue(test.toString().equals(
							"ppie     p\n"
							+ "          \n"
							+ "          \n"
							+ "pie       \n"
							+ "          \n"
							+ "          \n"
							+ "          \n"
							+ "          \n"
							+ "          \n"
							+ "          \n"));

	}

}
