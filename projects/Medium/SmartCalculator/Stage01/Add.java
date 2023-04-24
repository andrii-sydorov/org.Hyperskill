package projects.Medium.SmartCalculator.Stage01;

import java.util.Scanner;
import java.util.Arrays;

/**
 * Objective
 * Write a program that reads two integer numbers from the same line and prints
 * their sum in the standard output. Numbers can be positive, negative, or zero.
 * 
 * Example
 * The example below shows the input and the corresponding output. Your program
 * should work in the same way. Do not add extra characters after the output!
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Notice that it's not the part of the input.
 * 
 * > 5 8
 * 13
 * 
 * @author SMD_ASY
 *
 */

public class Add {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::valueOf).toArray();
        // int t = Arrays.stream(arr).sum();
        // System.out.println(t);
        System.out.println(summ(arr));
        sc.close();
    }

    public static int summ(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            ans += arr[i];
        }
        return ans;
    }

}
