package Arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class AsciiConversion {

	public static void main(String[] args) {

		char [] arr = new char[]{'a','b','c'};
		System.out.println(arr[1]);
		int [] ascii = new int[arr.length];
		Map<Character, Integer> map = new HashMap<>();
		
		for(Character c: arr) {
			map.put(c, (int) c);	
		}
		//System.out.println(int('a'));
	//	System.out.println(Arrays.toString(ascii));
		for(Entry<Character, Integer> enrty: map.entrySet()) {
			System.out.println(enrty.getKey()+"---"+enrty.getValue());
		}
	}

}
