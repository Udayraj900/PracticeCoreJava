package Arrays;

import java.util.HashMap;
import java.util.Map;
/*
 * problem: Given an array of an integers, 
 * return indices of the first two numbers 
 * such that they end up to a specific target.
 * Time
 */

public class TwoSum {

	public static void main(String[] args) {

		int[] nums = {2,1,11,8};
		int target = 3;
		int[] output = new int[2];
		for(int i=0; i<nums.length ; i++) {
		System.out.print(nums[i]);
		System.out.print(",");
		}
		output = twoSum(nums,target);
		System.out.println(output[0]);
		System.out.println(output[1]);
	}

	public static int[] twoSum(int[] nums,int target) {
		int[] arr = new int[2];
		Map<Integer, Integer> map = new HashMap<Integer,Integer>();
		for(int i=0; i<nums.length ; i++) {
			Integer val = map.get(target - nums[i]);
			System.out.println(val);
			if(val == null) {
				map.put(nums[i], i);
		}else {
			arr[0] = val;
			arr[1] = i;
			break;
		}
		}
		return arr;
	}
}
