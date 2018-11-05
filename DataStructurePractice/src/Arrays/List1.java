package Arrays;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
 
public class List1 {
 
    public static void main(String[] args) {
 
        List<Integer> list = new ArrayList<Integer>();
        
        Integer[] arr = {2,10,3};
        list = Arrays.asList(arr);
        list.set(0, 3);
        System.out.println(list);//[3, 10, 3]
        
        list.add(1);
        System.out.println(list);//Exception in thread "main" java.lang.UnsupportedOperationException
        /**
         * Arrays.asList() returns a fixed-size list backed by the specified array. 
         * Therefore, the arraylist can't grow.
         */
    }
 
}