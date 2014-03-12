import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TotientTester {
	
	public int number;
	public int answer;
	
	public TotientTester(int n, int a) {
		number = n;
		answer = a;
	}
	
	@Parameterized.Parameters
	public static Collection<Object[]> testCases() {
		return Arrays.asList(new Object[][] {
				{2, 1},
				{3, 2},
				{6, 2},
				{9, 6},
				{10, 4},
				{12, 4},
				{35, 24},
				{101, 100},
				{2520, 576},
		});
	}
	
	@Test
	public void test() {
		assertEquals(answer, Utils.totient(number));
	}
}
