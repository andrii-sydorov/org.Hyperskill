package Comparator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Odd and even 
 * 
 * Write a method that takes a List of Integer numbers and
 * returns a List containing the same Integer numbers sorted according to the
 * following rules:
 * 
 * In the sorted List, odd numbers should be at the beginning in ascending order
 * and even numbers should be at the end in descending order. You don't need to
 * read or write anything from or to the console, just implement the method.
 * 
 * 
 * Sample Input 1:
 * 
 * 0 1 2 3 4 5 
 * 
 * Sample Output 1:
 * 
 * 1 3 5 4 2 0 
 * 
 * Sample Input 2:
 * 
 * 5 4 7 2 1 4 
 * 
 * Sample Output 2:
 * 
 * 1 5 7 4 4 2
 * 
 * @author SMD_ASY
 *
 */

public class OddAndEven {

	public static List<Integer> sortOddEven(List<Integer> numbers) {
		List<Integer> odd = numbers.stream().filter(x -> x % 2 != 0).collect(Collectors.toList());
		List<Integer> even = numbers.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
		odd.sort((x, y) -> x > y ? 1 : x < y ? -1 : 0);
		even.sort((x, y) -> x < y ? 1 : x > y ? -1 : 0);
		odd.addAll(even);
		return odd;
	}

}
