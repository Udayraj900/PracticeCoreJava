package HackerrankCodeTest_GS;

import java.util.Scanner;

public class BankProject {

	static String feeOrUpfront(int n, int k, int x, int d, int[] p) {
		double totalBankCharge = 0;
		for (int i = 0; i < n; i++) {
			double value = (p[i] * x) / 100.0;
			totalBankCharge += Math.max(k, value);
			
		}
		System.out.println(totalBankCharge);
		if (totalBankCharge > d)
			return "upfront";
		else
			return "fee";
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		/*int q = in.nextInt();
		for (int a0 = 0; a0 < q; a0++) {
			int n = in.nextInt();
			int k = in.nextInt();
			int x = in.nextInt();
			int d = in.nextInt();
			int[] p = new int[n];
			for (int p_i = 0; p_i < n; p_i++) {
				p[p_i] = in.nextInt();
			}*/
			int[] pp = {735,550,355,848,399,756,66,702,93,31,523,562,551,663,139,260,546,448,45,363,67,898,842,84,585,244,229,825,307,746,151,738,269,266,962,230,255,700,220,717,396,875,859,171,759,246,321,781,689,40,165,615,431,12,265,800,978,435,390,957,276,439,533,249,874,935,501,920,789,796,129,986,132,323,470,286,447,988,793,768,403,766,862,198,330,395,367,208,615,975,447,17,609,754,191,116,147,828,633,755};
			String result = feeOrUpfront(100, 160, 52, 28112, pp);
			System.out.println(result);
		//}
		in.close();
	}
}
