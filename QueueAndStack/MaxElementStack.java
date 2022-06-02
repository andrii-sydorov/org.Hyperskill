package QueueAndStack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Scanner;

public class MaxElementStack {

	private static Deque<Integer> deq = new ArrayDeque<>();
	private static int max;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = Integer.valueOf(sc.nextLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			String[] data = sc.nextLine().split("\\s+");
			String command = data[0];
			switch (command) {
			case "push":
				push(Integer.valueOf(data[1]));
				break;
			case "max":
				sb.append(getMax()).append("\n");
				break;
			case "pop":
				pop();
				break;
			}
		}
		sc.close();
		System.out.println(sb.toString());
	}

	private static void push(int t) {
		// If new number is less than maxEle
		if (t > max) {
			deq.push(2 * t - max);
			max = t;
		}

		else
			deq.push(t);
	}

	private static void pop() {
		int t = deq.peek();
		deq.pop();
		if (t > max) {
			max = 2 * max - t;
		}
	}

	private static int getMax() {
		return max;
	}

}
