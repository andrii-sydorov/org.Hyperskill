package SimpleSearchEngine.Stage01;

import java.util.Scanner;

/**
 * String theory 
 * 
 * Description
 * 
 * Let's implement the simplest search engine possible. It should search for a
 * specific word from the line containing different words.
 * 
 * The first line contains several words separated by a space. The words are
 * numbered according to their order, with the first word having index 1.
 * Consider that all the words in the first line are unique, so there can be no
 * repetitions.
 * 
 * Write a small program that reads a single line and then another line. The
 * program must search in the first line for a word specified in the second
 * line. The program should output the index of the specified word. If there is
 * no such word in the first line, the program should print Not found. Please
 * remember that indexes start from 1!
 * 
 * You should output exactly one line. Examples
 * 
 * The lines that start with > represent the user input. Note that these symbols
 * are not part of the input.
 * 
 * Example 1:
 * 
 * > first second third fourth 
 * > third 
 * 3
 * 
 * Example 2:
 * 
 * > cat dog and mouse 
 * > elephant 
 * Not found
 * 
 * @author SMD_ASY
 *
 */

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String[] data = sc.nextLine().split("\\s+");
		String toFind = sc.nextLine();
		for (int i = 0; i < data.length; i++) {
			if (data[i].equals(toFind)) {
				System.out.println(i + 1);
				System.exit(0);
			}
		}
		sc.close();
		System.out.println("Not found");
	}

}
