package maps;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SortArrayByFrequency {

	public static void main(String[] args) {

		int[] arr = { 2, 2, 5, 5, 6, 8, 8, 8 };

		sortByFrequency(arr, 9);

	}

	public static void sortByFrequency(int[] arr, int size) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		//Set<E> set = new HashSet<>();
		// store map with frequency as value and Integer as key
		for (int i : arr) {
			if (map.containsKey(i)) {
				System.out.println(map.get(i));
				Integer count = map.get(i);
				map.put(i, ++count);
			} else {
				map.put(i, 1);
			}
		}
		// sort map by frequency(value) using comparator
		List<Map.Entry<Integer, Integer>> list = new LinkedList<>(map.entrySet());

		Collections.sort(list,
				(Comparator<Entry<Integer, Integer>>) (i1, i2) -> i1.getValue().compareTo(i2.getValue()));

		Map<Integer, Integer> sorteMap = new LinkedHashMap<>();

		for (Map.Entry<Integer, Integer> entry : list) {
			sorteMap.put(entry.getKey(), entry.getValue());
		}
		
		printMap(sorteMap);
		
		
		// directly from lambda 
		Map<Integer, Integer> sortedMap = map.entrySet().stream()
				.sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(val1,val2)->val1,LinkedHashMap::new));

		printMap(sortedMap);
	}

	public static <K, V> void printMap(Map<K, V> map) {
		for (Map.Entry<K, V> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "----->" + entry.getValue());
		}
}}
