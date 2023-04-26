package RegexExp;

import java.util.Scanner;

/**
 * Check the clock time
 * For a given string you should check whether it shows time in the correct
 * format. The string should consist of two integers separated by the colon. The
 * first integer should be from 00 to 23 and the second integer should be from
 * 00 to 59.
 * 
 * Note that if only one digit in the integer, it should be padded with a
 * leading zero. That is, strings "5:00" and "05:1" don't show time correctly,
 * but "05:00" and "05:01" do.
 * 
 * Output "YES" if the given string shows time in the correct format, otherwise
 * output "NO".
 * 
 * Sample Input 1:
 * 09:00
 * Sample Output 1:
 * YES
 * 
 * Sample Input 2:
 * 23:59
 * Sample Output 2:
 * YES
 * 
 * Sample Input 3:
 * 24:00
 * Sample Output 3:
 * NO
 * 
 * @author SMD_ASY
 *
 */

public class CheckTheTime {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        String hours = "([01]\\d|2[0-3])";
        String minutes = "[0-5]\\d";
        String delimiter = ":";
        String regex = hours + delimiter + minutes;

        String time = scanner.nextLine();
        System.out.println(time.matches(regex) ? "YES" : "NO");
        scanner.close();
    }

}
