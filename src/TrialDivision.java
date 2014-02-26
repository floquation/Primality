
public class TrialDivision {
	

	public static boolean doTrialDivision(int val){
		for(int i = 2; i<=Math.sqrt(val); i++){
			if(val%i == 0){
				return false;
			}
		}
		return true;
		
	}

}
