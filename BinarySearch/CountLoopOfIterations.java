package BinarySearch;

/**
 * Count of loop operations
 * 
 * Download the file. Change the search function so that it returns the number
 * of times the loop inside the function has been used. Search for every element
 * of this array in the same array. Calculate the total number of loop
 * operations in the search function for all elements.
 * 
 * Report a typo Sample Input 1:
 * 
 * [0, 3, 12, 15, 19, 20, 21, 22, 32, 33, 34, 38, 48, 51, 56, 59, 61, 64, 67,
 * 68, 69, 72, 76, 80, 81, 83, 84, 86, 93, 98] 
 * 
 * Sample Output 1:
 * 
 * 124
 * 
 * @author SMD_ASY
 *
 */

public class CountLoopOfIterations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] toSearch = { 3, 4, 6, 9, 13, 14, 15, 19, 21, 22, 25, 28, 31, 38, 39, 46, 57, 60, 61, 72, 75, 80, 82, 88,
				89, 97, 98 };
		System.out.println(numberOfIterations(toSearch, toSearch));
	}

	private static int numberOfIterations(int[] toSearch, int[] forSearch) {
		int sum = 0;
		for (int i = 0; i < toSearch.length; i++) {
			sum += iterationsByBinarySearch(toSearch, forSearch[i]);
		}
		return sum;
	}

	private static int iterationsByBinarySearch(int[] toSearch, int value) {

		int left = 0;
		int right = toSearch.length - 1;
		int count = 0;

		while (left <= right) {
			count++;
			int middle = (left + right) / 2;
			if (toSearch[middle] == value) {
				break;
			} else if (toSearch[middle] > value) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
		}

		return count;
	}

}
