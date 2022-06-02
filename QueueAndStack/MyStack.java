package QueueAndStack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class MyStack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = Integer.valueOf(sc.nextLine());
		Deque<Integer> stack = new ArrayDeque<>();
		Deque<Integer> max = new ArrayDeque<>();
		int currentMax = Integer.MIN_VALUE;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			String[] in = sc.nextLine().split("\\s+");
			switch (in[0]) {
			case "push":
				stack.push(Integer.valueOf(in[1]));
				if (stack.peek() > currentMax) {
					currentMax = stack.peek();
				}
				max.push(currentMax);
				break;
			case "pop":
				stack.pop();
				max.pop();
				currentMax = max.peek();
				break;
			case "max":
				sb.append(currentMax).append("\n");
				break;
			default:
				break;
			}
		}
		sc.close();
		System.out.println(sb.toString());
	}

}
