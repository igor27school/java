package problems;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem211 {
	
	private static Map<Long, BigInteger> numbersAndSums = 
		new HashMap<Long, BigInteger>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int MAX = 64000000;
		long answer = 1;
		Util.init(MAX);
		System.out.println("Init complete");
		populateMap(MAX);
		System.out.println("Populate complete");
		for (int i=2; i<MAX; i++) {
			// Primes won't produce square sum: p^2 + 1
			if (Util.is_prime(i))
				continue;
			List<Integer> primes = Util.getPrimeFactorization(i);
			if (is_prefect_square(primes)) {
				System.out.println("The number is " + i);
				answer += i;
			}
		}
		System.out.println("The answer is " + answer);
	}

	// f(p^m * q^n) = f(p^m) * f(q^n)
	private static boolean is_prefect_square(List<Integer> primes) {
		BigInteger counter = BigInteger.ONE;
		for (Integer prime : primes) {
			counter = counter.multiply(numbersAndSums.get((long)prime));
		}
		return is_perfect_square(counter);
	}

	private static void populateMap(int max) {
		int number;
		long counter;
		// Populate numbersAndSums map
		for (int i=2; i<max; i++) {
			if (!Util.is_prime(i))
				continue;
			number = i;
			counter = number;
			while (counter < max) {
				BigInteger sum = get_sum(counter, number);
				numbersAndSums.put(counter, sum);
				counter *= number;
			}
		}
		
	}

	private static boolean is_perfect_square(BigInteger sum) {
		long sqrt = Util.get_sqrt(sum.longValue());
		return sqrt * sqrt == sum.longValue();
	}

	// f(p^m) = 1 + p^2 + ... + p^(2m) = (1 - p^(2m+2)) / (1 - p^2)
	private static BigInteger get_sum(long counter, int prime) {
		BigInteger b = BigInteger.valueOf((long)counter * prime);
		return b.pow(2).subtract(BigInteger.ONE).
		divide(BigInteger.valueOf(prime).pow(2).subtract(BigInteger.ONE));
	}

}
