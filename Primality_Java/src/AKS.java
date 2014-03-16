import java.util.Arrays;

/**
 * 
 * @author Laurent Verweijen
 *
 */
public class AKS {
	public static boolean aks(int n) {
		if (n < 2) return false;
		
		// 1. Check if n = a**b
		for(int b = 2; b <= (int)(Math.log(n)/Math.log(2)); b++) {
			double a = Math.pow(n,1d/b);
			if(Math.floor(a) == a) 
				return false;
		}
		
		// 2. Find r such that o_r(n) > (log n)**2
		int logSquared = (int)Math.pow(log2(n), 2);
		int r = 2;
		while (Utils.multiplicativeOrder(n, r) <= logSquared)
			r++;
		
		// 3. 1 < gcd(a, n) < n for a < r, composite
		for(int a = 1; a <= r; a++) {
			int g = Utils.gcd(a, n);
			if (1 < g && g < n) 
				return false;
		}
		
		// 4
		if(n <= r)
			return true;
		
		// 5 The polynomial test
		int upper = (int)(Math.sqrt(Utils.totient(r)) * (Math.log(n) / Math.log(2)));
		for (int a = 1; a < upper; a += 1) {
			if(!polynomialTest(Long.valueOf(a), n, r))
				return false;
		}
		return true;
	}
	
	private static int log2(int bits) {
		return 31 - Integer.numberOfLeadingZeros(bits);
	}
	
	private static boolean polynomialTest(Long a, int n, int r) {
		Polynomial p = new Polynomial(Arrays.asList(new Long[] {a, 1l}));
		Polynomial m = new Polynomial();
			m.set(0, -1l);
			m.set(r, 1l);
		Polynomial t = new Polynomial();
			t.set(0, a);
			t.set(n, 1l);
		Polynomial q = p.modular_pow(n, m, n).minus(t).mod(m, n);
		return q.order() == -1;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i <= 1000; i++)
			System.out.println(i + ": " + AKS.aks(i));
	}
}
