package SetInterface;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implement two methods.
 * 
 * The first method should create a set from a string of numbers separated by a
 * space.
 * 
 * The second method should remove all numbers greater than 10 from the given
 * set.
 * 
 * Report a typo Sample Input 1:
 * 
 * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 
 * 
 * Sample Output 1:
 * 
 * 1 2 3 4 5 6 7 8 9 10
 * 
 * @author SMD_ASY
 *
 */

public class RemoveElements {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your elements: ");
		String str = sc.nextLine();
		Set<Integer> set = SetUtils.getSetFromString(str);
		SetUtils.removeAllNumbersGreaterThan10(set);
		set.forEach(e -> System.out.print(e + " "));
		sc.close();
	}

}

class SetUtils {

	public static Set<Integer> getSetFromString(String str) {
		// write your code here
		Set<Integer> s = Arrays.stream(str.split("\\s+")).map(Integer::valueOf).collect(Collectors.toSet());
		return s;
	}

	public static void removeAllNumbersGreaterThan10(Set<Integer> set) {
		set.removeIf(x -> x > 10);
	}

}
