package Vector;

import java.util.Vector;

public class Vector1 {

	public static void main(String[] args) {
		Vector<Integer> vector = new Vector<Integer>();
        //Printing Current Capacity of Vector
        System.out.println(vector.capacity());      //Output : 3
        //Adding 4 elements (greater than the capacity) to vector
        vector.add(10);
        vector.add(20);
        vector.add(30);
        vector.add(40);
        Vector<Integer> vector1 = (Vector<Integer>) vector.clone();
       // System.out.println(vector1.capacity()); 
        //again Printing Current Capacity of Vector
       // System.out.println(vector.capacity());     //Output : 6
        
       
        
        vector.add(50);
        vector.add(60);
        vector.add(70);
        vector.add(50);
        vector.add(60);
        vector.add(70);
       
        
        System.out.println(vector.capacity());  
        vector.add(70);
        System.out.println(vector.capacity());  
       // System.out.println(vector.capacity()); 
       /* for (Integer integer : vector) {
			System.out.println(integer);
		}
        for (Integer integer : vector1) {
			System.out.println(integer);
		}*/
	}

}
