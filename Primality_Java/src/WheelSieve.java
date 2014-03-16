/**
 * 
 * @author Laurent Verweijen
 *
 */
public class WheelSieve {
	public static boolean wheelSieve(int n) {
		// Negative check
		if (n < 2) return false;
		
		// Simple cases
		if (n % 2 == 0) return n == 2;
		if (n % 3 == 0) return n == 3;
		
		// Taking a wheel of 6
		int sqrtn = (int) Math.ceil(Math.sqrt(n)) + 1;
		for (int i = 6; i <= sqrtn; i += 6) {
			if (n % (i - 1) == 0) return false;
			if (n % (i + 1) == 0) return false;
		}
		return true;
	}
	
	/*public static void main(String[] args) {
		for (int i = 0; i < 100; i++)
			System.out.println(i + "beh: " + WheelSieve.wheelSieve(i));
	}*/
}
