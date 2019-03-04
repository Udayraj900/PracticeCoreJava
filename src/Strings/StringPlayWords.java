package Strings;

import java.util.Arrays;

public class StringPlayWords {

	public static void main(String[] args) {

		String str = "Hello java geeks of geeks name";
		System.out.println(str);
		String res = reverseStringType1(str);
		System.out.println(res);
	}
	public static String[] reverseStrings(String str) {
		String[] strArray = str.split(" ");
		String[] resArray = new String[strArray.length];
		int index = strArray.length-1;
		for(String s:strArray) {
			resArray[index]= s;
			index--;
		}
		return resArray;
	}
	public static String reverseStringType1(String str) {
		String[] strArray = reverseStrings(str);
	/*	String first = strArray[0];
		String last = strArray[strArray.length-1];
		strArray[0] = last;
		strArray[strArray.length-1]=first;*/
		
		
		String result="";
		System.out.println(Arrays.toString(strArray));
		for(int i=1;i<strArray.length-1;i++) {
			char[] chArr = strArray[i].toCharArray();
			char firstChar = chArr[0];
			char lastChar = chArr[strArray[i].length()-1];
			chArr[0] = lastChar;
			chArr[strArray[i].length()-1] = firstChar;
			strArray[i] = String.valueOf(chArr);
			
		}
		System.out.println(Arrays.toString(strArray));
	for(String s: strArray) {
		result+=s+" ";
	}
		return result.trim();
	}

}
