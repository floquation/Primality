import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ModexpTester {
	
	public int base;
	public int exponent;
	public int mod;
	public int answer;
	
	public ModexpTester(int b, int e, int m, int a) {
		base = b;
		exponent = e;
		mod = m;
		answer = a;
	}
	
	@Parameterized.Parameters
	public static Collection<Object[]> testCases() {
		return Arrays.asList(new Object[][] {
				{5, 0, 1, 0},
				{0, 5, 1, 0},
				{12, 53, 7, 3},
				{56, 49, 75, 71},
				{123, 456, 789, 699},
				{7777, 5555555, 7777, 0},
				{9688563,45896 ,71, 20}
		});
	}
	
	@Test
	public void test() {
		assertEquals(answer, Utils.modular_pow(base, exponent, mod));
	}
}
