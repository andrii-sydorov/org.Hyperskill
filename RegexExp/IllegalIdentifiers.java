package RegexExp;

import java.util.Scanner;

/**
 * Illegal identifiers
 * Suppose, you want to create a new programming language that supports
 * variables.
 * 
 * There is a set of rules for identifiers (names) of variables:
 * 
 * - It can include lower and upper letters, digits and the underscore character _;
 * - It can only start with a letter or an underscore;
 * - If an identifier starts with an underscore, the second character should be
 *   either a letter or a digit, but not an underscore;
 * 
 * Note that a single _ is not a valid identifier.
 * 
 * Using the provided template, write a program that reads n identifiers and
 * then outputs all invalid ones in the same order as they appear in the input
 * data. We hope that you will use regexes to solve the problem.
 * 
 * Sample Input 1:
 * 
 * 6
 * ident
 * i
 * __
 * 1a
 * b33
 * _n1
 * Sample Output 1:
 * 
 * __
 * 1a
 * 
 * @author SMD_ASY
 *
 */

public class IllegalIdentifiers {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);

        int numberOfVariables = Integer.parseInt(scanner.nextLine());

        String regex = "(_[a-zA-Z0-9].*)|([a-zA-Z]\\w*)";

        for (int i = 0; i < numberOfVariables; i++) {
            String identifier = scanner.nextLine();
            if (!identifier.matches(regex)) {
                System.out.println(identifier);
            }
        }
        scanner.close();
    }

}
