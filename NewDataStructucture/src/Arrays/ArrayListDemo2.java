package Arrays;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArrayListDemo2 {

	@SuppressWarnings("unlikely-arg-type") 
	public static void main(String[] args) {

		/**
		 * Question 1
		 */
		List<Integer> list = new ArrayList<Integer>();
		list.add(10);
		list.add(10);
		System.out.print("size 1 : " + list.size());// 2
		list.remove(new Integer(10));// remove first occurrence of the matching element that should be an object
		System.out.print("\nsize 2 : " + list.size());// 1
		try {
			list.remove(10); // java.lang.IndexOutOfBoundsException: Index: 10, Size: 1
		} catch (Exception e) {
			System.out.println("\n java.lang.IndexOutOfBoundsException :" + e.getMessage());
		}

		/**
		 * Question 2
		 */
		Set<Student> students = new HashSet<Student>();

		students.add(new Student(1));
		students.add(new Student(3));
		students.add(new Student(4));
		students.add(new Student(1));
		students.add(new Student(3));

		System.out.println(students.size());

		/**
		 * Question 3
		 */
		Set<Short> set = new HashSet<Short>();
		for (Short i = 0; i < 10; i++) {
			set.add(i);
			set.remove(i - 1);
			// The set contains Shorts and we are trying to remove integers from it. i-1 is
			// integer.
		}
		System.out.println(set.size());// 10

		/**
		 * Question 4
		 */
		ArrayDeque<String> adq = new ArrayDeque<String>();
		//push() and addFirst() add elements at front of queue and add() and offer() add elements at end of queue.
		
		adq.add("A"); //add elements at end of queue
		adq.push("B"); // add at the front of the queue
		adq.addFirst("C"); //add at the front of the queue
		adq.offer("D"); //add elements at end of queue
		// [A] -> [B A] -> [C B A] ->	[C B A D]
		
		System.out.println(adq.peek() + " " + adq.pop() + " " + adq.poll());// [C C B]
		
		//peek() just retrieves the element, does not remove it.
		//pop() and poll() remove from front of queue.
		for (String s: adq) {
			System.out.println(s);// [A D]
		}
	}
	
	/**
	 * Question 5
	 */

}