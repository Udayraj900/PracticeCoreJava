package Strings;

import java.math.BigInteger;

public class Fibonacci {

	public static void main(String[] args) {
		fibonacciSeries(10);
		System.out.println();
		fibonacciTillGivenNumber(100);
		System.out.println();
		for (char c= 'A';  c<= 'Z'; c++) 
			System.out.print(c + " ");
		System.out.println();
		armstrongNumber(371);
		armstrongNumber(321);
		fectorial();
	}

	private static void fectorial() {
		int num = 100;
        BigInteger factorial = BigInteger.ONE;
        for(int i = 1; i <= num; ++i)
        {
            // factorial = factorial * i;
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        System.out.printf("Factorial of %d = %d", num, factorial);
	}

	private static void fibonacciTillGivenNumber(int n) {
		int t1 = 0, t2 = 1;
		System.out.print("Upto " + n + ": ");
		while (t1 <= n) {
			System.out.print(t1 + " + ");
			int sum = t1 + t2;
			t1 = t2;
			t2 = sum;
		}
	}

	private static void fibonacciSeries(int n) {
		long t1 = 0, t2 = 1;
		System.out.print("First " + n + " terms: ");
		for (long i = 1; i<=n; ++i) {
			System.out.print(t1 + " + ");
			long sum = t1 + t2;
			t1 = t2;
			t2 = sum;
		}
	}
	private static void armstrongNumber(int n) {
		int number = n,result=0;
		while(number!=0) {
			int reminder = number % 10;
			result = (int) (result + Math.pow(reminder, 3));
			number = number/10;
		}
		if(result == n)
            System.out.println(n + " is an Armstrong number.");
        else
            System.out.println(n + " is not an Armstrong number.");
	}
}
