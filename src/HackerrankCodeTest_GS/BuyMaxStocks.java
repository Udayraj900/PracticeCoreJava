package HackerrankCodeTest_GS;

import java.util.ArrayList;
import java.util.Iterator;

public class BuyMaxStocks {

	public static void main(String[] args) {
		int[] a = { 26, 94, 80, 59, 32, 3, 38, 91, 93, 33, 78, 4, 79, 98, 58, 60, 20, 57, 34, 33, 62, 79, 3, 69, 63, 22,
				12, 14, 35, 6, 61, 74, 39, 75, 31, 72, 28, 70, 28, 94, 78, 62, 57, 29, 17, 92, 1, 12, 64, 27, 31, 17,
				97, 74, 29, 27, 57, 74, 97, 38, 9, 80, 31, 83, 62, 53, 84, 91, 92, 39, 20, 45, 64, 27, 39, 56, 76, 20,
				46, 63, 85, 3, 66, 54, 57, 91, 12, 68, 98, 72, 78, 48, 95, 17, 33, 87, 11, 68, 82, 53 };
		long res = buyMaximumProducts(100, 167121, a);
		// int[] a = {49,100,100,100,10};
		// long res = buyMaximumProducts(5, 50, a);
		System.out.println(res);
	}
	// 3868

	static long buyMaximumProducts(int n, long k, int[] a) {
		long totalNoOfStock = 0L;
		long totalAmount = 0L;
		ArrayList<Keys> arl = new ArrayList<>();

		for (int i = n - 1; i >= 0; i--) {
			arl.add(new Keys(a[i], i + 1));
		}
		arl.sort((c, b) -> ((c.getSharevalue() - b.getSharevalue() == 0 ? c.getNumber() - b.getNumber()
				: c.getSharevalue() - b.getSharevalue())));
		
		for (Keys keys : arl) {
			totalNoOfStock += (keys.getNumber());
			totalAmount += ((keys.getNumber()) * (keys.getSharevalue()));
			System.out.println(totalAmount + " " + keys.getNumber() + " " + keys.getSharevalue());
			if (totalAmount > k) {
				long excess = totalAmount - k;
				System.out.println("excess" + excess);
				long multiple = (excess / keys.getSharevalue()) + ((keys.getSharevalue()) != 0 ? 1 : 0);
				System.out.println("multiple" + multiple);
				totalNoOfStock -= multiple;
				return totalNoOfStock;
			} else if (totalAmount == k) {
				return totalNoOfStock;
			}
		}

		return totalNoOfStock;
	}

}

class Keys {
	private int sharevalue;
	private int number;

	public Keys(int sharevalue, int number) {
		super();
		this.sharevalue = sharevalue;
		this.number = number;
	}

	public int getSharevalue() {
		return sharevalue;
	}

	public void setSharevalue(int sharevalue) {
		this.sharevalue = sharevalue;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
