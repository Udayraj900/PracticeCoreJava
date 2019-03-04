package Arrays;


/*
 * Given an Sorted array of integers ,
 * find the starting and ending position of a given target value.
 * 
 */
public class SearchForARange {

	public static void main(String[] args) {

		int[] nums = {1,1,2,2,3,4,4,4,4,9};
		
		int res[] = searchRange(nums, 4);
		for(int i=0;i < res.length;i++) {
			System.out.println(res[i]);
		}
	}
	
	public static int[] searchRange(int[] nums, int target) {
		
		int start = firstGreaterEqual(nums, target);
		System.out.println("==========");
		int end = firstGreaterEqual(nums, target + 1);
		return new int[] {start,end-1};
	}

	public static int firstGreaterEqual(int[] a, int target) {
		int low = 0,high = a.length;
		while (low < high) {
			int temp = (high - low) >> 1;
			System.out.println("===>"+ temp);
			int mid = low + ((high - low) >> 1);
			System.out.println(low);
			System.out.println(mid);
			System.out.println(high);
			System.out.println("--------");
			
			if(a[mid] < target) {
				low = mid+1;
			}else {
				high = mid;
			}
		}
			return low;
	}
}
