package problems;

import java.util.LinkedList;
import java.util.List;

public class Util {
	// Easier to use composites since we'll be marking things as not prime
	private static boolean[] composites;

	public static void init(int max) {
		composites = new boolean[max];
		composites[0] = true;
		composites[1] = true;
		long sqrt = get_sqrt(max);
		for (int i=2; i<= sqrt; i++) {
			if (composites[i])
				continue;
			for (int j=i*i; j<max; j+=i) {
				composites[j] = true;
			}
		}
	}
	
	// Gets the integer part of the sqrt
	public static long get_sqrt(long number) {
		long sqrt = Math.round(Math.sqrt(number));
		if (sqrt*sqrt > number)
			sqrt--;
		return sqrt;
	}
	
	public static boolean is_prime(int i) {
		return !composites[i];
	}
	
	public static List<Integer> getPrimeFactorization(int number) {
		List<Integer> primes = new LinkedList<Integer>();
		int currentPrime;
		long sqrt = get_sqrt(number);
		for (int i=2; i<=sqrt && number > 1; i++) {
			currentPrime = 1;
			while (number % i == 0) {
				currentPrime *= i;
				number /= i;
			}
			if (currentPrime > 1)
				primes.add(currentPrime);
		}
		// If the number contains a prime larger than its square root
		if (number > 1)
			primes.add(number);
		return primes;
	}
}
