package SetInterface;

import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.Scanner;

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
		System.out.printf("The size of text is %d \n", text.size());
		for (String s : dictionary) {
			text.removeAll(Set.of(s.toUpperCase()));
			text.removeAll(Set.of(s.toLowerCase()));
		}
		text.forEach(x -> System.out.println(x));
		System.out.printf("The size of text is %d \n", text.size());
	}

}
