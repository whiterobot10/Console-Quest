import static org.junit.Assert.*;

import org.junit.Test;

public class Utils_Test {

	@Test
	public void testImprovedFlip() {
		assertEquals("eip", Utils.ImprovedFlip("pie"));
		assertEquals("[/({<]\\)}>", Utils.ImprovedFlip("<{(/[>})\\]"));

	}

}
