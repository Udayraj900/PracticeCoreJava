import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class Product {

	int id;
	String name;
	float price;

	public Product(int id, String name, float price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
}

public class LambdaExpressionExample {
	public static void main(String[] args) {
		List<Product> list = new ArrayList<Product>();
		list.add(new Product(1, "HP Laptop", 25000f));
		list.add(new Product(3, "Keyboard", 300f));
		list.add(new Product(2, "Dell Mouse", 150f));
		System.out.println("Sorting on the basis of name...");
		// implementing lambda expression code here
		Collections.sort(list, (p1, p2) -> {
			return p1.name.compareTo(p2.name);
		});
//		Collection.sort(list, (p1, p2) -> {
//			return p1.name.compareTo(p2.name);
//		});
//		Collections.sort(list, (p1, p2) -> {
//			return p1.name.compare(p2.name);
//		});
//		Collection.sort((list).p1, p2 -> {
//			return p1.name.compareTo(p2.name);
//		});
		for (Product p : list) {
			System.out.println(p.id + " " + p.name + " " + p.price);
		}
		
		
		//====================
		
		//	new Thread( Runnable -> System.out.println("Hello from thread")).start();
		 new Thread( () -> System.out.println("Hello from thread")).start();
		// new Thread( new Runnable -> System.out.println("Hello from thread")).start();
		// new Thread( (Runnable r) -> System.out.println("Hello from thread")).start();
		 
	}
}