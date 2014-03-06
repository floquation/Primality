import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class GCDTester {
	
	public int a;
	public int b;
	public int answer;
	
	public GCDTester(int a, int b, int answer) {
		this.a = a;
		this.b = b;
		this.answer = answer;
	}
	
	@Parameterized.Parameters
	public static Collection testCases() {
		return Arrays.asList(new Object[][] {
				{13, 13, 13},
				{37, 600, 1},
				{20, 100, 20},
				{624129, 2061517, 18913},
		});
	}
	
	@Test
	public void testGCD() {
		assertEquals(answer, SS.gcd(a, b));
	}
	
	@Test
	public void testGCDReverse() {
		assertEquals(answer, SS.gcd(b, a));
	}

}
