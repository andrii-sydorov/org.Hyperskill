package RegexExp;

import java.util.Scanner;

/**
 * Check the date
 * Write a program that uses regular expressions to check whether the input date
 * is valid.
 * The input date can be in any of the two formats: yyyy-mm-dd or dd-mm-yyyy.
 * The year must be 19yy or 20yy. - /. symbols can be used as splitters.
 * 
 * dd from 01 to 31
 * 
 * mm from 01 to 12
 * 
 * 
 * For a given string output Yes if this string is a valid date, otherwise
 * output No.
 * 
 * Sample Input 1:
 * 
 * 21/12/1999
 * Sample Output 1:
 * 
 * Yes
 * 
 * @author SMD_ASY
 *
 */

public class CheckTheDate {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        String date = sc.nextLine();
        String years = "(19|20)\\d{2}";
        String months = "0[1-9]|1[0-2]";
        String days = "0[1-9]|[12][0-9]|3[01]";
        String regex = "((19|20)\\d{2}[-/.](0[1-9]|1[0-2])[-/.](0[1-9]|[12][0-9]|3[01]))|((0[1-9]|[12][0-9]|3[01])[-/.](0[1-9]|1[0-2])[-/.](19|20)\\d{2})";
        System.out.println(date.matches(regex) ? "Yes" : "No");
        sc.close();
    }

}
