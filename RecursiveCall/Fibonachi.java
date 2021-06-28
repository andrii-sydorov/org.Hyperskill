package RecursiveCall;

import java.util.Scanner;

/**
 * Given the small integer n (0 <= n <= 40) you need to find the n-th number of
 * the alternating Fibonacci sequence.
 * 
 * The sequence starts with 0, 1, -1, 2, -3, 5, -8, 13, -21, ...
 * 
 * So, fib(0) = 0, fib(1) = 1 => fib(2) = -1.
 * 
 * Think of the recurrence relation and implement the method named fib in a
 * recursive way. It's not efficient in the general but works well for small n.
 * Report a typo Hint by LW Lucifer Watson fib() isn't the recursive method, it
 * calls the recursive method. Was this hint helpful?
 * 
 * Sample Input 1:
 * 
 * 2
 * 
 * Sample Output 1:
 * 
 * -1
 * 
 * Sample Input 2:
 * 
 * 3
 * 
 * Sample Output 2:
 * 
 * 2
 * 
 * 
 * 
 * Fib(n) = Fib(n - 1) + Fib(n - 2)
 */

public class Fibonachi {

	public static void main(String[] args) {
		System.out.println("Enter your's number:");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.println(alteringFibonache(n));
		sc.close();
	}

	public static long alteringFibonache(int n) {
		if (n == 0) {
			return 0;
		}
		int sign = n % 2 == 0 ? -1 : 1;
		return sign * extracted(n);
	}

	private static long extracted(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return extracted(n - 1) + extracted(n - 2);
		}
	}
}
