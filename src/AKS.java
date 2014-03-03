public class AKS {
	
	public static boolean doAKS(int n){
		
		
		throw new RuntimeException("Code cannot arrive here!");
	}
	
	private static long modular_pow(long a, long b, long m) {
		long  tempo;
		if (b ==0 ){
			tempo =  1;  //EXIT condition
		}//if
		else  if (b == 1) tempo = a;
			else { long temp = modular_pow(a,b/2,m);
            	if (b%2 == 0)
            		tempo = (temp*temp)%m;
            	else
            		tempo = ((temp*temp)%m)*a%m;
			}
		    return tempo;
	 }
		
}
