package SearchingAlgorythm.SequentialSearch;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Sort and search
 * 
 * Sort the following array in descending order and write the index number of
 * the value 9.
 * 
 * {3,4,9,2,1,4,5,8,3,4,0,12}
 * 
 * Please note that the index number of the first element is 0.
 * 
 * @author SMD_ASY
 *
 */

public class SearchingProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int[] arr = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::valueOf).toArray();
		sc.close();
		sortAndSearch(arr);
	}

	private static void sortAndSearch(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] < arr[j]) {
					int t = arr[j];
					arr[j] = arr[i];
					arr[i] = t;
				}
			}
		}
		printArray(arr);
		findIndex(arr);
	}

	private static void findIndex(int[] arr) {
		int n = 9;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == n) {
				System.out.println(i);
				break;
			}
		}
	}

	private static void printArray(int[] arr) {
		System.out.println("Print the array!");
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

}
