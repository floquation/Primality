
public class Utils {
	/*
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
	}*/
	
	public static int modular_pow_old(long a, int b, int m) {
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
		
		// I hate do while, so let's do more of them just to piss myself off
		do {
			product = (a * product) % m;
			i += 1;
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
}
