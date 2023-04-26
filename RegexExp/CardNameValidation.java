package RegexExp;

import java.util.Scanner;

/**
 * Card name validation and identification
 * Write a program that uses regular expressions to check whether the input card
 * number is valid and identifies the card network name.
 * 
 * The input must consist only of numbers. Your program must take a string as an
 * input and print the name of the card network as output, choosing from the set
 * of names specified below, or the message: "Card number does not exist”.
 * 
 * Card networks: Visa, Mastercard, American Express.
 * 
 * 1. A Visa card starts with 4 and has the length of 16 digits.
 * 2. A MasterCard starts with the numbers from 51 to 55, or the numbers 2221 to
 *    2720. All have 16 digits.
 * 3. American Express card numbers start with 34 or 37 and have 15 digits.
 * 
 * Sample Input 1:
 * 4235 2345 6543 1234
 * Sample Output 1:
 * Visa
 * 
 * @author SMD_ASY
 *
 */

public class CardNameValidation {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scn = new Scanner(System.in);
        String numbers = scn.nextLine();
        String card = numbers.replaceAll("\\s", "");
        String visaRegex = "4\\d{15}";
        String masterCardRegex = "(5[1-5]\\d{14})|(222[1-9]\\d{12})|(22[3-9][0-9]\\d{12})|(2[3-6]\\d{14})|(27[0-1]\\d{13})|(2720\\\\d{12})";
        String americanExpressRegex = "3(4|7)\\d{13}";

        if (card.matches(visaRegex)) {
            System.out.println("Visa");
        } else if (card.matches(masterCardRegex)) {
            System.out.println("MasterCard");
        } else if (card.matches(americanExpressRegex)) {
            System.out.println("AmericanExpress");
        } else {
            System.out.println("Card number does not exist");
        }
        scn.close();
    }

}
