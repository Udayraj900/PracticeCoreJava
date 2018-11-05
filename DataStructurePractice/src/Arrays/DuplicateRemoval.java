package Arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a sorted array, 
 * remove the duplicates in place 
 * such that each element appear 
 * only once and return the new length.
 * 
 */
public class DuplicateRemoval {

	public static void main(String[] args) {

		int[] nums = {1,1,2,2,3,4,4,6,9,9};
		Set<Integer> listNum = new HashSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			listNum.add(nums[i]);
		}
		listNum.forEach(System.out::println);
		System.out.println("-----------");
		System.out.println(listNum.size());
		
		int b = removeDuplicate(nums);
		System.out.println(b);
	}

	public static int removeDuplicate(int[] A) {
		
		if(A.length == 0) return 0;
		
		int j=0;
		for(int i=1;i<A.length;i++) {
			if(A[i] != A[j])
				A[++j] = A[i];
			
		}
		System.out.println("-----------");
		for(int i=0;i<j+1;i++) {
			System.out.println(A[i]);
		}
		System.out.println("-----------");
		return ++j;
	}
}
