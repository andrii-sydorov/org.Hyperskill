package BinarySearch;

import java.util.Arrays;

/**
 * An array of integers is given:
 * 
 * [1, 2, 3, 5, 7, 8, 9, 10] 
 * 
 * Arrange the elements according to the speed of
 * finding them by binary search (in the order of decreasing speed).
 * 
 * @author SMD_ASY
 *
 */

public class SpeedFinding {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// int[] searchFor = makeArray(100);
		// System.out.println(Arrays.toString(searchFor));
		int[] searchFor = { 1, 2, 3, 5, 7, 8, 9, 10 };
		System.out.println(getNumberOfComparisons(searchFor, 2));
		System.out.println(getNumberOfComparisons(searchFor, 10));
		System.out.println(getNumberOfComparisons(searchFor, 5));
		System.out.println(getNumberOfComparisons(searchFor, 7));
	}

	private static int[] makeArray(int limit) {
		int[] ans = new int[limit];
		for (int i = 0; i < limit; i++) {
			ans[i] = i + 1;
		}
		return ans;
	}

	private static int getNumberOfComparisons(int[] searchFor, int value) {

		int left = 0;
		int right = searchFor.length - 1;
		int count = 0;

		while (left <= right) {
			count++;
			int middle = (left + right) / 2;
			if (searchFor[middle] == value) {
				return count;
			} else if (searchFor[middle] < value) {
				left = middle + 1;
			} else {
				right = middle - 1;
			}
		}

		return count;
	}

}
