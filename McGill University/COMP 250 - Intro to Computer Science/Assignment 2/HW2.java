import java.util.*;

// ALEXANDER BRATYSHKIN
// 260684228

public class HW2 {

	/**
	 * Method which calculates factorial iteratively
	 * 
	 * @param n number you wish to perform factorial operation on
	 * @return factorial
	 */
	public static long factorial(int n) {
		long result = 1;

		for (int i = 1; i <= n; i++) result = result * i;
		
		return result;
	}

	/**
	 * Method which calculates the binomial coefficient recursively
	 * 
	 * @param number of possibilities
	 * @param k unordered outcomes
	 * @return binomial coefficient
	 */
	public static int BCoeffRec(int n, int k) {

		int result;

		if (k == 0 || k == n)
			return result = 1; // base case
		else if (1 <= k && k <= (n - 1))
			return result = BCoeffRec(n - 1, k) + BCoeffRec(n - 1, k - 1); // inductive
																			// step

		else
			return result = 0;

	}

	/**
	 * Method which calculates the binomial coefficient iteratively
	 * 
	 * @param n number of possibilities
	 * @param k unordered outcomes
	 * @return binomial coefficient
	 */
	public static long BCoeffExp(int n, int k) {

		return factorial(n) / (factorial(n - k) * factorial(k));

	}

	/* calculate execution time of BCoeffRec(n,k) */
	public static long TimeBcoeffRec(int n, int k) {
		long startTime = System.nanoTime();
		int bc = BCoeffRec(n, k);
		long endTime = System.nanoTime();

		long duration = (endTime - startTime) / 1000; // time in microseconds.

		return duration;
	}

	/* calculate execution time of BCoeffExp(n,k) */
	public static long TimeBcoeffExp(int n, int k) {
		long startTime = System.nanoTime();
		long bc = BCoeffExp(n, k);
		long endTime = System.nanoTime();

		long duration = (endTime - startTime) / 1000; // time in microseconds.

		return duration;
	}

	/**
	 * Method which returns running times of recursive and iterative binomial
	 * coefficient methods
	 * 
	 * @param n number of possibilities
	 */
	public static void benchmark(int n) {

		for (int i = 0; i <= n; i++) {
			System.out
					.println("(" + n + " , " + i + ")   " + TimeBcoeffRec(n, i) + "   " + TimeBcoeffExp(n, i) + "   ");
		}

	}

	/*********************************************************************/
	/* Main */
	/*                                                                   */
	/* Note: You can comment the appropriate line below for testing each */
	/* method individually */
	/*                                                                   */
	/*********************************************************************/

	public static void main(String[] args) {

		// This variable determines the upper bound of n for the test.
		int depthtest = 20;

		// Test for Question 3(b)
		System.out.println(">> Test factorial");
		for (int i = 0; i <= depthtest; i++) {
			System.out.println(String.valueOf(i) + "! = " + String.valueOf(factorial(i)));

		}

		// Test for Question 4(b)
		System.out.println();
		System.out.println(">> Test of Binomial Coefficient Recursive");
		for (int n = 0; n <= depthtest; n++) {
			for (int k = 0; k <= n; k++) {
				int bcoeffval = BCoeffRec(n, k);
				System.out.print(String.valueOf(bcoeffval) + "\t");
			}
			System.out.println();
		}

		// Test for Question 4(c)
		System.out.println();
		System.out.println(">> Test of Binomial Coefficient Explicit");
		for (int n = 0; n <= depthtest; n++) {
			for (int k = 0; k <= n; k++) {
				long bcoeffval = BCoeffExp(n, k);

				System.out.print(String.valueOf(bcoeffval) + "\t");
			}
			System.out.println();
		}

		// Test for Question 4(d)
		System.out.println();
		System.out.println(">> Execution time benchmark");
		System.out.println(">> n=10");
		benchmark(10);
		System.out.println(">> n=20");
		benchmark(20);

	}
}