package Lambdas;

import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

		names.sort((name1,name2) -> name1.compareTo(name2));
		System.out.println(names);

		names.sort((name1,name2) -> name1.length() - name2.length());
		System.out.println(names);

		names.sort((name1,name2) -> name2.length() - name1.length());
		System.out.println(names);


	}
}
/*Sorting with Lambdas

Write a program to sort a list of strings in different ways using lambda expressions:
		1.	Sort alphabetically.
		2.	Sort by length.
	3.	Sort by reverse length.

Hint: Use Collections.sort() or List.sort() with a lambda expression as the comparator.
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");*/

// 1. Sort alphabetically
// 2. Sort by length
// 3. Sort by reverse length