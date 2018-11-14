package HackerrankCodeTest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class PlucMinus {

	static void plusMinus(int[] arr) {
		Double positive = 0.0;
		Double negative = 0.0;
		Double zero = 0.0;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 0) {
				positive++;
			} else if (arr[i] < 0) {
				negative++;
			} else {
				zero++;
			}
		}

		BigDecimal[] array = new BigDecimal[] { new BigDecimal(positive / arr.length).setScale(6, RoundingMode.CEILING),
				new BigDecimal(negative / arr.length).setScale(6, RoundingMode.HALF_EVEN),
				new BigDecimal(zero / arr.length).setScale(6, RoundingMode.CEILING) };

		for (BigDecimal i : array) {
			System.out.println(i);
		}

	}

	public static void main(String[] args) {

		int arr[] = { -1, -2, 0, 11, 2, 4 };

		plusMinus(arr);
	}
}
