
public class AKS2 {
	public static boolean aks(int n) {
		if (n < 2) return false;
		
		// 1. Check if n = a**b
		for(int a = 2; a < n; a++) {
			for(int b = 2; b < n; b++) {
				int p = (int)Math.pow(a, b);
				//System.out.println("P is " + p);
				if (p > n)
					break;
				else if (p == n)
					return false;
			}
		}
		
		// 2. Find r such that o_r(n) > (log n)**2
		int logSquared = (int)Math.pow(Math.log(n) / Math.log(2), 2);
		//int logSquared = (int)Math.pow(log2(n), 2);
		int r = 2;
		
		// FIXME: infinite loop so I added r < n
		while (multiplicativeOrder(r, n) <= logSquared && r < n)
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
		
		// 5
		// No efficient way to calculate totient?
		throw new RuntimeException("TODO");
	}
	
	private static int multiplicativeOrder(int a, int n) {
		if (Utils.gcd(a, n) != 1)
			return 0;
		
		for(int i = 1; i < n; i++) {
			//System.out.println("ain" + a + i + n + ": " + Utils.modular_pow(a, i, n));
			if(Utils.modular_pow(a, i, n) == 1)
				return i;
		}
		throw new RuntimeException(
				"Failed to compute multiplicative order");
	}
	
	public static int log2(int bits) {
		// untested
		return 31 - Integer.numberOfLeadingZeros(bits);
	}
	
	public static void main(String[] args) {
		
		// quick test
		System.out.println(multiplicativeOrder(4, 7));
		
		for (int i = 0; i <= 20; i++)
			System.out.println(i + ": " + AKS2.aks(i));
	}
}
