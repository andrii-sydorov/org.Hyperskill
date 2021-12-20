package MapInterface;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

/**
 * You need to implement the mapShare method that should modify the given map as
 * follows:
 * 
 * if the key "a" has a value, set the key "b" to have that same value. In all
 * cases remove the key "c", leaving the rest of the map unmodified.
 * 
 * {"a":"abstraction","b":"boolean","c":"xyz"} ->
 * {"a":"abstraction","b":"abstraction"}
 * 
 * Report a typo Hint by avatar Bladimir Moracher Remove c in all cases; add b
 * if a exist; Was this hint helpful?
 * 
 * Sample Input 1:
 * 
 * a:Abstraction b:Boolean c:xyz
 * 
 * Sample Output 1:
 * 
 * a:Abstraction b:Abstraction
 * 
 * @author SMD_ASY
 *
 */
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Map<String, String> map = new HashMap<>();
		while (scanner.hasNextLine()) {
			String s = scanner.nextLine();
			if (s.isEmpty()) {
				break;
			}
			String[] pair = s.split(":");
			map.put(pair[0], pair[1]);
		}
		scanner.close();
		MapUtils.mapShare(map);
		map.forEach((key, value) -> System.out.println(key + ":" + value));
	}

}
