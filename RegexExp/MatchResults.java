package RegexExp;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Find all passwords
 * 
 * Write a program searching for passwords in a given text. It is known that:
 * 
 * a password consists of digits and/or Latin upper- and lowercase letters in
 * any combination;
 * a password always follows the word "password" (it can be written in upper-
 * and/or lowercase letters) but can be separated from it by any number of
 * whitespaces and colon : characters.
 * Output all passwords found in the text, each password starting with a new
 * line. If the text does not contain any passwords, output "No passwords
 * found." without quotes.
 * 
 * Try to use Matcher and Pattern to solve it. All the needed modules are
 * already imported.
 * 
 * Sample Input 1:
 * My email javacoder@gmail.com with password SECRET115. Here is my old
 * PASSWORD: PASS.
 * Sample Output 1:
 * SECRET115
 * PASS
 * 
 * Sample Input 2:
 * My email is javacoder@gmail.com.
 * Sample Output 2:
 * No passwords found.
 * 
 * @author SMD_ASY
 *
 */

public class MatchResults {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        Pattern p = Pattern.compile("password[ :]+[a-zA-Z0-9]+", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        String s = null;
        while (m.find()) {
            s = m.group().replaceAll("\\w+[ :]+", "");
            System.out.println(s);
        }
        if (s == null) {
            System.out.println("No passwords found.");
        }
        sc.close();
    }

}
