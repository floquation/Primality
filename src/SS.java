

public class SS {

	public static int gcd(int a, int b) {
		while(b != 0) {
			//int temp = a;
			//a = ((a % b) + b) % b; // I really want Euler modulo!!!! 
			//b = temp;

			int temp = b;
			b = ((a % b) + b) % b; // I really want Euler modulo!!!! 
			a = temp;
		}
		return a;
	}

	public static int jacobi(int a, int n) {
		//System.out.println("Ik ben jacobi: " + a + "," + n);
		int res = Integer.MIN_VALUE;

		// special case
		//if(n == 0) return 0;
		//if(a == 0) return 0;
		
		//if(a == 0) throw new RuntimeException("a == 0");

		// Property 4
		if (a == 1) {
			//System.out.println("Property 4");
			res = 1;
		}

		// Property 5
		else if (a == 2) {
			//System.out.println("Property 5");
			if (n % 8 == 3 || n % 8 == 5)
				res = -1;
			else if (n % 8 == 1 || n % 8 == 7)
				res = 1;
			else {
				//throw new RuntimeException("n somehow isn't even");
				res = 0;
			}
				
		}

		else {
			//System.out.println("Property 2");
			// Property 2
			
			//if(a == n) throw new RuntimeException("a == n == " + a);
			//System.out.println("a, n =" + a + ", " + n);
			
			if(n == 0) {
				//assert False;
				return 0;
			}
			
			a %= n;
			//if(a == 0) throw new RuntimeException("fuck0");

			// Property 1
			if(a % 2 == 0 && a != 0) {
				//System.out.println("Property 1");
				res = jacobi(2, n) * jacobi(a / 2, n); // OOPS
			} else
				//System.out.println("Property 3");
				// Property 3 (assumption, because otherwise i'm out of rules)
				res = (int) (Math.pow(-1, (a - 1) * (n - 1) / 4)) * jacobi(n, a);
		}
		//System.out.println("The result is " + res);
		return res;
		//return (int) (Math.pow(-1, (a - 1) * (n - 1) / 4)) * jacobi(n, a);
	}

	/** 
	 * Return whether n is probably prime with accurary k 
	 */
	public static boolean ss(int n, int k) {

		// Handle even numbers
		if (n % 2 == 0) {
			return n == 2;
		}
		
		for (int i = 0; i < k; i++) {
			int a = 1 + (int)(Math.random() * (n - 1));
			int g = gcd(a, n);
			if (g != 1) {
				System.out.println("a = " + a);
				return false;
			}

			int x = jacobi(a, n);
			assert(x != 0);

			//System.out.println("x = " + x);

			/*if (x == 0)
				return false;*/

			//if ((Math.pow(a, (n - 1) / 2) % n) != x)
			
			int y = MR.modular_pow(a, (n - 1) / 2, n);
			
			if (y != x && x + n != y) {
				System.out.println("x = " + x);
				System.out.println("y = " + y);
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		jacobi(191, 279);
	}
}
