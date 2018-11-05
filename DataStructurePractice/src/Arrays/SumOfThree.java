package Arrays;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * Problem: Given an array S of n integers, are there elements a, b, c in S 
 * such that a+b+c = 0 ?
 * find all unique triplets in the array which gives the sum of zero. 
 * 
 */
public class SumOfThree {

	public static void main(String[] args) {

		int[] nums = { -1, 0, 1, 2, -1, -4 };
		List<List<Integer>> result = threeSum(nums);
		result.forEach(System.out::println);
	}

	public static List<List<Integer>> threeSum(int[] num) {

		Arrays.sort(num);
		List<List<Integer>> result = new LinkedList<>();
		for (int i = 0; i < num.length - 2; i++) {

			if (i == 0 || (i > 0 && num[i] != num[i - 1])) {
				int low = i + 1;
				int high = num.length - 1;
				int sum = 0 - num[i];
				while (low < high) {
					if (num[low] + num[high] == sum) {
						result.add(Arrays.asList(num[i], num[low], num[high]));
						while (low < high && num[low] == num[low + 1]) {
							low++;
						}
						while (low < high && num[high] == num[high - 1]) {
							high--;
						}
						low++;
						high--;
					} else if (num[low] + num[high] < sum)
						low++;
					else
						high--;
				}
			}
		}
		return result;
	}

}
