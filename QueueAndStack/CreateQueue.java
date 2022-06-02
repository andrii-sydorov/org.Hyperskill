package QueueAndStack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Create ArrayDeque named queue and enqueue the following four numbers 2, 0, 1,
 * 7.
 * 
 * The code for displaying the queue is already written. Report a typo
 * 
 * Sample Input 1:
 * 
 * Sample Output 1:
 * 
 * [2, 0, 1, 7]
 * 
 * @author SMD_ASY
 *
 */

public class CreateQueue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deque<Integer> deq = new ArrayDeque<>();
		deq.add(7);
		deq.add(1);
		deq.add(0);
		deq.add(2);
		String[] arr = new String[deq.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = String.valueOf(deq.pollLast());
		}

		System.out.println(Arrays.toString(arr));

		Deque<String> deq1 = new ArrayDeque<>();
		deq1.add("2");
		deq1.add("0");
		deq1.add("1");
		deq1.add("7");
		System.out.println(deq1);
	}

}
