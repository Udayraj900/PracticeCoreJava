package inheritence;

/**
 * 
 * @author RShaw : If a class inherits a method (abstract or concrete) from its
 *         superclass and a method with the same signature from one of its
 *         super-interfaces, then the class inherits the method of the
 *         superclass and the methods in the super-interfaces are ignored.
 * 
 *         This rule treats a default method in an interface as a fallback if
 *         the same method is not available in the class through the class
 *         hierarchy.
 *
 * 
 */

public class DemoClass extends ClassA implements InterfaceA {
	public static void main(String[] args) {
		System.out.println(new DemoClass().getMessage());
	}

}
