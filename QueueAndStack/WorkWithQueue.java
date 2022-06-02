package QueueAndStack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class WorkWithQueue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<Integer> queue = new ArrayDeque<>(Arrays.asList(1, 2, 3, 4));
		queue.add(5);
		queue.poll();
		queue.poll();
		System.out.println(queue);
	}

}
