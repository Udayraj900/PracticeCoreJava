package HackerrankCodeTest;

public class DiagonalDifferenceTest {

	 static int diagonalDifference(int[][] arr) {
		 
		 int length = arr.length; 
		 int d1 = 0;
		 int d2 = 0;
		 for(int i=0; i<length; i++) {
			 d1 += arr[i][i];
			 d2 += arr[i][length-i-1];
		 }
		return Math.abs(d1-d2);
	    }
	public static void main(String[] args) {
		
        
        int arr[][] = 
        { 
            {11, 2, 4}, 
            {4 , 5, 6}, 
            {10, 8, -12} 
        }; 
		System.out.println(diagonalDifference(arr));
	}

}
