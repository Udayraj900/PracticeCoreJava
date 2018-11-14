package Arrays;

/*
 * Given an array and a value, remove all instances of 
 * that value in the place and return the new length.
 * 
 * don't use another array.
 */
public class RemovalOfElement {

	public static void main(String[] args) {

		int[] nums = {1,1,2,2,3,4,4,4,9,9};
		int res = removeElement(nums, 4);
		System.out.println(res);
	}

	public static int removeElement(int[] nums, int value) {
		
		if (nums.length == 0) return 0;

		int slow = 0;
		for(int fast = 0;fast<nums.length;fast++) {
			if(nums[fast] != value)
				nums[slow++] = nums[fast];
			
		}
		for(int i=0; i < slow; i++)
			System.out.println(nums[i]);
		return slow;
	
	}
}
