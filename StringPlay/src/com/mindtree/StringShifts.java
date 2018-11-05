package com.mindtree;

public class StringShifts {

	public static void main(String[] args) {
		 String input = "Stackoverflow";
		 int pos = 10
				 ;
		 if(pos > input.length()) {
			System.out.println("invalid pos"); 
		 }
		 else {
		 System.out.println(input); 
		 input = leftShift(input,pos);
		 System.out.println(input); 
		 input = rightShift(input,pos);       
		 System.out.println(input); }
		   
	}

		public static String rightShift(String s,int pos) {
			//System.out.println(s.substring(s.length()-pos, s.length()));
			//System.out.println(s.substring(0, s.length()-pos));
		    return s.substring(s.length()-pos, s.length()) + s.substring(0, s.length()-pos);
		}
		public static String leftShift(String s, int pos) {
		    return s.substring(pos, s.length())+s.substring(0, pos);
		}
}
