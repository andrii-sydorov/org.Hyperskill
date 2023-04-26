package RegexExp;

import java.util.Scanner;

/**
 * Vehicle registration number
 * Write a program that uses regular expressions to check whether the input
 * string is a valid vehicle registration number.
 * 
 * A vehicle registration number has the following format: ?***??, where ? – a
 * Latin symbol from the set [A, B, E, K, M, H, O, P, C, T, Y, X], and * – any
 * digit from 0 to 9.
 * 
 * The program should output true or false.
 * 
 * Sample Input 1:
 * X777XX
 * Sample Output 1:
 * 
 * true
 * 
 * @author SMD_ASY
 *
 */

public class VehicleRegistration {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        String regNum = scanner.nextLine(); // a valid or invalid registration number
        String regex = "[ABEKMHOPCTYX]\\d{3}[ABEKMHOPCTYX]{2}";
        System.out.println(regNum.matches(regex));
        scanner.close();
    }

}
