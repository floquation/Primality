import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


public class mainClass {

	/* INPUT */
	static int numberOfInputs = 1000;
	static int input_minValue = 4;
	static int input_maxValue = 500;
	
	static boolean doTrialDivision = true;
	static boolean doSS = true;
	static boolean doAKS = true;
	static boolean doMR = true;
	static boolean doWheelSieve = true;	
	
	
	/* OUTPUT */
	static boolean appendFiles = true; //Turn to false to create new files, overwriting all existing ones.
	
	static String MR_fileName = "MR.txt";
	static String AKS_fileName = "AKS.txt";
	static String SS_fileName = "SS.txt";
	static String TrialDivision_fileName = "TD.txt";
	static String WheelSieve_fileName = "WS.txt";

	
	/* VARIABLES */
	static long time;
	
	static boolean[] results;
	static boolean[] correctResults;
	static int[] inputs = new int[numberOfInputs];
	
	
	/* CODE */
	
	
	private static boolean writeToFile(String fileName, int N, int minValue, int maxValue, double[] error, double time, boolean append){
		FileWriter fw;
		try {
			fw = new FileWriter(fileName,append);
			
			fw.write(N + ", " + minValue + ", " + maxValue + ", " + time + ", " + (1-error[0]) + ", " + (1-error[1]) + "\n");			
			
			
			fw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
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
				writeToFile(TrialDivision_fileName, numberOfInputs, input_minValue, input_maxValue, new double[]{0,0}, time/1000000000d, appendFiles);
				
				//Output:
				System.out.println("<<Trial Division>>");
				/*for(int i = 0; i<numberOfInputs; i++){
					System.out.println("The number " + inputs[i] + " is " + (correctResults[i]?"":"not ") + "a prime");
				}*/
				System.out.println("Execution time was: " + time/1000000000d + "s");
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
			System.out.println("<<SS>>");
			/*for(int i = 0; i<numberOfInputs; i++){
				System.out.println("(SS) The number " + inputs[i] + " is " + (results[i]?"a prime":"a composite") + " -- " +
						(correctResults[i] == results[i]?"correct.":"INCORRECT!!!"));
			}*/
			for(int i = 0; i<numberOfInputs; i++){
				if(correctResults[i] != results[i] && correctResults[i] == true)
				System.out.println("(SS) The number " + inputs[i] + " is " + (results[i]?"a prime":"a composite") + " -- " +
					(correctResults[i] == results[i]?"correct.":"INCORRECT!!!"));
			}
			System.out.println("Execution time was: " + time/1000000000d + "s");
			
			error = computeError(results,correctResults);
			System.out.println("Primes fraction predicted correct = " + (1-error[0]) + "; Composite fraction predicted correct = " + (1-error[1]));
			
			writeToFile(SS_fileName, numberOfInputs, input_minValue, input_maxValue, error, time/1000000000d, appendFiles);
			
			results = null;
		}
		
		//AKS:
		if(doAKS){
			results = new boolean[numberOfInputs];
			time = System.nanoTime();
			for(int i = 0; i<numberOfInputs; i++){
				results[i] = AKS.aks(inputs[i]);
			}
			time = System.nanoTime() - time;

			//Output:
			System.out.println("<<AKS>>");
			for(int i = 0; i<numberOfInputs; i++){
				System.out.println("(AKS) The number " + inputs[i] + " is " + (results[i]?"a prime":"a composite") + " -- " +
						(correctResults[i] == results[i]?"correct.":"INCORRECT!!!"));
			}
			System.out.println("Execution time was: " + time/1000000000d + "s");
			
			error = computeError(results,correctResults);
			System.out.println("Primes fraction predicted correct = " + (1-error[0]) + "; Composite fraction predicted correct = " + (1-error[1]));
			
			writeToFile(AKS_fileName, numberOfInputs, input_minValue, input_maxValue, error, time/1000000000d, appendFiles);
			
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
			System.out.println("<<MR>>");
			/*for(int i = 0; i<numberOfInputs; i++){
				System.out.println("(MR) The number " + inputs[i] + " is " + (results[i]?"a prime":"a composite") + " -- " +
						(correctResults[i] == results[i]?"correct.":"INCORRECT!!!"));
			}*/
			for(int i = 0; i<numberOfInputs; i++){
				if(correctResults[i] != results[i] && correctResults[i] == true)
				System.out.println("(MR) The number " + inputs[i] + " is " + (results[i]?"a prime":"a composite") + " -- " +
					(correctResults[i] == results[i]?"correct.":"INCORRECT!!!"));
			}
			System.out.println("Execution time was: " + time/1000000000d + "s");
			
			error = computeError(results,correctResults);
			System.out.println("Primes fraction predicted correct = " + (1-error[0]) + "; Composite fraction predicted correct = " + (1-error[1]));
			
			writeToFile(MR_fileName, numberOfInputs, input_minValue, input_maxValue, error, time/1000000000d, appendFiles);
			
			results = null;
		}
		
		//WheelSieve:
		if(doWheelSieve){
			results = new boolean[numberOfInputs];
			time = System.nanoTime();
			System.out.println("<<Wheel-Sieve>>");
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
			
			writeToFile(WheelSieve_fileName, numberOfInputs, input_minValue, input_maxValue, error, time/1000000000d, appendFiles);
			
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
