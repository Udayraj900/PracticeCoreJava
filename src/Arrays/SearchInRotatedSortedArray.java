package Arrays;

public class SearchInRotatedSortedArray {

	public static void main(String[] args) {

		int[] nums = { 20, 21, 22, 23, 24, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int res = searchRotatedArray(nums, 5);
		System.out.println("--------");
		System.out.println(res);
	}

	public static int searchRotatedArray(int[] nums, int target) {
		int low = 0, high = nums.length;
		while (low < high) {
			int mid = (low + high) / 2;
			System.out.println(low);
			System.out.println(mid);
			System.out.println(high);
			System.out.println();
			double num = (nums[mid] < nums[0]) == (target < nums[0]) ? nums[mid]
					: target < nums[0] ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
			System.out.println(num);
			if (num < target)
				low = mid + 1;
			else if (num > target)
				high = mid;
			else
				return mid;
		}
		return -1;
	}
}
