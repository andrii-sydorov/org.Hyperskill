package ChuckNorrisCipherEncoder.Stage03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EncryptsWithZero {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("Input string:");
        String word = sc.nextLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            String decoded = makeBinary(word.charAt(i));
            //String decoded = Integer.toBinaryString(word.charAt(i));
            //System.out.printf("%c - %s\n", word.charAt(i), decoded);
            List<String> ls = makeList(decoded);
            //System.out.printf("%c - %s\n", word.charAt(i), ls.toString());
            String zeros = makeDecodeWithZeros(ls);
            //System.out.printf("%c - %s\n", word.charAt(i), zeros);
            sb.append(zeros);
        }
        sc.close();
        //System.exit(0);
        System.out.println();
        System.out.println("The result:");
        System.out.println(sb.toString());
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
