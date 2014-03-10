
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
	
	public static int modular_pow(long a, long b, long m) {
		long product = 1;
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
		return (int)(product % m);
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
}
