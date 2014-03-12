import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class Utils {
	
	public static int modular_pow(long a, int b, int m) {
		a %= m;
		long product = (1 % m);
		while (b > 0) {
			if (b % 2 == 1)
				product = (a * product) % m;
			a = (a * a) % m;
			b /= 2;
		}
		return (int)product;
	}
	
	public static int multiplicativeOrder(long a, int m) {
		int i = 0;
		long product = 1;
		
		if(Utils.gcd((int)a, m) != 1) return 0;
		
		// I hate do while, so let's do more of them just to piss myself off
		do {
			product = (a * product) % m;
			i += 1;
			//System.out.println(product + "," + a + "," + m);
		} while(product != 1 && product != 0);
		return i;
	}
	
	/**
	 * Calculate the greatest common divisor of 2 positive integers.
	 * @param a positive integer
	 * @param b positive integer
	 * @return gcd(a, b)
	 */
	public static int gcd(int a, int b) {
		while(b != 0) {
			int temp = b; 
			b = a % b;
			a = temp;
		}
		return a;
	}
	
	public static void factorize(int n, Collection<Integer> factors) {
		// Trivial factors
		for (int p: new int[] {2, 3})
			while (n % p == 0) {
				factors.add(p);
				n /= p;
			}
		
		// Other factors
		for (int i = 6; n > 1; i += 6)
			for (int p: new int[] {i - 1, i + 1})
				while (n % p == 0) {
					factors.add(p);
					n /= p;
				}
		
		assert(n == 1);
	}
	
	public static int totient(int n) {
		Set<Integer> factors = new HashSet<Integer>();
		factorize(n, factors);
		return totient(n, factors);
	}
	
	public static int totient(int n, Set<Integer> factors) {
		int totient = n;
		for (int p: factors)
			totient -= totient / p;
		return totient;
	}
	
}
