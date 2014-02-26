public class MR {
	
	public static boolean doMR(int n){
		//Exclude even numbers:
		if(n%2==0) return false;
		
		//Step1: compute r and R
		int r = 0;		
		int R = n-1;
		while(true){
			r++;
			R = R/2;
			if(R%2==1)break;
		}

		System.out.println(">> n = " + n);
		System.out.println("r = " + r + "; R = " + R);
		
		System.out.println(modular_pow(432,5432,315));
		
		//Step2: choose a random a between 1 and n
		int a = (int)Math.ceil(Math.random()*n);
		
		System.out.println("a = " + a);

		//Step4: Composite if b_r := a^(n-1) != 1 (mod n)
		System.out.println(Math.pow(a, n-1)%n);
		if(Math.pow(a, n-1)%n != 1){
			return false;
		}
		
		//Step5: Prime if b_0 := a^R == 1 (mod n)
		int b_0 = (int)Math.pow(a, R);
		if(b_0%n == 1){
			return true;
		}
		
		//Step3: compute b_i = a^(2^i*R)
		int b;
		for(int i = r-1; i>=0; i--){
			b = (int)Math.pow(a,Math.pow(2,i)*R);
			
			//Step 6: find j := max{i|b_i != 1 (mod n)}
			if(b%n!=1){
				//The current i is j
				//Step 7: if b == -1 (mod n) -> Prime
				if(b%n==-1){
					return true;
				}else{
					return false;
				}
			}
		}
		
		throw new RuntimeException("Code cannot arrive here!");
	}
	
	public static int modular_pow(int base, int exponent, int modulus){
	    int c = 1;
	    for (int e_prime = 1; e_prime <= exponent; e_prime++){ 
	        c = (c * base) % modulus;
	    }
	    return c;
	}
}
