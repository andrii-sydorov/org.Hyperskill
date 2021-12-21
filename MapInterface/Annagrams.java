package MapInterface;

import java.util.Scanner;
import java.util.Objects;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.SortedMap;

/**
 * In this problem, you are Sherlock Holmes and you want to quickly detect all
 * anagrams.
 * 
 * Write a program that finds out whether two words are anagrams or not. If one
 * word is an anagram of another, print "yes", else print "no".
 * 
 * Note: anagrams are words that contain the same characters with the same
 * frequencies. In other words, anagrams are created by changing the order of
 * letters in the word.
 * 
 * For example:
 * 
 * "ppaaagg" (2 ps, 3 as, 2 gs) and "gagaapp" (2 ps, 3 as, 2 gs) are anagrams;
 * "hello" (1 h, 1 e, 2 ls, 1 o) and "helllo" (1 h, 1 e, 3 ls, 1 o) are not
 * anagrams.
 * 
 * Remember: anagrams are case-insensitive. Report a typo
 * 
 * Sample Input 1:
 * 
 * ppaaagg 
 * gagaapp
 * 
 * Sample Output 1:
 * 
 * yes
 * 
 * @author ASY
 *
 */

public class Annagrams {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		SortedMap<String, Integer> first = Arrays.stream(sc.nextLine().split(""))
				.collect(Collectors.toMap(letter -> letter.toLowerCase(), letter -> 1, Integer::sum, TreeMap::new));
		SortedMap<String, Integer> second = Arrays.stream(sc.nextLine().split(""))
				.collect(Collectors.toMap(letter -> letter.toLowerCase(), letter -> 1, Integer::sum, TreeMap::new));
		System.out.println(Objects.equals(first, second) ? "yes" : "no");
		sc.close();
	}

}
