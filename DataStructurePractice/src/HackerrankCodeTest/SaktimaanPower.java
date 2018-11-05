package HackerrankCodeTest;

public class SaktimaanPower {

	static int minPower(int[] nums) {
		int minimumPower = 1;
		int currentPower = minimumPower;
		for (int i = 0; i < nums.length; i++) {
			if (currentPower + nums[i] > 0) {
				currentPower = currentPower + nums[i];
			} else {
				currentPower = currentPower + nums[i];
				if (currentPower < 0) {
					minimumPower = minimumPower + Math.abs(currentPower);
					currentPower = minimumPower;
				} else if (currentPower == 0) {
					minimumPower = minimumPower + Math.abs(currentPower) + 1;
					currentPower = minimumPower;
				}
				i = -1;
			}
		}
		return minimumPower;
	}

	public static void main(String args[]) {

		int[] arr1 = { -5, 4, -2, 3, 1, -1, -6, -1, 0, 5 };
		int value = minPower(arr1);
		System.out.println(value);

		int[] arr2 = { -5, 4, -2, 3, 1 };
		int value2 = minPower(arr2);
		System.out.println(value2);

		int[] arr3 = { -5, 4, -2, 3, 1, -1, -6, -1, 0, -5 };
		int value3 = minPower(arr3);
		System.out.println(value3);

		
		  int[] arr4 = { -5, -4, -2, 3, 1, -1, -6, -1, 0, 5 }; 
		  int value4 = minPower(arr4); 
		  System.out.println(value4);
		 
	}
}
