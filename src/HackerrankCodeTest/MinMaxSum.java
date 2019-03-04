package HackerrankCodeTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MinMaxSum {

	 static void miniMaxSum(int[] arr) {
		 List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
		 Collections.sort(list);
		 long minSum = list.stream().limit(list.size()-1).mapToLong(Integer::intValue).sum();
		 long maxSum = list.stream().skip(1).mapToLong(Integer::intValue).sum();

		 System.out.println(minSum +" "+ maxSum);
	    }
	public static void main(String[] args) {
		int arr[] = { 1, 2, 9, 11, 5, 4 };
		miniMaxSum(arr);
	}

}
