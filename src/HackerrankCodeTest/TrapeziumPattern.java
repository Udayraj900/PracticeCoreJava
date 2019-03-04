package HackerrankCodeTest;

import javax.jws.WebMethod;

public class TrapeziumPattern {

	public static void main(String[] args) {
		printTrapezium(3);
		System.out.println("=========================");
		printTrapezium_1(3);
	}

	public static void printTrapezium(int noOfLines) {
		// int noOfLines=3;
int i,j;
		int low = 1;
		int high = (noOfLines * (noOfLines + 1));

		for ( i = noOfLines; i > 0; i--) {

			int countPerLine = i;
			for ( j = 1; j <= noOfLines - i; j++) {
				System.out.print("  ");
			}
			for ( j = 1; j <= countPerLine; j++) {
				System.out.print((low++));
				System.out.print("*");
			}

			for ( j = high - countPerLine + 1; j <= high; j++) {
				System.out.print(j);
				if (j < high)
					System.out.print("*");
			}
			high = high - countPerLine;
			System.out.println();
		}
	}
	//@Override
	public static void printTrapezium_1(int num) {
		// int num = 3;
		int space;

		int i, j;

		int left = 1; // The terms on the LHS of the pattern

		// The terms on the RHS of the pattern
		int right = num * num + 1;

		for (i = num; i > 0; i--) {

			// To print number of spaces
			for (space = num; space > i; space--)
				System.out.print("  ");

			for (j = 1; j <= i; j++) {
				System.out.print(left);
				System.out.print("*");
				left++;
			}
			for (j = 1; j <= i; j++) {
				System.out.print(right);
				if (j < i)
					System.out.print("*");
				right++;
			}

			// To get the next term on RHS of the Pattern
			right = right - (i - 1) * 2 - 1;
			System.out.println();
		}
	}
}
