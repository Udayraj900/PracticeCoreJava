package Strings;

import java.math.BigInteger;
import java.util.Locale;

public class NumberToString {
	private static final String[] lowNames = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight",
			"nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
			"nineteen" };

	private static final String[] tensNames = { "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty",
			"ninety" };

	private static final String[] bigNames = { "thousand", "lakh", "crore" };

	public static void main(String[] args) {
		System.out.println("-2: " + convertNumberToWords(-2));
		System.out.println("25: " + convertNumberToWords(25));
		System.out.println("12: " + convertNumberToWords(12));
		System.out.println("203: " + convertNumberToWords(203));
		System.out.println("2007: " + convertNumberToWords(2007));
		System.out.println("501: " + convertNumberToWords(501));
		System.out.println("280009: " + convertNumberToWords(280009));
		System.out.println("250908765: " + convertNumberToWords(250908765));
		
		System.out.println("=================");
		System.out.println("24: "+numberToWords(24));
	}

	/**
	 * Converts an integer number into words (American English).
	 * 
	 * @author Christian d'Heureuse, Inventec Informatik AG, Switzerland,
	 *         www.source-code.biz
	 **/
	public static String convertNumberToWords(int n) {
		if (n < 0) {
			return "minus " + convertNumberToWords(-n);
		}
		if (n <= 999) {
			return convert999(n);
		}
		String s = null;
		int t = 0;
		while (n > 0) {
			if (n % 1000 != 0) {
				String s2 = convert999(n % 1000);
				if (t > 0) {
					s2 = s2 + " " + bigNames[t - 1];
				}
				if (s == null) {
					s = s2;
				} else {
					s = s2 + ", " + s;
				}
			}
			n /= 1000;
			t++;
		}
		return s;
	}

	// Range 0 to 999.
	private static String convert999(int n) {
		String s1 = lowNames[n / 100] + " hundred";
		String s2 = convert99(n % 100);
		if (n <= 99) {
			return s2;
		} else if (n % 100 == 0) {
			return s1;
		} else {
			return s1 + " " + s2;
		}
	}

	// Range 0 to 99.
	private static String convert99(int n) {
		if (n < 20) {
			return lowNames[n];
		}
		String s = tensNames[n / 10 - 2];
		if (n % 10 == 0) {
			return s;
		}
		return s + "-" + lowNames[n % 10];
	}

	public static String numberToWords(long number) {
		if (number == 0) {
			return "zero";
		}
		if (number < 0) {
			return "minus " + numberToWords(Math.abs(number));
		}
		String words = "";
		if ((number / 10000000) > 0) {
			words += numberToWords(number / 10000000) + " Crore ";
			number %= 10000000;
		}
		if ((number / 100000) > 0) {
			words += numberToWords(number / 100000) + " Lakh ";
			number %= 100000;
		}
		if ((number / 1000) > 0) {
			words += numberToWords(number / 1000) + " Thousand ";
			number %= 1000;
		}
		if ((number / 100) > 0) {
			words += numberToWords(number / 100) + " Hundred ";
			number %= 100;
		}
		if (number > 0) {
			if (!words.equals("")) {
				words += "and ";
			}
			if (number < 20) {
				words += number;
			} else {
				words += (number);
				if ((number % 10) > 0) {
					words += "-" + (number % 10);
				}
			}
		}
		return words;
	}
}
