package projects.Easy.ChuckNorrisCipherEncoder.Stage02;

import java.util.Scanner;

/**
 * Stage 2/5: The binary world
 * Description
 * In this stage, you'll learn how to convert each character of a string into a
 * binary form. Any ASCII character has a size of 7 bits; in binary, the form is
 * 0 and 1. For example, the character A has a decimal value of 65. The binary
 * representation is 1000001. b is 98 in decimal or 1100010 in binary. The space
 * character is 32 in decimal value or 0100000 in binary.
 * 
 * In this stage, you will learn to represent characters in binary form.
 * 
 * Objectives
 * In this stage, your program should:
 * 
 * Read a string from a console. The input contains a single line.
 * Print The result: line, followed by each character of input on a separate
 * line, formatted as <char> = <binary value>.
 * Note that the binary representation must be 7-bit, even if the first digits
 * are zeros. The Integer.toBinaryString() and String.format() methods can help
 * you with that.
 * 
 * Examples
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Example 1:
 * 
 * Input string:
 * > One
 * 
 * The result:
 * O = 1001111
 * n = 1101110
 * e = 1100101
 * Example 2:
 * 
 * Input string:
 * > 123
 * 
 * The result:
 * 1 = 0110001
 * 2 = 0110010
 * 3 = 0110011
 * Example 3:
 * 
 * Input string:
 * > Who am I?
 * 
 * The result:
 * W = 1010111
 * h = 1101000
 * o = 1101111
 * = 0100000
 * a = 1100001
 * m = 1101101
 * = 0100000
 * I = 1001001
 * 
 * @author SMD_ASY
 *
 */

public class BinaryWorld {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("Input:");
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        sc.close();
        System.out.println();
        String[] tt = new String[word.length()];
        for (int i = 0; i < word.length(); i++) {
            int n = word.codePointAt(i);
            //tt[i] = Integer.toBinaryString(n);
            tt[i] = makeBinary(n);
        }
        System.out.println("The result:");
        for (int i = 0; i < tt.length; i++) {
            System.out.printf("%c = %s\n", word.charAt(i), tt[i]);
        }
    }

    public static String[] makeBinaryRepresentation(char[] arr) {
        String[] res = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int toConvert = Character.getNumericValue(arr[i]);
            res[i] = makeBinary(toConvert);
        }
        return res;
    }

    public static String makeBinary(int toConvert) {
        StringBuilder sb = new StringBuilder();
        int base = 2;
        while (toConvert > 0) {
            if (toConvert < base) {
                sb.append(String.valueOf(toConvert));
                break;
            }
            int reminder = toConvert % base;
            sb.append(String.valueOf(reminder));
            toConvert /= base;
        }
        sb.reverse();
        if (sb.length() < 7) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }

}
