package problems;

import java.math.BigInteger;
import java.util.HashMap;

public class Problem169 {

	private static HashMap<BigInteger, BigInteger> f_values = new HashMap<BigInteger, BigInteger>();
	private final static BigInteger zero = BigInteger.ZERO;
	private final static BigInteger one = BigInteger.ONE;
	private final static BigInteger two = BigInteger.valueOf(2l);
	private final static BigInteger ten = BigInteger.TEN;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		populate_initial_f_values();
		BigInteger number = ten.pow(25);
		System.out.println("f(" + number + ") = " + get_f(number));
	}

	private static void populate_initial_f_values() {
		f_values.put(zero, one);
		f_values.put(one, one);
	}

	/*
	 * Proving f(2n + 1) = f(n):
	 * For every summation of n, we can generate a summation of 2n+1 by:
	 * a) Increasing the order of all powers by 1
	 * b) Adding 1
	 * Example:  4 = 2 + 1 + 1 => 9 = (4 + 2 + 2) + 1
	 * For every summation of 2n + 1, we can generate a summation of n by:
	 * a) Removing the 1 (since the number is odd, it appears exactly one
	 * b) Decreasing the order of all the powers by 1
	 * It's easy to see there are one-to-one mappings.
	 * 
	 * Proving f(2n+2) = f(n) + f(n+1):
	 * To transform a f(n) solution to f(2n+2) solution:
	 * a) Increase the order of all the powers by 1
	 * b) Add 1 + 1 to summation
	 * To transform a f(n+1) solution to f(2n+2) solution:
	 * a) Increase the order of all powers by 1
	 */
	private static BigInteger get_f(BigInteger number) {
		if (f_values.containsKey(number))
			return f_values.get(number);
		BigInteger new_value;
		BigInteger half = number.divide(two);	
		if (number.remainder(two).equals(one))
			new_value = get_f(half);
		else
			new_value = get_f(half).add(get_f(half.subtract(one)));
		f_values.put(number, new_value);
		return new_value;
	}
}
