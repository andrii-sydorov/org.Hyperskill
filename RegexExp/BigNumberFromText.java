package RegexExp;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extracting big numbers from a text
 * 
 * Write a program that extracts and outputs all big numbers from a string. A
 * big number contains at least 10 characters.
 * 
 * Output all the big numbers you found. Each number should start with a new
 * line and be followed by its length (the number of digits). Insert the colon :
 * character to separate numbers and their lengths.
 * 
 * Try to use Matcher and Pattern to solve it. All the needed modules are
 * already imported.
 * 
 * Sample Input 1:
 * If X is 609348676234, Y is 3077, the sum is 609348679311.
 * 
 * Sample Output 1:
 * 609348676234:12
 * 609348679311:12
 * 
 * @author SMD_ASY
 *
 */

public class BigNumberFromText {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        String stringWithNumbers = sc.nextLine();
        Pattern p = Pattern.compile("\\d{10,}");
        Matcher m = p.matcher(stringWithNumbers);
        while (m.find()) {
            String s = m.group();
            System.out.printf("%s:%d\n", s, s.length());
        }
        sc.close();
    }

}
