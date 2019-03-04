package Arrays;



	public class MplusNArray {

		public static void main(String[] args) {

			/* Initialize arrays */
			int mPlusN[] = { 2, 8, -1, -1, -1, 13, -1, 15, 20 };
			int N[] = { 5, 7, 9, 25 };
			int n = N.length;
			int m = mPlusN.length - n;

			/* Move the m elements at the end of mPlusN */
			moveToEnd(mPlusN, m + n);
			printArray(mPlusN, m + n);
			
			/* Merge N[] into mPlusN[] */
			merge(mPlusN, N, m, n);

			/* Print the resultant mPlusN */
			printArray(mPlusN, m + n);
		}
		 static void printArray(int arr[], int size)  
		    { 
		        int i; 
		        for (i = 0; i < size; i++)  
		            System.out.print(arr[i] + " "); 
		  
		        System.out.println(""); 
		    } 
		  
		 static void moveToEnd(int mPlusN[], int size)  
		    { 
		        int i, j = size - 1; 
		        for (i = size - 1; i >= 0; i--)  
		        { 
		            if (mPlusN[i] != -1)  
		            { 
		                mPlusN[j] = mPlusN[i]; 
		                j--; 
		            } 
		        } 
		    } 
		 
		 static void merge(int mPlusN[], int N[], int m, int n)  
		    { 
		        int i = n; 
		          
		        /* Current index of i/p part of mPlusN[]*/
		        int j = 0; 
		          
		        /* Current index of N[]*/
		        int k = 0; 
		          
		        /* Current index of of output mPlusN[]*/
		        while (k < (m + n))  
		        { 
		            /* Take an element from mPlusN[] if 
		            a) value of the picked element is smaller and we have 
		                not reached end of it 
		            b) We have reached end of N[] */
		            if ((i < (m + n) && mPlusN[i] <= N[j]) || (j == n))  
		            { 
		                mPlusN[k] = mPlusN[i]; 
		                k++; 
		                i++; 
		            }  
		            else // Otherwise take element from N[] 
		            { 
		                mPlusN[k] = N[j]; 
		                k++; 
		                j++; 
		            } 
		        } 
		    } 
	}


