package ChuckNorrisCipherEncoder.Stage04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Stage 4/5: 
 * 
 * Try to understand it
 * 
 * Description
 * 
 * In this stage, you will write a decoder for a cipher. You need to transform
 * the encrypted message into its original format.
 * 
 * Objectives
 * 
 * Your program receives a string of zeros and spaces and converts it to
 * readable text. You must parse the string to the blocks of zeroes and decode
 * the message the same way as in previous stages but in reversed order.
 * 
 * For example, your program receives 0 0 00 0000 0 000 00 0000 0 00. You can
 * split blocks of zeros and group those blocks by two. Then you need to decode
 * these blocks like in the previous stage:
 * 
 * - 0 0 is 1
 * - 00 0000 is 0000
 * - 0 000 is 111
 * - 00 0000 is 0000
 * - 0 00 is 11
 * 
 * Concatenation of the lines above gives us 10000111000011.
 * 
 * After that, you need to split the result into blocks of seven symbols (binary
 * form) and convert these blocks to characters. In this case, splitting
 * 10000111000011 by seven symbols gives us two characters — 1000011 1000011 ,
 * convert them into characters and the result will be CC (C is 1000011).
 * 
 * In this stage, your program should:
 * 
 * 1. Read a string from a console. The input contains a single line of spaces and
 * 0 characters.
 * 2. Print The result: line, followed by a line with a decoded message.
 * The Integer.parseInt() method might be useful at this stage.
 * 
 * Examples
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Example 1:
 * 
 * Input encoded string:
 * > 0 0 00 0000 0 000 00 0000 0 00
 * 
 * The result:
 * CC
 * 
 * Example 2:
 * 
 * Input encoded string:
 * > 0 0 00 00 0 0 00 000 0 00 00 0 0 0 00 00 0 0 00 0 0 0 00 000000 0 0000 00
 * 000 0 00 00 00 0 00
 * 
 * The result:
 * Hi <3
 * 
 * @author SMD_ASY
 *
 */

public class DecodeStrings {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input encoded string:");
        String words = sc.nextLine();
        ChuckNorrisCipherEncoder.encodeMessages(words);
        System.out.println("\nThe result:");
        sc.close();
        System.out.println(ChuckNorrisCipherEncoder.getOutputMessage());
        
       // parts of the solution from hyperskill.org with regex 
        String[] binaryWords = unaryToBinary(words).split("(?<=\\G.{7})");
        for (String binaryWord : binaryWords) {
            System.out.print((char) Integer.parseInt(binaryWord, 2));
        }
    }
    
    private static String unaryToBinary(String unarySequence) {
        String[] unarySequenceParts = unarySequence.split(" ");
        String binarySequence = "";

        for (int i = 0; i < unarySequenceParts.length; i += 2) {
            binarySequence += (unarySequenceParts[i].equals("00") ? "0" : "1").repeat(unarySequenceParts[i + 1].length());
        }

        return binarySequence;
    }

    static class ChuckNorrisCipherEncoder {

        private static String inputMessage;
        private static String outputMessage;

        public static String getInputMessage() {
            return inputMessage;
        }

        public static void setInputMessage(String inputMessage) {
            ChuckNorrisCipherEncoder.inputMessage = inputMessage;
        }

        public static String getOutputMessage() {
            return outputMessage;
        }

        public static void setOutputMessage(String outputMessage) {
            ChuckNorrisCipherEncoder.outputMessage = outputMessage;
        }

        // decode messages

        public static void decodeMessages(String input) {
            StringBuilder encoded = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                encoded.append(makeBinary(input.charAt(i)));
            }
            List<String> ls = makeList(encoded.toString());
            String res = makeDecodeWithZeros(ls);
            outputMessage = res;
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

        // encode messages

        private static void encodeMessages(String inputMessage) {
            StringBuilder binary = new StringBuilder();

            StringBuilder letters = new StringBuilder();
            String[] arrToEncode = inputMessage.split(" ");

            for (int i = 0; i < arrToEncode.length; i += 2) {
                if (arrToEncode[i].equals("0")) {
                    binary.append(buildString(arrToEncode[i + 1], "1"));
                } else if (arrToEncode[i].equals("00")) {
                    binary.append(buildString(arrToEncode[i + 1], "0"));
                }
            }

            int[] numbers = new int[binary.length() / 7];

            int index = 0;
            for (int i = 0; i < binary.length();) {
                String s = binary.substring(i, i + 7);
                i += 7;
                numbers[index] = Integer.parseInt(s, 2);
                index++;
            }

            for (int i : numbers) {
                letters.append((char) i);
            }

            outputMessage = letters.toString();
        }

        private static String buildString(String toEncode, String toFill) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < toEncode.length(); i++) {
                sb.append(toFill);
            }
            return sb.toString();
        }

    }

}
