package unitTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import problems.Util;

public class TestUtil {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Util.init(1000000);
	}
	
	@Test
	public void testPrimeness() {
		assertTrue("0 is not prime", !Util.is_prime(0));
		assertTrue("1 is not prime", !Util.is_prime(1));
		assertTrue("2 is prime", Util.is_prime(2));
		assertTrue("3 is prime", Util.is_prime(3));
		assertTrue("4 is not prime", !Util.is_prime(4));
		assertTrue("139*139 is not prime", !Util.is_prime(139*139));
	}
	
	@Test
	public void testPrimeFactorization() {
		List<Integer> primes = Util.getPrimeFactorization(1);
		assertTrue("The list is " + primes.toString(), primes.size() == 0);
		primes = Util.getPrimeFactorization(0);
		assertTrue("The list is " + primes.toString(), primes.size() == 0);
		primes = Util.getPrimeFactorization(144);
		assertTrue("The list is " + primes.toString(), primes.size() == 2);
		assertTrue("The list is " + primes.toString(), primes.get(0) == 16);
		assertTrue("The list is " + primes.toString(), primes.get(1) == 9);
		primes = Util.getPrimeFactorization(12345);
		assertTrue("The list is " + primes.toString(), primes.size() == 3);
		assertTrue("The list is " + primes.toString(), primes.get(0) == 3);
		assertTrue("The list is " + primes.toString(), primes.get(1) == 5);
		assertTrue("The list is " + primes.toString(), primes.get(2) == 823);
	}

}
