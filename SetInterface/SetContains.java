package SetInterface;

import java.util.Scanner;
import java.util.Set;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * In this problem, you need to implement a math operation that checks whether
 * the second set is a strict superset of the first set.
 * 
 * It means, the second set should contain all elements of the first set, but
 * the sets must not be equal. Report a typo
 * 
 * Sample Input 1:
 * 
 * 1 2 3 
 * 4 1 2 3 
 * 
 * Sample Output 1:
 * 
 * true
 * 
 * Sample Input 2:
 * 
 * b a c 
 * e c d b
 * 
 * Sample Output 2:
 * 
 * false
 * 
 * Sample Input 3:
 * 
 * a b c 
 * c b a
 * 
 * Sample Output 3:
 * 
 * false
 * 
 * @author ASY
 *
 */

public class SetContains {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the first set:");
		Set<String> set1 = readStringSet(sc);
		System.out.println("Enter the second set:");
		Set<String> set2 = readStringSet(sc);
		sc.close();
		System.out.println(isStrictSuperset(set1, set2));
	}

	public static Set<String> readStringSet(Scanner sc) {
		return Arrays.stream(sc.nextLine().split("\\s+")).collect(Collectors.toSet());
	}

	public static <T> boolean isStrictSuperset(Set<T> set1, Set<T> set2) {
		for (T s : set1) {
			if (set2.contains(s)) {
				continue;
			}
			return false;
		}
		return true & !Objects.equals(set1, set2);
	}
}
