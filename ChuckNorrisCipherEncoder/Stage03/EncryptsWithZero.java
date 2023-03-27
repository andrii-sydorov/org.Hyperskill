package ChuckNorrisCipherEncoder.Stage03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Stage 3/5: Chuck Norris encrypts only with zeros
 * 
 * Description
 * 
 * Binary with 0 and 1 is good, but binary with only 0 is even better! This
 * encoding has a name — the Chuck Norris Unary Code.
 * 
 * Let's convert our text into a sequence of zeros and spaces!
 * 
 * Objectives
 * 
 * The encoding principle is simple. The input message consists of ASCII
 * characters (7-bit). You need to transform the text into the sequence of 0 and
 * 1 and use the Chuck Norris technique. The encoded output message consists of
 * blocks of 0. A block is separated from another block by a space.
 * 
 * Two consecutive blocks are used to produce a series of the same value bits
 * (only 1 or0 values):
 * 
 * - First block: it is always 0 or 00. If it is 0, then the series contains 1, if
 * not, it contains 0
 * - Second block: the number of 0 in this block is the number of bits in the
 * series
 * 
 * Let's take a simple example with a message which consists of only one
 * character C. The C symbol in binary is represented as 1000011, so with Chuck
 * Norris technique this gives:
 * 
 * - 0 0 (the first series consists of only a single 1);
 * - 00 0000 (the second series consists of four 0);
 * - 0 00 (the third consists of two 1)
 * - So C is coded as: 0 0 00 0000 0 00
 * 
 * Make sure, that an encoding of a single character sequence is not separated.
 * For example, 000 should be encoded as 00 000 and not as 00 0 00 0 00 0 or 00
 * 0 00 00 or 00 00 00 0
 * 
 * In this stage, your program should:
 * 
 * 1. Read a string from a console. The input contains a single line.
 * 2. Print The result: line, followed by a line with an encoded message.
 * 
 * Examples
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Example 1:
 * 
 * Input string:
 * > C
 * 
 * The result:
 * 0 0 00 0000 0 00
 * 
 * Example 2:
 * 
 * Input string:
 * > CC
 * 
 * The result:
 * 0 0 00 0000 0 000 00 0000 0 00
 * 
 * Example 3:
 * 
 * Input string:
 * > Hi <3
 * 
 * The result:
 * 0 0 00 00 0 0 00 000 0 00 00 0 0 0 00 00 0 0 00 0 0 0 00 000000 0 0000 00 000
 * 0 00 00 00 0 00
 * 
 * @author SMD_ASY
 *
 */

public class EncryptsWithZero {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("Input string:");
        String word = sc.nextLine();
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            encoded.append(makeBinary(word.charAt(i)));
        }
        sc.close();
        List<String> ls = makeList(encoded.toString());
        String res = makeDecodeWithZeros(ls);
        System.out.println();
        System.out.println("The result:");
        System.out.println(res);
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

    public static List<String> makeList(String s) {
        List<String> ls = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i == 0) {
                sb.append(s.charAt(i));
                continue;
            }
            if (sb.indexOf(Character.toString(s.charAt(i))) >= 0) {
                sb.append(s.charAt(i));
            } else {
                ls.add(sb.toString());
                sb.setLength(0);
                sb.append(s.charAt(i));
            }
        }
        if (sb.length() != 0) {
            ls.add(sb.toString());
        }
        return ls;
    }

    public static String makeDecodeWithZeros(List<String> ls) {
        String[] arr = new String[ls.size()];
        for (int i = 0; i < arr.length; i++) {
            String s = ls.get(i);
            if (s.contains("1")) {
                arr[i] = "0 " + makeZeros(s);
            } else {
                arr[i] = "00 " + makeZeros(s);
            }
        }
        return String.join(" ", arr);
    }

    public static String makeZeros(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append("0");
        }
        return sb.toString();
    }

}
