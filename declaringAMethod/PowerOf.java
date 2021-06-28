package declaringAMethod;

import java.util.Scanner;

/**
 * You're given the method power that takes two int numbers n and m. The method
 * should return the value of nm as a long value.
 * 
 * Write a body of the method.
 * 
 * @author SMD_ASY
 *
 */

public class PowerOf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your's value:");
		String line = sc.nextLine();
		int n = Integer.valueOf(line.split(" ")[0]);
		int m = Integer.valueOf(line.split(" ")[1]);
		sc.close();
		System.out.println(power(n, m));
	}

	public static long power(int n, int m) {
		long ans = 1;
		for (int i = 1; i <= m; i++) {
			ans *= n;
		}
		return ans;
	}

}
