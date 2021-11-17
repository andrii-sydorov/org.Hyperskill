package CollectionImplementation;

import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implement a method that takes an integer value and two lists of numbers. It
 * must check if the value occurs the same number of times in both sequences.
 * 
 * In the following input example, the first line contains the value, the second
 * line is the first list, the third line is another list.
 * 
 * All numbers are separated by whitespaces. Report a typo
 * 
 * Sample Input 1:
 * 
 * 3 
 * 8 8 3 3 2 
 * 1 3 3 2
 * 
 * Sample Output 1:
 * 
 * true
 * 
 * Sample Input 2:
 * 
 * 3 
 * 9 8 4 3 2 
 * 1 3 3 3
 * 
 * Sample Output 2:
 * 
 * false
 * 
 * @author ASY
 *
 */

public class CountOccurence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the desired value, which will be monitored: ");
		int elem = Integer.parseInt(sc.nextLine());
		System.out.println("Enter the elements of first list: ");
		List<Integer> ls1 = Arrays.stream(sc.nextLine().split("\\s+")).map(Integer::valueOf)
				.collect(Collectors.toList());
		System.out.println("Enter the elements of decond list: ");
		List<Integer> ls2 = Arrays.stream(sc.nextLine().split("\\s+")).map(Integer::valueOf)
				.collect(Collectors.toList());
		System.out.println(checkTheSameNumberOfTimes(elem, ls1, ls2));
		sc.close();
	}

	private static boolean checkTheSameNumberOfTimes(int elem, List<Integer> ls1, List<Integer> ls2) {
		int count = 0;
		for (Integer i : ls1) {
			if (elem == i) {
				count++;
			}
		}
		for (Integer i : ls2) {
			if (elem == i) {
				count--;
			}
		}
		return count == 0 ? true : false;
	}

}
