package SearchingAlgorythm.LinearSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * Searching for an array in an array 
 * 
 * We search for every value of the first array in the second array using linear
 * search.
 * 
 * Download the file and figure out the number of comparison steps required for
 * a complete execution of the algorithm? Report a typo
 * 
 * Sample Input 1:
 * 
 * 0 4 8 0 6 1 4 8 0 7 3 2 9 0 6 9 2 2 5 7
 * 
 * Sample Output 1:
 * 
 * 77
 * 
 * @author SMD_ASY
 *
 */

public class SearhingForArrayInArray {

	public static void main(String[] args) throws IOException {
// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int[] arr1 = Arrays.stream(bf.readLine().split("\\W+")).mapToInt(Integer::valueOf).toArray();
		int[] arr2 = Arrays.stream(bf.readLine().split("\\W+")).mapToInt(Integer::valueOf).toArray();
		bf.close();
		System.out.println(countSteps(arr1, arr2));
	}

	private static int countSteps(int[] arr1, int[] arr2) {
		int count = 0;
		for (int i = 0; i < arr1.length; i++) {
			int t = arr1[i];
			for (int j = 0; j < arr2.length; j++) {
				count++;
				if (t == arr2[j]) {
					break;
				}
			}
		}
		return count;
	}

}
