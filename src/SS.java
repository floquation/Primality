

public class SS {

	public static int jacobi(int a, int n) {
		int res = Integer.MIN_VALUE;

		// Property 4
		if (a == 1) {
			res = 1;
		}

		// Property 5
		else if (a == 2) {
			if (n % 8 == 3 || n % 8 == 5)
				res = -1;
			else if (n % 8 == 1 || n % 8 == 7)
				res = 1;
			else {
				res = 0;
			}
		}

		else {
			// Property 2
			if(n != 0)
				a %= n;
			else
				return 0;

			// Property 1
			if(a % 2 == 0 && a != 0) {
				res = jacobi(2, n) * jacobi(a / 2, n); // OOPS
			} else
				// Property 3 (assumption, because otherwise i'm out of rules)
				res = (int) (Math.pow(-1, (a - 1) * (n - 1) / 4)) * jacobi(n, a);
		}
		return res;
	}

	/** 
	 * Return whether n is probably prime with accurary k 
	 */
	public static boolean ss(int n, int k) {
		
		// Prevent 1 or negative numbers from evalulating to true
		if (n < 2) return false;

		// Handle even numbers
		if (n % 2 == 0) {
			return n == 2;
		}
		
		for (int i = 0; i < k; i++) {
			int a = 1 + (int)(Math.random() * (n - 1));
			int g = Utils.gcd(a, n);
			if (g != 1) {
				//System.out.println("a = " + a);
				return false;
			}

			int x = jacobi(a, n);
			assert(x != 0);

			int y = Utils.modular_pow(a, (n - 1) / 2, n);
			
			if (y != x && x + n != y) {
				//System.out.println("x = " + x);
				//System.out.println("y = " + y);
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		jacobi(191, 279);
	}
}
