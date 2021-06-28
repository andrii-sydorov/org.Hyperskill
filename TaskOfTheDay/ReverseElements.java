package TaskOfTheDay;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * You need to implement reverseElements method. It should reverse all elements
 * in the rows of the twoDimArray as in the example below.
 * 
 * Example:
 * 
 * 0 0 9 9                  9 9 0 0
 * 1 2 3 4    will become:  4 3 2 1
 * 5 6 7 8                  8 7 6 5 
 * It is guaranteed that twoDimArray has at least 1 row.
 * 
 * P.S. You don't need to print anything in this task.
 */

public class ReverseElements {

    public static void main(String[] args) {
        int[][] twoDimArray = new int[][] { { 0, 0, 9, 9 }, { 1, 2, 3, 4 }, { 5, 6, 7, 8 } };
        System.out.println("Before reversing");
        printTwoDimArray(twoDimArray);
        System.out.println("reversing");
        reverseTwoDimArray(twoDimArray);
        System.out.println("rereversing");
        reverseTwoDimArrayCol(twoDimArray);
        // List<Integer> ls = Arrays.stream(twoDimArray[0]).map(Integer :: valueOf).c;
    }

    private static void reverseTwoDimArrayCol(int[][] twoDimArray) {
        List<Integer>[] ls = new ArrayList[twoDimArray.length];
        for (int i = 0; i < ls.length; i++) {
            ls[i] = new ArrayList<>();
            for (int j = 0; j < twoDimArray[i].length; j++) {
                ls[i].add(twoDimArray[i][j]);
            }
            Collections.reverse(ls[i]);
        }

        for (List<Integer> l : ls) {
            System.out.println(
                    Arrays.toString(l.toArray(new Integer[0])).replace("[", "").replace("]", "").replace(",", ""));
        }
    }

    public static void reverseTwoDimArray(int[][] twoDimArray) {
        int[][] temp = new int[twoDimArray.length][twoDimArray[0].length];
        for (int i = 0; i < twoDimArray.length; i++) {
            for (int j = 0; j < twoDimArray[i].length; j++) {
                temp[i][j] = twoDimArray[i][j];
            }

        }

        for (int i = 0; i < twoDimArray.length; i++) {
            for (int j = 0; j < twoDimArray[i].length; j++) {
                twoDimArray[i][j] = temp[i][temp[i].length - 1 - j];
            }
        }

        for (int[] ar : twoDimArray) {
            System.out.println(Arrays.toString(ar).replace("[", "").replace("]", "").replace(",", ""));
        }
    }

    public static void printTwoDimArray(int[][] twoDimArray) {
        for (int[] ar : twoDimArray) {
            System.out.println(Arrays.toString(ar).replace("[", "").replace("]", "").replace(",", ""));
        }
    }

}
