package betterplace;

/**
 * @author M1023322
 *
 *         find the nth fibonacci number.
 */
public class Fibonacci {

	public static void main(String[] args) {
		
		System.out.println(fibonacciNumber(4));
		 System.out.println(fibonacciNumber(100));
	}

	public static long fibonacciNumber(long n) {
		
		/*long t1 = 0, t2 = 1;
		long sum=0;
		System.out.print("First " + n + " terms: ");
		for (long i = 1; i<n; i++) {
			System.out.print(t1 + " + ");
			 sum = t1 + t2;
			 t1 = t2;
			 t2 = sum;
		}
		return t1;
*/		
		
		long t1 = 0, t2 = 1;
		long fib = 0;

		for (long i = 1; i <= n; i++) {
			System.out.print(t1 + " + ");
				fib = t1 + t2;
				t1 = t2;
				t2 = fib;
			
		}
		System.out.println();
		return t1;
	}
}
