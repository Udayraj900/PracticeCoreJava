package maps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DistinctCountOfCharsInAString {

	public static void main(String[] args) {
		String input1 = "aabbccdd3";
		printDistinctCharsWithCount(input1);	
	}

	private static void printDistinctCharsWithCount(String input) {
		
		Map<Character, Integer> charCountMap = new HashMap<>();
		List<Character> list = input.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
		list.stream().forEach(c -> charCountMap.merge(c, 1, Integer::sum));
		System.out.println(charCountMap);
	}
}
