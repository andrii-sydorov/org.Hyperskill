package MapInterface;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.SortedMap;

/**
 * Wow! This problem is kind of tricky. If you're ready to put your thinking cap
 * on, brace yourself and good luck! Otherwise, you can skip it for now and
 * return any time later
 * 
 * Modify and return the given map as follows:
 * 
 * if the first key % 2 != 0, return sub-map from firstKey inclusive to firstKey
 * + 4 inclusive in descending order; else return sub-map from lastKey – 4
 * inclusive to the lastKey inclusive in descending order.
 * 
 * Report a typo
 * 
 * Sample Input 1:
 * 
 * 1:one 2:two 3:three 4:four 5:five 6:six 7:seven
 * 
 * Sample Output 1:
 * 
 * 5 : five 4 : four 3 : three 2 : two 1 : one
 * 
 * Sample Input 2:
 * 
 * 2:two 4:four 6:six 8:eight 10:ten 12:twelve 14:fourteen
 * 
 * Sample Output 2:
 * 
 * 14 : fourteen 12 : twelve 1 0 : ten
 * 
 * @author SMD_ASY
 *
 */
public class TrickySubMaps {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		TreeMap<Integer, String> map = new TreeMap<>();
		Arrays.stream(scanner.nextLine().split("\\s")).forEach(s -> {
			String[] pair = s.split(":");
			map.put(Integer.parseInt(pair[0]), pair[1]);
		});
		TreeMap<Integer, String> map4 = (TreeMap<Integer, String>) map.clone();
		Map<Integer, String> res = MapUtils.getSubMap(map);
		res.forEach((k, v) -> System.out.println(k + " : " + v));
		scanner.close();
		System.out.println("Another method:");
		// only for test how to get exactly 4 values from map
		Map<Integer, String> res4 = MapUtils.getFourElements(map4, true);
		res4.forEach((k, v) -> System.out.println(k + " : " + v));
	}

}
