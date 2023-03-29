package ChuckNorrisCipherEncoder.Stage05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Stage 5/5: 
 * 
 * Cooler than Chuck Norris
 * 
 * Description
 * 
 * Let's finish our encryption-decryption software by adding a simple user
 * interface. The program asks the user for the desired option
 * (encode/decode/exit), performs it, and all that in a loop until the user
 * wants to finish.
 * 
 * Objectives
 * 
 * In this stage, your program should:
 * 
 * 1. Ask users what they want to do, encode a string, decode a string or quit the
 *    program with
 *    Please input operation (encode/decode/exit):
 * 2. If user inputs encode as the desired operation, the program should print
 *    Input string: to the output, read a line and output two lines — 
 *    Encoded string: followed by the encoded string;
 * 3. If user inputs decode as the desired operation, the program should print
 *    Input encoded string: to the output, read a line and output two lines —
 *    Decoded string: followed by the actual decoded string;
 * 4. If user inputs exit as the desired operation, the program should say Bye! and
 *    finish its execution.
 *    
 * The program should be looped to terminate only if the user inputs exit as an
 * operation. Otherwise, it should continue asking users Please input operation
 * (encode/decode/exit): after each iteration.
 * 
 * Also, let's prevent some incorrect input.
 * 
 * 1. If the user misspells the operation name, the program should print out There
 *    is no '<input>' operation
 * 2. If the user provided an incorrect encoded message as input to decode, the
 *    program should print out appropriate feedback containing not valid substring
 * 
 * List of not valid encoded messages:
 * 
 * - The encoded message includes characters other than 0 or spaces;
 * - The first block of each sequence is not 0 or 00;
 * - The number of blocks is odd;
 * - The length of the decoded binary string is not a multiple of 7.
 * 
 * Examples
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Example 1:
 * 
 * Please input operation (encode/decode/exit):
 * > encode
 * Input string:
 * > Hey!
 * Encoded string:
 * 0 0 00 00 0 0 00 000 0 00 00 00 0 0 00 0 0 00000 00 00 0 0 00 0 0 0 00 0000 0
 * 0
 * 
 * Please input operation (encode/decode/exit):
 * > decode
 * Input encoded string:
 * > 0 0 00 00 0 0 00 000 0 00 00 00 0 0 00 0 0 00000 00 00 0 0 00 0 0 0 00 0000
 * 0 0
 * Decoded string:
 * Hey!
 * 
 * Please input operation (encode/decode/exit):
 * > exit
 * Bye!
 * 
 * Example 2:
 * 
 * Please input operation (encode/decode/exit):
 * > smile
 * There is no 'smile' operation
 * 
 * Please input operation (encode/decode/exit):
 * > decode
 * Input encoded string:
 * > 0 0 00 00 0 0 00 000
 * Decoded string:
 * H
 * 
 * Please input operation (encode/decode/exit):
 * > decode
 * Input encoded string:
 * > 0 0 1 00 0 0 1 000
 * Encoded string is not valid.
 * 
 * Please input operation (encode/decode/exit):
 * > decode
 * Input encoded string:
 * > 000 0 00 00 0000 0 00 000
 * Encoded string is not valid.
 * 
 * Please input operation (encode/decode/exit):
 * > decode
 * Input encoded string:
 * > 0 0 00 00 0 0 00
 * Encoded string is not valid.
 * 
 * Please input operation (encode/decode/exit):
 * > decode
 * Input encoded string:
 * > 0 0 00 00 0 0 00 00
 * Encoded string is not valid.
 * 
 * Please input operation (encode/decode/exit):
 * > exit
 * Bye!
 * 
 * @author SMD_ASY
 *
 */

public class DecodeAndEncode {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean toExit = false;
        while (!toExit) {
            System.out.println("Please input operation (encode/decode/exit):");
            String option = sc.nextLine();
            switch (option) {
                case "encode":
                    System.out.println("Input strings:");
                    String toEncode = sc.nextLine();
                    ChuckNorrisCipherEncoder.encodeMessages(toEncode);
                    System.out.println("Encoded strings");
                    System.out.println(ChuckNorrisCipherEncoder.getOutputMessage());
                    System.out.println();
                    break;
                case "decode":
                    System.out.println("Input encoded string:");
                    String toDecode = sc.nextLine();
                    ChuckNorrisCipherEncoder.decodeMessages(toDecode);
                    if (ChuckNorrisCipherEncoder.getOutputMessage() == null) {
                        System.out.println("Encoded string is not valid.");
                    } else {
                        System.out.println("Decoded string:");
                        System.out.println(ChuckNorrisCipherEncoder.getOutputMessage());
                    }
                    System.out.println();
                    break;
                case "exit":
                    toExit = true;
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println(String.format("There is no '%s' operation \n", option));
                    break;
            }
        }
        sc.close();
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

        // encode messages

        public static void encodeMessages(String input) {
            outputMessage = null;
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

        // decode messages

        private static void decodeMessages(String inputMessage) {
            outputMessage = null;
            String[] arrToEncode = inputMessage.split(" ");

            if (inputMessage.indexOf("1") >= 0 || inputMessage.matches("[A-Za-z]+")) {
                return;
            }
            if (arrToEncode.length % 2 != 0) {
                return;
            }
            int sum = 0;
            for (int i = 1; i < arrToEncode.length; i += 2) {
                sum += arrToEncode[i].length();
            }
            if (sum % 7 != 0) {
                return;
            }

            StringBuilder binary = new StringBuilder();

            StringBuilder letters = new StringBuilder();
            for (int i = 0; i < arrToEncode.length; i += 2) {
                if (arrToEncode[i].equals("0")) {
                    binary.append(buildString(arrToEncode[i + 1], "1"));
                } else if (arrToEncode[i].equals("00")) {
                    binary.append(buildString(arrToEncode[i + 1], "0"));
                } else {
                    return;
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
