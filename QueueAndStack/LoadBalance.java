package QueueAndStack;

import java.util.*;

/**
 * Greedy load balance Write a program that implements a simple load balancer.
 * 
 * The program must read tasks from the standard input and distribute them
 * between two queues. Tasks will be processed by a system (in the future). Each
 * task has a unique identifier and a number indicating the load on the system
 * (in parrots).
 * 
 * The balancer should distribute tasks between queues by the following rule:
 * the task is added to the lower-load queue (by the total load). If both queues
 * have the same total load indicator, the task must be added to the first
 * queue.
 * 
 * It's guaranteed, the input data contain at least two tasks.
 * 
 * Input data format
 * 
 * The first line contains the number of tasks. Other lines consist of task
 * description: an identifier and a load indicator (separated by a space).
 * 
 * Output data form
 * 
 * The first line should contain identifiers of tasks in the first queue, the
 * second line should contain the identifiers in the second queue.
 * 
 * 
 * Sample Input: 
 * 6 
 * 1 1 
 * 2 1 
 * 3 1 
 * 4 3 
 * 5 1 
 * 6 1
 * 
 * Sample Output: 
 * 1 3 5 6 
 * 2 4
 * 
 * @author SMD_ASY
 *
 */

public class LoadBalance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int turns = Integer.valueOf(sc.nextLine());
		int sum1 = 0;
		int sum2 = 0;
		Deque<Integer> deq1 = new ArrayDeque<>();
		Deque<Integer> deq2 = new ArrayDeque<>();
		for (int i = 0; i < turns; i++) {
			int[] arr = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::valueOf).toArray();
			if (sum1 <= sum2) {
				deq1.add(arr[0]);
				sum1 += arr[1];
			} else {
				deq2.add(arr[0]);
				sum2 += arr[1];
			}
		}
		sc.close();
		while (deq1.peek() != null) {
			System.out.print(deq1.pop() + " ");
		}
		System.out.println();
		while (deq2.peekLast() != null) {
			System.out.print(deq2.removeLast() + " ");
		}
	}

}
