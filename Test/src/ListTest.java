import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListTest {

	public static void main(String[] args) {
		
		String obj1 = "xyz";

		String obj2 = new String("xyz");
		
		String obj3 = obj1;

		if(obj1 == obj2)
		   System.out.println("obj1==obj2 is TRUE");
		else
		  System.out.println("obj1==obj2 is FALSE");
		
		if(obj1.equals(obj2))
			 System.out.println("obj1.equals(obj2) is TRUE");
		else
		     System.out.println("obj1.equals(obj2) is FALSE");
		
		if(obj1 == obj3)
			   System.out.println("obj1==obj3 is TRUE");
			else
			  System.out.println("obj1==obj3 is FALSE");
		
		if(obj1.matches(obj2))
			   System.out.println("obj1==obj2 is TRUE");
			else
			  System.out.println("obj1==obj2 is FALSE");
		
		System.out.println(Math.round(10.55));
		System.out.println(Math.nextUp(10.55));
		System.out.println(Math.nextAfter(10.22, 0.01));
		System.out.println(Math.floor(10.55));
		
//		Predicate<String> p1 = s-> s.length()<2;
//		Predicate<String> p2 = Predicate.isEqual("car");
//		Function<Boolean, String> bool = i -> Boolean.toString(i);
//		Function<Boolean,String> stri = s->Boolean.parseBoolean(s);
//		System.out.println(ll);
		
		String a = null;
		System.out.println(a.length());
		
	}
}
