package HashFunction;

import java.util.Scanner;
import java.util.Arrays;

/**
 * You have an array [1, 2, 3][1,2,3]. Compute the hash value using p = 3p=3 and
 * no modulo.
 * 
 * @author SMD_ASY
 *
 */

public class Hash {

	private static int p;
	private static int[] data;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Enter the prime numbers:");
			p = Integer.valueOf(sc.nextLine());
			System.out.println("Enter the arrays that should be codded: ");
			data = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::valueOf).toArray();
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		} finally {
			sc.close();
		}

		System.out.println(calculateHashFunction(p, data));
	}

	private static int calculateHashFunction(int p, int[] data) {
		int hash = 0;
		for (int i = 0; i < data.length; i++) {
			hash = hash * p + data[i];
		}
		return hash;
	}

}
