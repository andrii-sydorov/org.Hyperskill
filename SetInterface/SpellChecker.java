package SetInterface;

import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.Scanner;
import java.util.Iterator;

/**
 * The simplest spell checker is the one based on a list of known words. Every
 * word in the text is being searched for in this list and, if such word was not
 * found, it is marked as erroneous.
 * 
 * Write such a spell checker.
 * 
 * The first line of the input contains dd – number of records in the list of
 * known words. Next go dd lines containing one known word per line, next — the
 * number ll of lines of the text, after which — ll lines of the text.
 * 
 * Write a program that outputs those words from the text that are not found in
 * the dictionary (i.e. erroneous). Your spell checker should be case
 * insensitive. The words are entered in an arbitrary order. Words, which are
 * not found in the dictionary, should not be duplicated in the output.
 * 
 * Report a typo Sample Input 1:
 * 
 * 3 
 * a 
 * bb 
 * cCc 
 * 2 
 * a bb aab aba ccc 
 * c bb aaa Sample 
 * 
 * Output 1:
 * 
 * c 
 * aab 
 * aaa 
 * aba
 * 
 * @author SMD_ASY
 *
 */

public class SpellChecker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int numOfRecords = Integer.valueOf(sc.nextLine());
		Set<String> dictionary = new HashSet<>();
		int index = 0;
		while (index != numOfRecords) {
			dictionary.add(sc.nextLine());
			index++;
		}
		int numOfLinesInText = Integer.valueOf(sc.nextLine());
		Set<String> text = new HashSet<>();
		index = 0;
		while (index != numOfLinesInText) {
			Collections.addAll(text, sc.nextLine().split("\\s+"));
			index++;
		}
		sc.close();
		// System.out.printf("The size of text is %d \n", text.size());
		for (String s : dictionary) {
			Iterator<String> i = text.iterator();
			while (i.hasNext()) {
				if (i.next().toLowerCase().equals(s.toLowerCase())) {
					i.remove();
				}
			}
		}
		// System.out.printf("The size of text is %d \n", text.size());
		text.forEach(x -> System.out.println(x));
	}

}
