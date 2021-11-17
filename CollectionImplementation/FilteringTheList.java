package CollectionImplementation;

import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Write a program that reads the list of integer numbers separated by spaces
 * from the standard input and then remove all numbers with even indexes (0, 2,
 * 4, and so on).
 * 
 * After that, the program should output the resulting sequence in the reverse
 * order. Report a typo
 * 
 * Sample Input 1:
 * 
 * 1 2 3 4 5 6 7
 * 
 * Sample Output 1:
 * 
 * 6 4 2
 * 
 * Sample Input 2:
 * 
 * 1 2
 * 
 * Sample Output 2:
 * 
 * 2
 * 
 * Sample Input 3:
 * 
 * 7 6 -5 -4 -3 2 1
 * 
 * Sample Output 3:
 * 
 * 2 -4 6
 * 
 * @author ASY
 *
 */

public class FilteringTheList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the numbes: ");
		List<Integer> ls = Arrays.stream(sc.nextLine().split("\\s+")).map(Integer::valueOf)
				.collect(Collectors.toList());
		sc.close();
		System.out.println(deleteEvenIndexes(ls).toString().replace("[", "").replaceAll(",", "").replace("]", ""));
	}

	private static List<Integer> deleteEvenIndexes(List<Integer> ls) {
		List<Integer> ans = new ArrayList<>();
		for (int i = 0; i < ls.size(); i++) {
			if (i % 2 != 0) {
				ans.add(ls.get(i));
			}
		}
		Collections.reverse(ans);
		return ans;
	}

}
