package MapInterface;

import java.util.Map;
import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;

/**
 * Write a program that reads key-value pairs and outputs pairs whose keys
 * belong to the specified range (inclusive) in the ascending order by the key.
 * 
 * Input data format
 * 
 * The first line contains two values that represent range borders. The second
 * line contains the number of key-value pairs. Next lines contain pair (an
 * integer key and a string value separated by a space).
 * 
 * Output data format
 * 
 * All pairs whose keys belong to the range, each pair in a new line. The key
 * and the value of a pair must be separated by a space. Report a typo
 * 
 * Sample Input 1:
 * 
 * 2 4 
 * 5 
 * 1 aa 
 * 5 ee 
 * 2 bb 
 * 4 dd 
 * 3 cc
 * 
 * Sample Output 1:
 * 
 * 2 bb 
 * 3 cc 
 * 4 dd
 * 
 * @author ASY
 *
 */

public class GetSubmaps {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int[] range = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(range);
		int numberOfLines = Integer.valueOf(sc.nextLine());
		int i = 0;
		Map<Integer, String> map = new HashMap<>();
		while (i < numberOfLines) {
			String[] s = sc.nextLine().split("\\s+");
			int key = Integer.valueOf(s[0]);
			String value = s[1];
			if (key <= range[1] && key >= range[0]) {
				map.put(key, value);
			}
			i++;
		}
		sc.close();
		map.forEach((key, value) -> System.out.println(key + " " + value));
	}

}
