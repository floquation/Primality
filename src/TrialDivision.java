public class TrialDivision {
	public static boolean doTrialDivision(int val){
		int limit = (int)Math.sqrt(val);
		for(int i = 2; i<=limit; i++){
			if(val%i == 0){
				return false;
			}
		}
		return true;
	}
}
