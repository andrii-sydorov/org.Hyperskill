package SetInterface;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.Objects;

/**
 * The symmetric difference of two sets is the set that contains elements that
 * are in either of the sets but not in their intersection. In other words, only
 * those elements that are present in one set and not present in the other.
 * Check out the illustration for better understanding:
 * 
 * Implement a method for finding the symmetric difference of the two given sets
 * of strings. Elements in the resulting set can be in any order.
 * 
 * Example:
 * 
 * The symmetric difference of two sets {1, 2, 3} and {0, 1, 2} is {0, 3} Report
 * a typo
 * 
 * Sample Input 1:
 * 
 * 1 2 3 
 * 0 1 2
 * 
 * Sample Output 1:
 * 
 * 0 3
 * 
 * @author ASY
 *
 */

public class CalculateSymmetricDifference {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the first set:");
		String str1 = sc.nextLine();
		System.out.println("Enter the second set:");
		String str2 = sc.nextLine();
		sc.close();
		Set<String> set1 = new HashSet<>();
		Set<String> set2 = new HashSet<>();

		if (!Objects.equals(str1, "empty")) {
			Collections.addAll(set1, str1.split("\\s+"));
		}

		if (!Objects.equals(str2, "empty")) {
			Collections.addAll(set2, str2.split("\\s+"));
		}

		Set<String> resultSet = SetUtils1.symmetricDifference(set1, set2);

		System.out.println(String.join(" ", resultSet));
	}

}

class SetUtils1 {
	public static Set<String> symmetricDifference(Set<String> set1, Set<String> set2) {
		Set<String> toSummarize = new HashSet<>(set1);
		toSummarize.addAll(set2);
		Set<String> toBeRemoved = new HashSet<>(set1);
		toBeRemoved.retainAll(set2);
		toSummarize.removeAll(toBeRemoved);

		return toSummarize;
	}
}
