package RegexExp;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Find a word with a given length
 * For a given number N and a line with text, output "YES" if this line contains
 * a word with exactly N symbols, otherwise output "NO".
 * 
 * A word can contain only symbols of the English alphabet.
 * 
 * 
 * Sample Input 1:
 * 
 * 3
 * Java is the most popular programming language
 * Sample Output 1:
 * 
 * YES
 * Sample Input 2:
 * 
 * 11
 * Regular expression is hard to read, isnt it?
 * Sample Output 2:
 * 
 * NO
 * Sample Input 3:
 * 
 * 4
 * Wow! How awesome is that!
 * Sample Output 3:
 * 
 * YES
 * 
 * @author SMD_ASY
 *
 */

public class FindWordWithGivenLength {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        String line = scanner.nextLine();
        Pattern p = Pattern.compile(String.format("\\b[a-zA-Z]{%d}\\b", size));
        Matcher m = p.matcher(line);
        System.out.println(m.find() ? "YES" : "NO");
        scanner.close();
    }

}
