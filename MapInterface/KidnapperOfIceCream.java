package MapInterface;

import java.util.Scanner;
import java.util.TreeMap;
import java.util.SortedMap;
import java.util.Arrays;

/**
 * Imagine now you are an outlaw in the Sherlock Holmes times. You are in need
 * of money and you want to demand some by blackmailing. Obviously, you have to
 * write a letter completely anonymously because you do not want to be busted.
 * 
 * So you decide to compose a letter by cutting the required words from a
 * newspaper.
 * 
 * In this problem, you get two lines: 1) available words you have in a
 * newspaper, 2) your note.
 * 
 * Find out and print if you can write a note from available words ("You get
 * money") or you will be busted ("You are busted").
 * 
 * Remember: your note is case-sensitive Report a typo
 * 
 * Sample Input 1:
 * 
 * batman batman batman i i I need am and and money 
 * I am batman and i need money
 * 
 * Sample Output 1:
 * 
 * You get money
 * 
 * @author ASY
 *
 */

public class KidnapperOfIceCream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		SortedMap<String, Integer> wordsFromNewspaper = new TreeMap<>();
		Arrays.stream(sc.nextLine().split("\\s+")).forEach(key -> wordsFromNewspaper.merge(key, 1, Integer::sum));
		SortedMap<String, Integer> message = new TreeMap<>();
		Arrays.stream(sc.nextLine().split("\\s+")).forEach(key -> message.merge(key, 1, Integer::sum));
		System.out.println(getResult(wordsFromNewspaper, message));
		sc.close();
	}

	public static String getResult(SortedMap<String, Integer> words, SortedMap<String, Integer> message) {
		if (words.keySet().size() < message.keySet().size()) {
			return "You are busted";
		}
		for (String s : message.keySet()) {
			if (!words.containsKey(s) || words.get(s) < message.get(s)) {
				return "You are busted";
			}
		}
		return "You get money";
	}
}
