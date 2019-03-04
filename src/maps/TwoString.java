package maps;

import java.util.Set;
import java.util.stream.Collectors;

public class TwoString {

	public static void main(String[] args) {
		String s1 = "abc";
		String s2 = "ab";
		System.out.println(twoStrings(s1, s2));
		System.out.println(twoStrings("hello", "world"));
		System.out.println(twoStrings("hi", "world"));
		System.out.println(twoStrings("adfvc", "vbers"));
		System.out.println(twoStrings("name", "fame"));
		
	}

	static String twoStrings(String s1, String s2) {
		Set<Character> setS1 = s1.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());
        Set<Character> setS2 = s2.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());
        String res = "YES";
        if (s1.length() <= s2.length()) {
            char[] ch = new char[s1.length()];
            int i=0;
            for (Character c : setS1) {
                if(setS2.contains(c))
                    ch[i++] = 'Y';
                    else
                    i++;
            }
            for(char c:ch){
            if(c == 'Y')
            return "YES";
            
        }
        } else {
            char[] ch = new char[s2.length()];
             int i=0;
            for (Character c : setS2) {
                if(setS1.contains(c))
                    ch[i++] = 'Y';
                     else
                    i++;
            }
            for(char c:ch){
            if(c == 'Y')
            return "YES";
            
        }
        }
        
        return "NO";
	}

}
