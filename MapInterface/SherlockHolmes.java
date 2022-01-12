package MapInterface;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Wow! This problem is kind of tricky. If you're ready to put your thinking cap
 * on, brace yourself and good luck! Otherwise, you can skip it for now and
 * return any time later
 * 
 * For this problem, imagine that you are Sherlock Holmes. You've deduced that
 * the clues are somehow hidden within the pairs of words that contain only the
 * same letters with the same frequencies. To crack the case, you now need to
 * find out how many characters must be deleted to get such words (character
 * sequences) from the given ones.
 * 
 * For example: for two words "case" and "seal" you'll need to remove characters
 * "c" and "l" respectively to get "ase" and "sea". In this case, the answer is
 * 2 ("c" and "l").
 * 
 * Remember: these "words" are case-insensitive Report a typo
 * 
 * Sample Input 1:
 * 
 * case 
 * seal
 * 
 * Sample Output 1:
 * 
 * 2
 * 
 * Sample Input 2:
 * 
 * Poooohgf 
 * poohgf
 * 
 * Sample Output 2:
 * 
 * 2
 * 
 * Sample Input 3:
 * 
 * write 
 * write
 * 
 * Sample Output 3:
 * 
 * 0
 * 
 * @author SMD_ASY
 *
 */

public class SherlockHolmes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your word's: ");
//		String[] data = Arrays.asList(sc.nextLine().split("\\s+")).stream().map(String::toLowerCase)
//				.toArray(String[]::new);
		String[] data = new String[2];
		data[0] = sc.nextLine().toLowerCase();
		data[1] = sc.nextLine().toLowerCase();
		System.out.println(toDelete(data));
		System.out.println(toDelete2(data));
		sc.close();
	}

	public static int toDelete(String[] data) {
		Map<String, Integer>[] arraysOfMaps = new HashMap[data.length];
		for (int i = 0; i < data.length; i++) {
			arraysOfMaps[i] = new HashMap<>();
			for (int j = 0; j < data[i].length(); j++) {
				String st = Character.toString(data[i].charAt(j));
				if (arraysOfMaps[i].containsKey(st)) {
					arraysOfMaps[i].put(st, arraysOfMaps[i].get(st) + 1);
				} else {
					arraysOfMaps[i].put(st, 1);
				}
			}
		}
		Map<String, Integer> mapFirst = arraysOfMaps[0];
		Map<String, Integer> mapSecond = arraysOfMaps[1];
		int count = 0;
		for (Map.Entry<String, Integer> entrFirst : mapFirst.entrySet()) {
			if (mapSecond.containsKey(entrFirst.getKey())) {
				count += Math.abs(entrFirst.getValue() - mapSecond.get(entrFirst.getKey()));
				mapSecond.remove(entrFirst.getKey());
			} else {
				count += entrFirst.getValue();
			}
		}
		for (Map.Entry<String, Integer> entrSecond : mapSecond.entrySet()) {
			count += entrSecond.getValue();
		}
		return count;
	}

	public static int toDelete2(String[] data) {
		Map<String, Integer> map = getMap(data[0]);
		for (int i = 1; i < data.length; i++) {
			for (int j = 0; j < data[i].length(); j++) {
				String str = Character.toString(data[i].charAt(j));
				map.put(str, map.getOrDefault(str, 0) - 1);
			}
		}
		int count = 0;
		for (Map.Entry<String, Integer> entr : map.entrySet()) {
			count += Math.abs(entr.getValue());
		}
		return count;
	}

	public static Map<String, Integer> getMap(String s) {
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			String str = Character.toString(s.charAt(i));
			map.put(str, map.getOrDefault(str, 0) + 1);

		}
		return map;
	}
}
