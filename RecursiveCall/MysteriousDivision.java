package RecursiveCall;

/**
 * Given the following recursive method:

public static long method(long n) {
    if (n == 0) {
        return 0;
    }            
    return n % 10 + method(n / 10);
}


Enter the result of the following invocation:

method(29815);
 */

import java.util.Scanner;

public class MysteriousDivision {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your's number:");
        int n = sc.nextInt();
        sc.close();
        long startRecursion = System.currentTimeMillis();
        System.out.println(recursiveDivision(n));
        System.out.println(System.currentTimeMillis() - startRecursion);
        long startLoops = System.currentTimeMillis();
        System.out.println(loopsDivision(n));
        System.out.println(System.currentTimeMillis() - startLoops);
        System.out.println(recursiveDivision(n) == loopsDivision(n));
    }
/*
 * another word find the sum of elements in numbers
 */
    public static int recursiveDivision(int n) {
        if (n == 0) {
            return n;
        }
        return n % 10 + recursiveDivision(n / 10);
    }

    public static int loopsDivision(int n) {
        int ans = 0;
        while (n != 0) {
            ans += n % 10;
            n /= 10;
        }
        return ans;
    }
}
