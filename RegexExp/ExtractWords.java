package RegexExp;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extracting words from a text
 * 
 * Write a program extracting all words that include the substring "program"
 * from a given text. The text can be large enough. You should not count
 * whitespaces, punctuation marks and other special characters as parts of
 * extracted words.
 * 
 * Try to use Matcher and Pattern to solve it. All the needed modules are
 * already imported. Your pattern should be case insensitive.
 * 
 * Input data format
 * 
 * A single string containing a text about programs, programmers, or
 * programming.
 * 
 * Output data format
 * 
 * Multiple lines where each line contains two parts separated by one
 * whitespace: the starting index of the word and the word itself.
 * 
 * Sample Input 1:
 * All Java programmers program good programs.
 * 
 * Sample Output 1:
 * 9 programmers
 * 21 program
 * 34 programs
 * 
 * @author SMD_ASY
 *
 */

public class ExtractWords {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        Pattern p = Pattern.compile("program[a-zA-Z]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        while (m.find()) {
            System.out.printf("%d %s\n", m.start(), m.group());
        }
        sc.close();
    }

}
