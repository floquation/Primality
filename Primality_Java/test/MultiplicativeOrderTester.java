import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class MultiplicativeOrderTester {
	
	public int base;
	public int mod;
	public int answer;
	
	public MultiplicativeOrderTester(int b, int n, int a) {
		base = b;
		mod = n;
		answer = a;
	}
	
	@Parameterized.Parameters
	public static Collection<Object[]> testCases() {
		return Arrays.asList(new Object[][] {
				{4, 7, 3}, //ord_7(4)
				{7, 4, 2}, //ord_4(7)
				{87, 101, 5}, //ord_101(87) = 5
				{101, 87, 28},
				//{100, 500, -111},
				//{500, 100, -111},
		});
	}
	
	@Test
	public void test() {
		assertEquals(answer, Utils.multiplicativeOrder(base, mod));
	}
}
