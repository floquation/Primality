import java.util.Arrays;


public class mainClass {
	static long time;
	
	static int numberOfInputs = 1000;
	static int input_minValue = 4;
	static int input_maxValue = 1000000;
	
	static boolean[] results = new boolean[numberOfInputs];
	static int[] inputs = new int[numberOfInputs];
	
	public static void main(String[] args) {
		//Input:
		for(int i = 0; i<numberOfInputs; i++){
			inputs[i] = (int)Math.round(Math.random()*(input_maxValue-input_minValue)) + input_minValue;
		}
		Arrays.sort(inputs);

		//Algorithm:
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
	}

}
