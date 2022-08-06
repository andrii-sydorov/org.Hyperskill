package BinarySearch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Approximate search  
 * Download the file. The first line contains a
 * sorted array of numbers. In this array, you need to find the nearest number
 * for each number from the second row. If two numbers are equidistant from the
 * one you're analyzing, select the left value. As an outcome, specify these
 * nearest values.
 * 
 * Report a typo Sample Input 1:
 * 
 * 3 10 12 13 19 38 42 48 50 63 82 85 90 97 98 86 91 73 62 82 11 85 40 31 62 76
 * 67 28 97 21 Sample Output 1:
 * 
 * 85 90 82 63 82 10 85 38 38 63 82 63 19 97 19
 * 
 * @author SMD_ASY
 *
 */

public class AproximateSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(RepeatPreviousTopic.equals());
		int[] toSearch = { 5, 32, 35, 38, 39, 49, 57, 60, 60, 62, 66, 81, 90, 96, 98 };
		int[] searchFor = { 35, 11, 2, 96, 93, 48, 96, 44, 2, 52, 23, 56, 55, 48, 74 };
		// 85, 90, 82, 63, 82, 10, 85, 38, 38, 63, 82, 63, 19, 97, 19
		System.out.println(Arrays.toString(nearestNumber(toSearch, searchFor)));
	}

	private static int[] nearestNumber(int[] toSearch, int[] searchFor) {
		int[] ans = new int[searchFor.length];
		for (int i = 0; i < searchFor.length; i++) {
			ans[i] = toSearch[binarySearch(toSearch, searchFor[i])];
		}
		return ans;
	}

	private static int binarySearch(int[] toSearch, int value) {

		int left = 0;
		int right = toSearch.length - 1;
		int middle = 0;

		while (left <= right) {
			middle = (left + right) / 2;
			if (toSearch[middle] == value) {
				return middle;
			} else if (toSearch[middle] < value) {
				left = middle + 1;
			} else {
				right = middle - 1;
			}
		}

		if (toSearch[middle] > value && middle - 1 > 0) {

			int diff1 = toSearch[middle] - value;
			int diff2 = value - toSearch[middle - 1];
			return diff1 < diff2 ? middle : middle - 1;

		}

		if (toSearch[middle] < value && middle + 1 <= toSearch.length - 1) {

			int diff1 = value - toSearch[middle];
			int diff2 = toSearch[middle + 1] - value;
			return diff1 <= diff2 ? middle : middle + 1;

		}

		return middle;
	}

}

class RepeatPreviousTopic {

	static int[] toSearch = { 1, 2, 3, 4, 5, 6, 7, 12, 13, 14, 15, 16, 18, 19, 21, 24, 25, 27, 29, 31, 32, 33, 34, 36,
			37, 38, 39, 40, 41, 44, 45, 46, 47, 48, 49 };
	static int[] searchFor = { 0, 5, 18, 23, 30, 31, 34, 40, 44, 48 };

	private static Map<Integer, Integer> testJumpSearch(int[] toSearch, int[] searchFor) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < searchFor.length; i++) {
			map.put(searchFor[i], jumpSearch(toSearch, searchFor[i]));
		}
		return map;
	}

	private static int jumpSearch(int[] toSearch, int value) {

		int curr = 0;
		int prev = 0;
		int step = (int) Math.sqrt(toSearch.length);
		int last = toSearch.length - 1;

		while (toSearch[curr] < value) {
			if (curr == last) {
				return -1;
			}
			prev = curr;
			curr = Math.min(step + curr, last);
		}

		while (toSearch[curr] > value) {
			if (curr == prev) {
				return -1;
			}
			curr--;
		}

		if (toSearch[curr] == value) {
			return curr;
		}

		return -1;
	}

	private static Map<Integer, Integer> testBinarySearch(int[] toSearch, int[] searchFor) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < searchFor.length; i++) {
			map.put(searchFor[i], binarySearch(toSearch, searchFor[i]));
		}
		return map;
	}

	private static int binarySearch(int[] toSearch, int value) {

		int left = 0;
		int right = toSearch.length - 1;

		while (left <= right) {
			int middle = (left + right) / 2;
			if (toSearch[middle] == value) {
				return middle;
			} else if (toSearch[middle] < value) {
				left = middle + 1;
			} else {
				right = middle - 1;
			}
		}
		return -1;
	}

	public static boolean equals() {
		Map<Integer, Integer> jump = testJumpSearch(toSearch, searchFor);
		Map<Integer, Integer> binary = testBinarySearch(toSearch, searchFor);
		return Objects.equals(jump, binary);
	}
}
