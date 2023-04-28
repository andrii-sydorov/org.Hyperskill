package RegexExp;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Remove extra spaces
 * Write a program that reads a text and removes all extra spaces. The program
 * must replace all repeating spaces and tabulations between words with a single
 * space character (' ').
 * 
 * Use the provided template. Please, use regular expressions to solve the task.
 * 
 * Sample Input 1:
 * Just   a     text
 * Sample Output 1:
 * Just a text
 * 
 * 
 * Sample Input 2:
 * The Java language was initially called Oak after an oak tree that stood
 * outside Gosling's office. Later the project went by the name Green and was
 * finally renamed Java, from Java coffee.
 * Sample Output 2:
 * The Java language was initially called Oak after an oak tree that stood
 * outside Gosling's office. Later the project went by the name Green and was
 * finally renamed Java, from Java coffee.
 * 
 * @author SMD_ASY
 *
 */

public class RemoveExtraSpaces {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String regex = "\\s+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        System.out.println(m.replaceAll(" "));
        scanner.close();
    }

}
