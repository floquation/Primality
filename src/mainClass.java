import java.util.Arrays;


public class mainClass {
	static long time;
	
	static int numberOfInputs = 1000;
	static int input_minValue = 4;
	static int input_maxValue = 1000000;
	
	static boolean[] results;
	static int[] inputs = new int[numberOfInputs];
	
	static boolean doTrialDivision = true;
	static boolean doSS = true;
	static boolean doAKS = true;
	static boolean doMR = true;
	
	public static void main(String[] args) {
		//Generate Input:
		for(int i = 0; i<numberOfInputs; i++){
			inputs[i] = (int)Math.round(Math.random()*(input_maxValue-input_minValue)) + input_minValue;
		}
		Arrays.sort(inputs);

		//All algorithms:
		
		//TrialDivision:
		if(doTrialDivision){
			results = new boolean[numberOfInputs];
			time = System.nanoTime();
			for(int i = 0; i<numberOfInputs; i++){
				results[i] = TrialDivision.doTrialDivision(inputs[i]);
			}
			time = System.nanoTime() - time;
			
			//Output:
			for(int i = 0; i<numberOfInputs; i++){
				System.out.println("The number " + inputs[i] + " is " + (results[i]?"":"not ") + "a prime");
			}
			System.out.println("Execution time was: " + time/1000000000d + "s");
			results = null;
		}
		
		//SS:
		if(doSS){
			results = new boolean[numberOfInputs];
			time = System.nanoTime();
			for(int i = 0; i<numberOfInputs; i++){
				//results[i] = SS.doSS(inputs[i]);
			}
			time = System.nanoTime() - time;
			
			//Output:
			for(int i = 0; i<numberOfInputs; i++){
				System.out.println("The number " + inputs[i] + " is " + (results[i]?"":"not ") + "a prime");
			}
			System.out.println("Execution time was: " + time/1000000000d + "s");
			results = null;
		}
		
		//AKS:
		if(doAKS){
			results = new boolean[numberOfInputs];
			time = System.nanoTime();
			for(int i = 0; i<numberOfInputs; i++){
				//results[i] = AKS.doAKS(inputs[i]);
			}
			time = System.nanoTime() - time;
			
			//Output:
			for(int i = 0; i<numberOfInputs; i++){
				System.out.println("The number " + inputs[i] + " is " + (results[i]?"":"not ") + "a prime");
			}
			System.out.println("Execution time was: " + time/1000000000d + "s");
			results = null;
		}
		
		//MR:
		if(doMR){
			results = new boolean[numberOfInputs];
			time = System.nanoTime();
			for(int i = 0; i<numberOfInputs; i++){
				//results[i] = MR.doMR(inputs[i]);
			}
			time = System.nanoTime() - time;
			
			//Output:
			for(int i = 0; i<numberOfInputs; i++){
				System.out.println("The number " + inputs[i] + " is " + (results[i]?"":"not ") + "a prime");
			}
			System.out.println("Execution time was: " + time/1000000000d + "s");
			results = null;
		}
		
		
	}

}
