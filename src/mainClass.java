import java.util.Arrays;


public class mainClass {
	static long time;
	
	static int numberOfInputs = 50000;
	static int input_minValue = 4;
	static int input_maxValue = 10000;
	
	static boolean[] results;
	static boolean[] correctResults;
	static int[] inputs = new int[numberOfInputs];
	
	static boolean doTrialDivision = false;
	static boolean doSS = true;
	static boolean doAKS = false;
	static boolean doMR = false;
	static boolean doWheelSieve = true;
	
	public static void main(String[] args) {
		double[] error;
		
		//Generate Input:
		for(int i = 0; i<numberOfInputs; i++){
			inputs[i] = (int)Math.round(Math.random()*(input_maxValue-input_minValue)) + input_minValue;
		}
		inputs[0] = 7;
		inputs[1] = 34123;
		inputs[2] = 40471;
		inputs[3] = 40483;
		inputs[4] = 47189;
		inputs[5] = 3;
		Arrays.sort(inputs);

		//All algorithms:
		
		//Correct result:
			correctResults = new boolean[numberOfInputs];
			//TrialDivision:
			time = System.nanoTime();
			for(int i = 0; i<numberOfInputs; i++){
				correctResults[i] = TrialDivision.doTrialDivision(inputs[i]);
			}
			time = System.nanoTime() - time;
			
			if(doTrialDivision){
				//Output:
				for(int i = 0; i<numberOfInputs; i++){
					System.out.println("The number " + inputs[i] + " is " + (correctResults[i]?"":"not ") + "a prime");
				}
				System.out.println("Execution time was: " + time/1000000000d + "s");
				correctResults = null;
			}
			
		
		//SS:
		if(doSS){
			results = new boolean[numberOfInputs];
			time = System.nanoTime();
			for(int i = 0; i<numberOfInputs; i++){
				results[i] = SS.ss(inputs[i], 1);
			}
			time = System.nanoTime() - time;
			
			//Output:
			/*for(int i = 0; i<numberOfInputs; i++){
				System.out.println("(SS) The number " + inputs[i] + " is " + (results[i]?"a prime":"a composite") + " -- " +
						(correctResults[i] == results[i]?"correct.":"INCORRECT!!!"));
			}*/
			System.out.println("Execution time was: " + time/1000000000d + "s");
			
			error = computeError(results,correctResults);
			System.out.println("Primes fraction predicted correct = " + (1-error[0]) + "; Composite fraction predicted correct = " + (1-error[1]));
			results = null;
		}
		
		//AKS:
		if(doAKS){
			results = new boolean[numberOfInputs];
			time = System.nanoTime();
			for(int i = 0; i<numberOfInputs; i++){
				results[i] = AKS.doAKS(inputs[i]);
			}
			time = System.nanoTime() - time;

			//Output:
			for(int i = 0; i<numberOfInputs; i++){
				System.out.println("(AKS) The number " + inputs[i] + " is " + (results[i]?"a prime":"a composite") + " -- " +
						(correctResults[i] == results[i]?"correct.":"INCORRECT!!!"));
			}
			System.out.println("Execution time was: " + time/1000000000d + "s");
			
			error = computeError(results,correctResults);
			System.out.println("Primes fraction predicted correct = " + (1-error[0]) + "; Composite fraction predicted correct = " + (1-error[1]));
			results = null;
		}
		
		//MR:
		if(doMR){
			results = new boolean[numberOfInputs];
			time = System.nanoTime();
			for(int i = 0; i<numberOfInputs; i++){
				results[i] = MR.doMR(inputs[i]);
			}
			time = System.nanoTime() - time;
			
			//Output:
			/*for(int i = 0; i<numberOfInputs; i++){
				System.out.println("(MR) The number " + inputs[i] + " is " + (results[i]?"a prime":"a composite") + " -- " +
						(correctResults[i] == results[i]?"correct.":"INCORRECT!!!"));
			}*/
			System.out.println("Execution time was: " + time/1000000000d + "s");
			
			error = computeError(results,correctResults);
			System.out.println("Primes fraction predicted correct = " + (1-error[0]) + "; Composite fraction predicted correct = " + (1-error[1]));
			results = null;
		}
		
		//WheelSieve:
		if(doWheelSieve){
			results = new boolean[numberOfInputs];
			time = System.nanoTime();
			for(int i = 0; i<numberOfInputs; i++){
				results[i] = WheelSieve.wheelSieve(inputs[i]);
				if (results[i] != correctResults[i])
					System.out.println("hallo" + inputs[i] + results[i]);
			}
			time = System.nanoTime() - time;

			//Output:
				/*for(int i = 0; i<numberOfInputs; i++){
						System.out.println("(MR) The number " + inputs[i] + " is " + (results[i]?"a prime":"a composite") + " -- " +
								(correctResults[i] == results[i]?"correct.":"INCORRECT!!!"));
					}*/
			System.out.println("Execution time was: " + time/1000000000d + "s");

			error = computeError(results,correctResults);
			System.out.println("Primes fraction predicted correct = " + (1-error[0]) + "; Composite fraction predicted correct = " + (1-error[1]));
			results = null;
		}
		
		
	}
	
	/*
	 * Input: boolean[N] inst (to-be-checked) and boolean[N] ref (correct answers)
	 * Output: double[2]: {fraction wrong prime, fraction wrong composite}
	 */
	public static double[] computeError(boolean[] inst, boolean[] ref){
		double[] error = new double[]{0,0};
		
		if(inst.length != ref.length) throw new RuntimeException("Error in your code... Dude...");
		
		int numPrimes = 0, numComposites = 0;
		
		for(int i = 0; i<inst.length; i++){
			//System.out.println(inst[i] + " = inst ; ref = " + ref[i]);
			if(ref[i] == true){
				//Prime
				numPrimes ++;
				if(inst[i] == true){
					//Correct prime
					
				}else{
					error[0] ++;
				}
			}else{
				//Composite
				numComposites ++;
				if(inst[i] == false){
					//Correct composite
					
				}else{
					error[1] ++;
				}
				
			}
		}
		error[0] /= numPrimes;
		error[1] /= numComposites;
		
		return error;
	}

}
