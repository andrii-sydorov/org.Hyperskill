package declaringAMethod;

import java.util.Scanner;

/**
 * Implement a method sumInRange for calculating the sum of numbers in the range
 * from (inclusive), to (exclusive).
 * 
 * @author SMD_ASY
 *
 */

public class SumInRange {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your numbers: ");
		int[] limit = new int[2];
		int i = 0;
		while (sc.hasNextInt()) {
			limit[i] = Integer.valueOf(sc.nextLine());
			i++;
		}
		sc.close();
		System.out.println(sumInRange(limit[0], limit[1]));
	}

	public static int sumInRange(int from, int to) {
		int ans = 0;
		for (int i = from; i < to; i++) {
			ans += i;
		}
		return ans;
	}

}
