package CollectionImplementation;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Arrays;

/**
 * You're given a sequence of strings separated by spaces. Read the sequence
 * from the standard input and store all strings to the list. Output the list to
 * the standard output using System.out.println(yourList).
 * 
 * The order of elements must be the same as in the input. Report a typo
 * 
 * Sample Input 1:
 * 
 * Google Oracle JetBrains
 * 
 * Sample Output 1:
 * 
 * [Google, Oracle, JetBrains]
 */
public class Company {

    public static void main(String[] args) {
        List<String> ls = List.of("andrii", "fucks", "Olga"); // return immutable list
        System.out.println(ls.getClass().getName());
        Scanner sc = new Scanner(System.in);
        List<String> com = Arrays.stream(sc.nextLine().split("\\s+")).map(String::valueOf).collect(Collectors.toList());
        sc.close();
        System.out.println(com);
    }

}
