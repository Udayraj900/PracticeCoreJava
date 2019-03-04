package HackerrankCodeTest_GS;

public class TradeProfit {

	public static void main(String[] args) {

		int[] a = { 10, 22, 5, 75, 65, 80 };
		System.out.println(traderProfit_Raj(2, 6, a));

		int[] b = { 20, 580, 420, 900 };
		System.out.println(traderProfit_Raj(3, 4, b));

		int[] c = { 100, 90, 80, 50, 25 };
		System.out.println(traderProfit_Raj(1, 5, c));
	}

	static void print2DArray(int[][] arr,int row, int col) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j <= col-1; j++)
				System.out.print(arr[i][j] + " ");
			System.out.println();
		}

		System.out.println("===================");
	}

	static int traderProfit_Raj(int k, int n, int[] A) {

		int profit[][] = new int[k+1][n+1];

		for (int i = 0; i < k; i++)
			profit[i][0] = 0;
		for (int j = 0; j <= n; j++)
			profit[0][j] = 0;

		for (int i = 1; i <= k; i++) {
			print2DArray(profit,k+1,n+1);
			int prevDiff = Integer.MIN_VALUE;
			for (int j = 1; j < n; j++) {
				prevDiff = Math.max(prevDiff, profit[i - 1][j - 1] - A[j - 1]);
				profit[i][j] = Math.max(profit[i][j - 1], A[j] + prevDiff);
			}
		}
		return profit[k][n - 1];
	}

	static int traderProfit(int k, int n, int[] A) {

		int tempArr[][] = new int[n][n];

		for (int i = 0; i < tempArr.length; i++) {
			for (int j = i; j < tempArr.length; j++) {
				tempArr[i][j] = A[j] - A[i];
				System.out.print(tempArr[i][j] + " ");
			}
			System.out.println();
		}

		int total = 0;
		int row = n;
		int col = n;
		for (int i = 0; i < k; i++) {
			Values values = calculateMax(tempArr, row, col);
			System.out.println(values);
			if (values.max == 0)
				break;
			row = values.row;
			col = values.row;
			total += values.max;

			System.out.println("row" + row + " max" + total);

		}

		return total;
	}

	static class Values {
		int row;
		int col, max;

		public Values(int max, int row, int col) {
			super();
			this.row = row;
			this.col = col;
			this.max = max;
		}

		@Override
		public String toString() {
			return "Values [row=" + row + ", col=" + col + ", max=" + max + "]";
		}

	}

	static Values calculateMax(int arr[][], int i, int j) {
		int max = Integer.MIN_VALUE;
		int j2 = -1;
		int k = -1;
		int row = 0, col = 0;
		for (j2 = 0; j2 < i; j2++) {
			for (k = 0; k < j; k++) {
				if (arr[j2][k] > max) {
					max = arr[j2][k];
					row = j2;
					col = k;
				}
			}
		}

		return new Values((max > 0 ? max : 0), row, col);
	}
}
