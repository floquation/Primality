import static org.junit.Assert.*;

import org.junit.Test;


public class SSTester {

	@Test
	public void testGCD() {
		assertEquals(13, SS.gcd(13, 13));
		assertEquals(1, SS.gcd(37, 600));
		assertEquals(20, SS.gcd(20, 100));
		assertEquals(18913, SS.gcd(624129, 2061517));
	}
}
