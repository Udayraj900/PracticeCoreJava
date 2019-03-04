package HackerrankCodeTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.*;

public class SweetFrequency {

	static String potluckSweet(List<String> vote) {

		Map<String, Integer> voteMap = new HashMap<>();
		for (String s : vote) {
			if (voteMap.containsKey(s)) {
				//Integer count = voteMap.get(s);
				voteMap.put(s, voteMap.get(s)+1);
			} else {
				voteMap.put(s, 1);
			}
		}
		for(Map.Entry<String, Integer> entry: voteMap.entrySet()) {
			System.out.println(entry.getKey()+" ===> "+entry.getValue());
		}
		
		Map<String, Integer> sortedMap = voteMap.entrySet().stream().sorted(Entry.comparingByValue())
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
		
		
		for(Map.Entry<String, Integer> entry: sortedMap.entrySet()) {
			System.out.println(entry.getKey()+" ===> "+entry.getValue());
		}
		/*
		 * Entry<String,Integer> entry =
		 * sortedMap.entrySet().stream().findFirst().get(); return entry.getKey();
		 */
		String result = null;
		for (Entry<String, Integer> entry : sortedMap.entrySet()) {
			result = entry.getKey();
		}
		return result;
	}

	public static void main(String[] args) {
		List<String> vote = new ArrayList<>();
		vote.add("Gulab-jamun");
		vote.add("Gulab-jamun");
		vote.add("Jalebi");
		vote.add("Laddu");
		vote.add("Gulab-jamun");
		vote.add("Gajar-halwa");
		vote.add("Gajar-halwa");
		vote.add("Gajar-halwa");
		vote.add("Jalebi");
		vote.add("Laddu");
		vote.add("Barfi");
		vote.add("Gajar-halwa");

		String result = potluckSweet(vote);
		System.out.println(result);
	}

}
