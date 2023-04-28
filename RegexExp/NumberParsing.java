package RegexExp;

import java.util.Scanner;

/**
 * Number parsing
 * You need to write a regular expression that can match any number.
 * 
 * A number can be positive or negative. If it is negative, then it must start
 * with a '-'. If it is positive, it may start with a '+'. If it doesn't start
 * with either '+' or '-' it is considered positive.
 * 
 * A number representation can't be padded with leading zeroes. For example, 001
 * is not a valid number (but 0 is).
 * 
 * A number can have a fractional part. A delimiter between an integer part and
 * a fractional part can be either a dot . or a comma ,. A fractional part can't
 * be padded with trailing zeros: for example, 1.01 and 1.0 are valid numbers,
 * but 1.00 and 1.10 are not.
 * 
 * Output "YES" if a given string is a valid number, otherwise output "NO".
 * 
 * 
 * Sample Input:
 * 123
 * Sample Output:
 * YES
 * 
 * Sample Input:
 * 0123
 * Sample Output:
 * NO
 * 
 * Sample Input:
 * -56.0
 * Sample Output:
 * YES
 * 
 * Sample Input:
 * -0,05
 * Sample Output:
 * YES
 * 
 * Sample Input:
 * -23.230
 * Sample Output:
 * NO
 * 
 * 
 * Sample Input:
 * +12376352189432535347623423443417,36817231873134176345178234234234234
 * Sample Output:
 * YES
 * 
 * @author SMD_ASY
 *
 */

public class NumberParsing {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        String regex = "[+-]?(0|[1-9]+\\d*)([.,](0$|\\d*[^0]$))?";
        String number = scanner.nextLine();
        System.out.println(number.matches(regex) ? "YES" : "NO");
        scanner.close();
    }

}
