package problems;

import java.util.Arrays;

public class Problem211 {
	
	private static long[] sums;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		int MAX = 64000000;
		long answer = 0;
		populateMap(MAX);
		System.out.println("Populate complete");
		for (int i=1; i<MAX; i++) {
			if (is_prefect_square(sums[i])) {
				System.out.println("The number is " + i);
				answer += i;
			}
		}
		System.out.println("The answer is " + answer);
		long end = System.currentTimeMillis();
		System.out.println("The program took " + (end - start)/1000 + 
				" seconds to complete");
	}

	private static boolean is_prefect_square(long sum) {
		long sqrt = Util.get_sqrt(sum);
		return sqrt * sqrt == sum;
	}

	private static void populateMap(int max) {
		sums = new long[max];
		// Start with 1 for every value of array
		Arrays.fill(sums, 1);
		long square;
		// Populate numbersAndSums map
		for (int i=2; i<max; i++) {
			square = (long)i * i;
			for (int j=i; j<max; j+=i) {
				sums[j] += square;
			}
		}
	}
}
