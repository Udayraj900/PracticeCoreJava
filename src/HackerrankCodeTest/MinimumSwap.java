package HackerrankCodeTest;

import java.util.Arrays;

public class MinimumSwap {

	public static void main(String[] args) {
		int[] q = {4,1,3,5,7,2,6};
		
		//int[] q = {2,31,1,38,29,5,44,6,12,18,39,9,48,49,13,11,7,27,14,33,50,21,46,23,15,26,8,47,40,3,32,22,34,42,16,41,24,10,4,28,36,30,37,35,20,17,45,43,25,19};
		
	        System.out.print(Arrays.toString(q));
		System.out.println();
		int res = minimumSwaps(q);
		System.out.println("Minimun swap = "+res);
	}
	
	static int minimumSwaps(int[] arr) {
        int len = arr.length;
        int swap=0;int swapIndex=0;
        for(int i=0; i<len-1; i++){
        	int min = arr[i+1];
        	swapIndex = i+1;
            for(int j=i+2; j<len;j++) {
            	if(min>arr[j]) {
            		min=arr[j];
            		swapIndex = j;
            	}
            }
            if(min<=arr[i]) {
            	arr[swapIndex]=arr[i];
            	arr[i]=min;
            	swap++;
            }
        }
        System.out.println(Arrays.toString(arr));
        return swap;
    }

	
/*	static int minimumSwaps2(int[] arr) {
        int len = arr.length;
        int swap=0;int swapIndex=0;
        for(int i=0; i<len-1; i++){
        	int min = arr[i];
        	for(int j=i+1;j<len;j++) {
        		if(min>arr[j]) {
        			min=arr[j];
        		}
        	}
            	
        
    }
        return swap;
}*/
}
