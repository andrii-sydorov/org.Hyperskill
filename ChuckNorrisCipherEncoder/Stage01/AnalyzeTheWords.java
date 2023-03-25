package ChuckNorrisCipherEncoder.Stage01;

import java.util.Scanner;

/**
 * Stage 1/5: Analyze the words
 * Description
 * To begin with, you will learn how to divide an encrypted message into the
 * characters that it contains. You will need this for further work.
 * 
 * Objectives
 * In this stage, your program should:
 * 
 * Read a string from a console. The input contains a single line.
 * Output all characters in the string, separated by one space (including the
 * space characters themselves).
 * Examples
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Example 1:
 * 
 * Input string:
 * > Greetings!
 * 
 * G r e e t i n g s !
 * Example 2:
 * 
 * Input string:
 * > Who am I?
 * 
 * W h o a m I ?
 * 
 * @author SMD_ASY
 *
 */

public class AnalyzeTheWords {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("Input strings:");
        String words = sc.nextLine();
        sc.close();
        System.out.println();
        System.out.println(String.join(" ", words.split("")));
    }

}
