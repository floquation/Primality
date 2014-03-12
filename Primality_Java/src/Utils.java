import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class Utils {
	public static int XXXmodular_pow(int a, int b, int m) {
		int tempo;
		if (b == 0){
			tempo = 1;  //EXIT condition
		}
		else if (b == 1) 
			tempo = a;
		else { 
			int temp = modular_pow(a,b/2,m);
			if (b%2 == 0)
				tempo = (temp*temp)%m;
			else
				tempo = ((temp*temp)%m)*a%m;
		}
		return tempo;
	}
	
	public static int modular_pow(long a, int b, int m) {
		a %= m;
		long product = (1 % m);
		while (b > 0) {
			if (b % 2 == 0) {
				a *= a; // square it
				a %= m;
				b /= 2;
			} else {
				product *= a;
				product %= m;
				b -= 1;
			}
		}
		return (int)product;
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