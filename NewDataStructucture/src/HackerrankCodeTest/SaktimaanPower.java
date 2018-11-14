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
	
	static int minPowerKru(int[] input) {
		
		int number = 0;
	      // no validations done
	      if(input[0]<1){
	          number=input[0]*-1 + 1;
	      }
	      int sum=input[0]+number;
	      for (int i = 1; i < input.length; i++) {
	          System.out.println("Number :"+ number +" sum:"+(sum));
	          sum+=input[i];
	          
	          if(sum<1){
	            number = number + Math.abs(sum)+1;
	            sum=1;
	          }
	         
	      }
	return number;
	}
	

	public static void main(String args[]) {

		int[] arr1 = { -5, 4, -2, 3, 1, -1, -6, -1, 0, 5 };
		int value = minPower(arr1);
		System.out.println(value);

		int[] arr1Kru = { -5, 4, -2, 3, 1, -1, -6, -1, 0, 5 };
		int valueKru = minPowerKru(arr1Kru);
		System.out.println(valueKru);
		
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
