import java.util.Arrays;
import java.util.List;

public class Calculator {
	public static void main(String args[]) {
		Calculator calculator = new Calculator();
		MathOperation addition = (int a, int b) -> a + b;
		MathOperation subtraction = (a, b) -> a - b;
		MathOperation multiplication = (int a, int b) -> {
			return a * b;
		};
		MathOperation division = (int a, int b) -> a / b;
		System.out.println("10 + 5 = " + calculator.operate(10, 5, addition));
		System.out.println("10 - 5 = " + calculator.operate(10, 5, subtraction));
		System.out.println("10 x 5 = " + calculator.operate(10, 5, multiplication));
		System.out.println("10 / 5 = " + calculator.operate(10, 5, division));
		
		
//		System.out.println("Print all items");
//		List<Integer> list = Arrays -> asList(5, 6, 7, 8, 9);
//		list.forEach(n -> System.out.println(n));
		
		System.out.println("Print all items"); 
		List<Integer> list2 = Arrays.asList(5,6,7,8,9);
		list2.forEach(n -> System.out.println(n));
		
//		System.out.println("Print all items"); 
//		List<Integer> list = Arrays.asList(5,6,7,8,9); 
//		list.foreach->(n -> System.out.println(n));
//		
//		System.out.println("Print all items");
//		List<Integer> list = Arrays.asList(5,6,7,8,9); 
//		list.foreach(n -> System.out.println(n));
	}

	interface MathOperation {
		int operation(int a, int b);
	}

	private int operate(int a, int b, MathOperation mathOperation) {
		return mathOperation.operation(a, b);
	}
}