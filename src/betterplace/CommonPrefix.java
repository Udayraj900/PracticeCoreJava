package betterplace;

import org.apache.commons.lang3.StringUtils;

public class CommonPrefix {
	public static final String EMPTY = "";

	public static void main(String[] args) {

		String str1[] = { "geeksforgeeks", "geeks", "geek", "geezer" };

		String str2[] = { "apple", "ape", "april" };

		String s = StringUtils.getCommonPrefix(str1);
		System.out.println(s);
		System.out.println("==============");
		String s1 =getCommonLongestPrefix(str1);
		System.out.println(s1);
		String s2 =getCommonLongestPrefix(str2);
		System.out.println(s2);
	}

	public static String getCommonLongestPrefix(String strs[]) {

		if (strs == null || strs.length == 0) {
			return EMPTY;
		}
		if (strs.length == 1) {
			return strs[0];
		}
		String prefix = "";
		int minLen = strs[0].length();//Assume first string is the shortest one. 
		for (int i = 0; i < minLen; i++) {
			for (int j = 1; j < strs.length; j++) {
				if(strs[0].charAt(i) != strs[j].charAt(i)) {
					return prefix;
				}
				minLen = Math.min(minLen, strs[j].length());//check and change the min length 
			} prefix = prefix+ strs[0].charAt(i);

		}
		return prefix;
	}

	public static int getLengthOfShortestString(String[] strs) {
		int minLen = strs[0].length();
		for (String s : strs) {
			if (minLen > s.length()) {
				minLen = s.length();
			}
		}
		return minLen;
	}

}
