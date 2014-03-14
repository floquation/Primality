import java.util.Arrays;

public class AKS {
	public static boolean aks(int n) {
		if (n < 2) return false;
		
		// 1. Check if n = a**b
		for(int b = 2; b <= (int)(Math.log(n)/Math.log(2)); b++) {
			double a = Math.pow(n,1d/b);
			if(Math.floor(a) == a) return false;
		}
		
		// 2. Find r such that o_r(n) > (log n)**2
		int logSquared = (int)Math.pow(Math.log(n) / Math.log(2), 2);
		//int logSquared = (int)Math.pow(log2(n), 2);
		
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
		
		//System.out.println("eindbaas");
		
		// 5 The polynomial test
		int upper = (int)(Math.sqrt(Utils.totient(r)) * (Math.log(n) / Math.log(2)));
		//System.out.println("Uppertop " + upper);
		//System.out.println("R " + r);
		for (int a = 1; a < upper; a += 1) {
			if(!polynomialTest(Long.valueOf(a), n, r))
				{//System.out.println("a: " + a);
				return false;}
		}
		return true;
	}
	
	public static int log2(int bits) {
		// untested
		return 31 - Integer.numberOfLeadingZeros(bits);
	}
	
	public static boolean polynomialTest(Long a, int n, int r) {
		Polynomial p = new Polynomial(Arrays.asList(new Long[] {a, 1l}));
		Polynomial m = new Polynomial();
			m.set(0, -1l);
			m.set(r, 1l);
		Polynomial t = new Polynomial();
			t.set(0, a);
			t.set(n, 1l);
		Polynomial q = p.modexp(n, m, n).minus(t).mod(m, n);
		/*System.out.println("p: " + p);
		System.out.println("m: " + m);
		System.out.println("t: " + t);
		System.out.println(p.modexp(n, m, n));
		System.out.println(p.modexp(n, m, n).minus(t));
		System.out.println(p.modexp(n, m, n).minus(t).mod(m, n));
		System.out.println("p^n mod (m, n): " + q);*/
		return q.order() == -1;
	}
	
	public static void main(String[] args) {
		//for (int i = 0; i <= 1000; i++)
			//System.out.println(i + ": " + AKS.aks(i));
		
		/*
		// Crashes on i = 3955
		Exception in thread "main" java.lang.RuntimeException: Failed to compute multiplicative order
			at AKS2.multiplicativeOrder(AKS2.java:56)
			at AKS2.aks(AKS2.java:18)
			at AKS2.main(AKS2.java:73)
		Deze lijkt nu opgelost te zijn
		
		En dit zijn vermoedelijk round-off issues:
		(AKS) The number 34123 is a composite -- INCORRECT!!!
		(AKS) The number 40471 is a composite -- INCORRECT!!!
		(AKS) The number 40483 is a composite -- INCORRECT!!!
		(AKS) The number 47189 is a composite -- INCORRECT!!!
		//System.out.println(AKS2.aks(3955));
		// En nu we voor alles long's gebruiken krijgen we dit weer:
		(AKS) The number 257 is a composite -- INCORRECT!!!
		(AKS) The number 257 is a composite -- INCORRECT!!!
		*/
		
		//System.out.println(AKS.aks(257));
		
		System.out.println("Polynomial: " + polynomialTest(1l, 257, 71));
	}
}
