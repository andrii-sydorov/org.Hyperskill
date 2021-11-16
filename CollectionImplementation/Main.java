package CollectionImplementation;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Write a program that finds out the closest integer to a given one from an
 * array. If you find several integers with the same distance to the N, you
 * should output all of them in the ascending order. If there are several equal
 * numbers, output them all.
 * 
 * Input: a set of integers and a number N.
 * 
 * Output: some number(s) from the given array. Report a typo
 * 
 * Sample Input 1:
 * 
 * 1 2 4 5 3
 * 
 * Sample Output 1:
 * 
 * 2 4
 * 
 * Sample Input 2:
 * 
 * 1 2 3 4 6
 * 
 * Sample Output 2:
 * 
 * 4
 * 
 * Sample Input 3:
 * 
 * 5 1 3 3 1 5 4
 * 
 * Sample Output 3:
 * 
 * 3 3 5 5
 */

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your's numbers: ");
        String[] str = sc.nextLine().split("\\s+");
        List<Integer> ls = new ArrayList<>();
        for (String s : str) {
            ls.add(Integer.valueOf(s));
        }
        System.out.println("Enter the searched numbers: ");
        int n = Integer.valueOf(sc.nextLine());
        sc.close();
        System.out.println(nearestNumbers(ls, n).toString().replace("[", "").replaceAll(",", "").replace("]", ""));
    }

    private static List<Integer> nearestNumbers(List<Integer> ls, int n) {
        List<Integer> ans = new ArrayList<>();
        if (ls.indexOf(n) != -1) {
            for (Integer i : ls) {
                if (n == i) {
                    ans.add(i);
                }
            }
            return ans;
        }
        ls.add(n);
        Collections.sort(ls);
        int index = ls.indexOf(n);
        int difference = 0;
        if (index == 0) {
            difference = ls.get(index + 1) - ls.get(index);
        } else if (index == ls.size() - 1) {
            difference = ls.get(index) - ls.get(index - 1);
        } else {
            difference = Math.min(ls.get(index) - ls.get(index - 1), ls.get(index + 1) - ls.get(index));
        }
        for (int i : ls) {
            if (Math.abs(ls.get(index) - i) == difference) {
                ans.add(i);
            }
        }
        return ans;
    }

}
