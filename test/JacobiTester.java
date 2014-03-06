import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class JacobiTester {
	
	public int a;
	public int n;
	public int answer;
	
	public JacobiTester(int a, int n, int answer) {
		this.a = a;
		this.n = n;
		this.answer = answer;
	}
	
	@Parameterized.Parameters
	public static Collection testCases() {
		return Arrays.asList(new Object[][] {
				{191, 279, 1},
				{277, 305, 1},
				{13, 17, 1},
				{19, 13, -1},
				{195, 139, -1},
				{1001, 9907, -1},
				/*{0, 0, 0},
				{77, 0, 0},
				{2, 0, 0},
				{0, 20, 0},
				{100, 1000, 0},
				{1000, 1000, 0},
				{999, 333, 0}*/
		});
	}
	
	@Test
	public void test() {
		assertEquals(answer, SS.jacobi(a, n));
	}
}
