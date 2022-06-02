package QueueAndStack;

import java.util.Queue;
import java.util.LinkedList;

/**
 * What elements does the queue contain.
 * 
 * Take a look at the following code with a queue:
 * 
 * Queue<Integer> q = new LinkedList<>(); 
 * q.offer(100); 
 * q.offer(200); 
 * q.peek();
 * q.offer(300); 
 * q.poll(); 
 * q.offer(400); 
 * q.peek();
 * 
 * What elements does the queue contain after executing the code above?
 * 
 * Enter all values from left (the first) to right (the last) separated by
 * spaces.
 * 
 * @author SMD_ASY
 *
 */

public class WhatElement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<Integer> q = new LinkedList<>();
		q.offer(100);
		q.offer(200);
		q.peek();
		q.offer(300);
		q.poll();
		q.offer(400);
		q.peek();
		System.out.println(q);
	}

}
