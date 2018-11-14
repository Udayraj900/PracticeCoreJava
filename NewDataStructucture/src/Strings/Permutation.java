package Strings;

public class Permutation {
	public static void main(String[] args) {
		String s = new String("ABBA");
		permute(s);
	}

	public static void permute(String s) {
		permute("", s);
	}

	private static void permute(String prefix, String s) {
		int n = s.length();
		if (n == 0) {
			System.out.println(prefix);
		} else {
			for (int i = 0; i < n; i++) {
				permute(prefix + s.charAt(0), s.substring(0, i) + s.substring(i + 1, n));
			}
		}
	}
}
