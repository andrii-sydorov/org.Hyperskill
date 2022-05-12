package SearchingAlgorythm.LinearSearch;

import java.util.Scanner;
import java.util.Arrays;

/**
 * 
 * How many repetitions?
 * 
 * Download the file and calculate how many times the number given in the first
 * line occurs in the array provided below. Report a typo
 * 
 * Sample Input 1:
 * 
 * 5 6 0 0 7 4 2 7 6 6 0 3 2 9 3 3 9 7 8 0 9 8 7 2 8 6 6 0 2 9 6 7 6 5 9 0 2 3 0
 * 5 3 3 6 9 0 4 4 7 1 4 6 4 5 0 7 8 2 3 4 2 1 8 0 3 2 5 7 3 8 2 2 8 6 8 9 7 9 2
 * 3 5 5 1 7 6 3 8 6 3 8 1 9 7 6 5 9 9 2 2 3 7 7
 * 
 * Sample Output 1:
 * 
 * 7
 * 
 * @author SMD_ASY
 *
 */

public class Repetitions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = Integer.valueOf(sc.nextLine());
		int[] arr = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
		sc.close();
		System.out.println(countRepetitons(n, arr));
	}

	private static int countRepetitons(int n, int[] arr) {
		// TODO Auto-generated method stub
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == n) {
				count++;
			}
		}
		return count;
	}

}
