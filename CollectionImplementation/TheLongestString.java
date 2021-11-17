package CollectionImplementation;

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

/**
 * Implement the changeList method so that it:
 * 
 * finds the longest string in the list replaces all list items with the found
 * string
 * 
 * Report a typo
 * 
 * Sample Input 1:
 * 
 * hi hello goodmorning
 * 
 * Sample Output 1:
 * 
 * goodmorning goodmorning goodmorning
 * 
 * @author ASY
 *
 */

public class TheLongestString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter your list: ");
		Scanner sc = new Scanner(System.in);
		List<String> list = Arrays.asList(sc.nextLine().replaceAll(" [^A-Za-z] ", " ").split("\\s+"));
		changeList(list);
		sc.close();
		list.forEach(e -> System.out.print(e + " "));
	}

	public static void changeList(List<String> list) {
		String requiredString = null;
		int max = Integer.MIN_VALUE;
		for (String s : list) {
			if (s.length() > max) {
				max = s.length();
				requiredString = s;
			}
		}
		int size = list.size();
		for (int i = 0; i < size; i++) {
			list.set(i, requiredString);
		}
	}
}
