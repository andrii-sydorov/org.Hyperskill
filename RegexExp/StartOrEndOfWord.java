package RegexExp;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Start or end of a word
 * The first line of the input contains a sequence of letters.
 * 
 * The second line of the input contains some text.
 * 
 * Your task is to determine if any of the words of this text start or end with
 * the sequence specified in the first line of the input. If there is, you
 * should output "YES", otherwise output "NO". A word can only contain symbols
 * of the English alphabet. You should ignore the case while searching for
 * matches.
 * 
 * Sample Input 1:
 * ing
 * Java is the most popular programming language
 * Sample Output 1:
 * YES
 * 
 * Sample Input 2:
 * press
 * Regular expressions is hard to read, isn't it?
 * Sample Output 2:
 * NO
 * 
 * Sample Input 3:
 * ho
 * Wow! How awesome is that!
 * Sample Output 3:
 * YES
 * 
 * Sample Input 4:
 * ONE
 * ponep,onep!
 * Sample Output 4:
 * YES
 * 
 * @author SMD_ASY
 *
 */

public class StartOrEndOfWord {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        String part = scanner.nextLine();
        String line = scanner.nextLine();
        Pattern p1 = Pattern.compile("\\b" + part, Pattern.CASE_INSENSITIVE);
        Pattern p2 = Pattern.compile(part + "\\b", Pattern.CASE_INSENSITIVE);
        Matcher m1 = p1.matcher(line);
        Matcher m2 = p2.matcher(line);
        System.out.println(m1.find() || m2.find() ? "YES" : "NO");
        scanner.close();
    }

}
