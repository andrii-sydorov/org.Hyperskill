package MapInterface;

import java.util.Scanner;
import java.util.Map;

/**
 * Implement these two methods for a given array of string:
 * 
 * wordCount that returns a SortedMap<String, Integer> where keys are all
 * different strings and values are the number of times that string appears in
 * the array. The method takes an array of strings as input; printMap that
 * prints all entries of the map ("key : value").
 * 
 * Report a typo
 * 
 * Sample Input 1:
 * 
 * a b b c d a b
 * 
 * Sample Output 1:
 * 
 * a : 2 
 * b : 3 
 * c : 1 
 * d : 1
 * 
 * @author ASY
 *
 */

public class CountAndPrint {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String[] line = sc.nextLine().split("\\s+");
		Map<String, Integer> map = MapUtils.wordCount(line);
		MapUtils.printMap(map);
		sc.close();
	}

}
