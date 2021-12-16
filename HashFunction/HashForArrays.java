package HashFunction;

import java.util.Scanner;
import java.util.Arrays;

/**
 * You now have the array of arrays of integers [[1,2],[2,3]][[1,2],[2,3]].
 * Using p = 2p=2 for all arrays and no modulo, calculate the hash value of the
 * top array.
 * 
 * @author SMD_ASY
 *
 */

public class HashForArrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the prime numbers: ");
		int p = Integer.valueOf(sc.nextLine());
		System.out.println("Enter the dimension of the arrays: ");
		int d = Integer.valueOf(sc.nextLine());
		int[][] data = makeMultidimensionalArray(d, sc);
		// System.out.println(Arrays.deepToString(data));
		System.out.println(calculateHashfucntionForMultidimensionalArrays(p, data));
	}

	private static int[][] makeMultidimensionalArray(int d, Scanner sc) {
		int[][] ans = new int[d][];
		for (int i = 0; i < d; i++) {
			System.out.printf("Enter the %d array \n", i + 1);
			ans[i] = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::valueOf).toArray();
		}
		return ans;
	}

	private static int calculateHashfunction(int p, int[] data) {
		int h = 0;
		for (int i = 0; i < data.length; i++) {
			h = h * p + data[i];
		}
		return h;
	}

	private static int calculateHashfucntionForMultidimensionalArrays(int p, int[][] data) {
		int[] hash = new int[data.length];
		for (int i = 0; i < data.length; i++) {
			hash[i] = calculateHashfunction(p, data[i]);
		}
		return calculateHashfunction(p, hash);
	}

}
