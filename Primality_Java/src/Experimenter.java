import java.util.Random;


public class Experimenter {
	public static Random rng = new Random(0);
	public static int numTrials = 1000;
	public static int maxLog = 31;
	
	public static void main(String[] args) {
		long[] times = new long[maxLog];
		double[] results = new double[maxLog];
		
		for (int k = 0; k < maxLog; k++) {
			int[] nums = new int[numTrials];
			boolean[] isPrime = new boolean[numTrials];
			boolean[] looksPrime = new boolean[numTrials];
			
			// Nieuw idee
			int true_positives = 0;
			int positives = 0;
			
			for (int t = 0; t < numTrials; t++) {
				int lower = 1 << k;
				int upper = 2 << k;
				int toTest = lower + rng.nextInt(upper - lower);
				nums[t] = toTest;
				isPrime[t] = WheelSieve.wheelSieve(toTest);
				
				if(isPrime[t]) true_positives++;
			}
			
			long time = System.nanoTime();
			for (int t = 0; t < numTrials; t++) {
				int toTest = nums[t];
				//looksPrime[t] = MR.doMR(toTest);
				//looksPrime[t] = SS.ss(toTest, 1);
				//looksPrime[t] = TrialDivision.doTrialDivision(toTest);
				//looksPrime[t] = WheelSieve.wheelSieve(toTest);
				looksPrime[t] = AKS.aks(toTest);
			}
			times[k] = System.nanoTime() - time;
			
			results[k] = 0;
			for (int t = 0; t < numTrials; t++) {
				if(isPrime[t] == looksPrime[t])
					results[k] += 1;
				
				if(looksPrime[t]) positives++;
			}
			
			// HACK: Calculate the results using another method
			results[k] = 1 - (double)true_positives / positives;
			
			System.out.println((k + 1) + "\t" + times[k] + "\t" + results[k]);
		}
	}
}
