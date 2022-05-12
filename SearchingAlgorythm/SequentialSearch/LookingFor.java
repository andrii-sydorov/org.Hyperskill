package SearchingAlgorythm.SequentialSearch;

import java.util.Scanner;

/**
 * In the array below you need to find the index of the last occurrence of the
 * value 4. Write down, what index it has and what kind of search you used.
 * 
 * {3,4,6,2,1,4,5,8,3,4,0,12}
 * 
 * Please note that the index of the first element is 0. Write your answer in
 * the following format: 5, Interval
 * 
 * @author SMD_ASY
 *
 */

public class LookingFor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		// int[] arr =
		// Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::valueOf).toArray();
		String s = sc.nextLine().replaceAll("\\W", "");
		StringBuilder sb = new StringBuilder(s);
		System.out.println(sb.toString());
		System.out.println(sb.lastIndexOf("4"));
		sc.close();
		// System.out.println(lastIndexOf(arr));
	}

	private static int lastIndexOf(int[] arr) {
		int n = 4;
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == n) {
				index = i;
			}
		}
		return index;
	}

}
