package betterplace;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NameOperation {
	static List<String> contacts = new ArrayList<>();

	public static void main(String[] args) {
		boolean exit = false;
		Scanner scanner = new Scanner(System.in);
		String input = "-1";
		System.out.println();
		do {
			System.out.println("1. Add Name");
			System.out.println("2. Find Name");
			System.out.println("3. Print All Names");
			System.out.println("0. Exit");
			System.out.println();
			System.out.print("Enter choice [1-3 or 0]: ");
			input = scanner.nextLine();
			switch (input) {
			case "1":
				System.out.println("Enter name to add...");
				String name = scanner.nextLine();
				addName(name);
				System.out.println("Name added successfully.");
				break;
			case "2":
				System.out.println("Enter name to find...");
				String namefind = scanner.nextLine();
				int count = findName(namefind);
				System.out.println(namefind + " is " + count + " times");
				break;
			case "3":
				System.out.println("Print All");
				for (String s : contacts) {
					System.out.println(s);
				}
				break;
			case "0":
				System.out.println("Thank You!!! Please visit again!!!");
				exit = true;
				break;
			default:
				System.out.println("Wrong choice!!!");
			}
		} while (!exit);
	}

	public static void addName(String name) {
		contacts.add(name);
	}

	public static int findName(String name) {
		int count = 0;
		int count2 = 0;
		for (String contact : contacts) {
			if (contact.contains(name))
				count++;
			if (contact.startsWith(name))
				count2++;
		}

		return count2;
	}
}
