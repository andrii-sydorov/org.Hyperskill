package RegexExp;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * In the middle of a word
 * The first line of the input contains a sequence of letters.
 * 
 * The second line of the input contains some text.
 * 
 * Your task is to determine whether any of the words from the second line
 * contain this sequence of letters. Count only the words that do not start or
 * end with your search term. If such a word is present in the line, output
 * "YES", otherwise output "NO". The word can contain only the letters of the
 * English alphabet. Ignore the case while searching for matches.
 * 
 * Sample Input 1:
 * 
 * Gramm
 * Java is the most popular programming language
 * Sample Output 1:
 * YES
 * 
 * Sample Input 2:
 * Press
 * Regular expressions is hard to read, isnt it?
 * Sample Output 2:
 * YES
 * 
 * Sample Input 3:
 * some
 * Wow! How awesome is that!
 * Sample Output 3:
 * NO
 * 
 * @author SMD_ASY
 *
 */

public class MiddleOfWord {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        String part = scanner.nextLine();
        String line = scanner.nextLine();
        Pattern p = Pattern.compile("\\w+" + part + "\\w+", Pattern.CASE_INSENSITIVE);
        // Pattern pattern = Pattern.compile("\\B" + part + "\\B",
        // Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(line);
        System.out.println(m.find() ? "YES" : "NO");
        scanner.close();
    }

}
