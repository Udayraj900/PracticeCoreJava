package com.mindtree;

public class Solution {

	public static void main(String[] args) {
		int arr[] = {1 ,3 ,2 ,6 ,1 ,2};
		int result = divisibleSumPairs(6, 3, arr);
		System.out.println(result);
	}

	static int divisibleSumPairs(int n, int k, int[] ar) {
        int total = 0;
        for(int i=0; i<n; i++){
            for(int j=i+1; j< n; j++ ){
               int sum = ar[i]+ar[j];
                if(sum % k == 0)
                    total++;
            }
        }
        return total;
    }
}
