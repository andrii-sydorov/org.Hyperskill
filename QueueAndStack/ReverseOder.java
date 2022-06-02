package QueueAndStack;

import java.util.Scanner;
import java.util.Deque;
import java.util.ArrayDeque;

/**
 * The LIFO principle in action Write a program that reads the input elements
 * and outputs them in the reverse order.
 * 
 * The first string contains the number of elements. Each line followed the
 * first one contains an element.
 * 
 * Sample Input: 
 * 3 
 * 1 
 * 2 
 * 3
 * 
 * Sample Output: 
 * 3 
 * 2 
 * 1
 * 
 * @author SMD_ASY
 *
 */

public class ReverseOder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = Integer.valueOf(sc.nextLine());
		Deque<Integer> deq = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			deq.push(Integer.valueOf(sc.nextLine()));
		}
		System.out.println(deq);
		sc.close();
		while (deq.peek() != null) {
			System.out.println(deq.pop());
		}
	}

}
