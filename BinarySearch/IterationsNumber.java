package BinarySearch;

/**
 * 
 * Number of iterations
 * 
 * You have a sorted array of an unknown length, and you want to count the
 * number of comparisons until you find an index of the 7th element. To do that,
 * implement the binary search algorithm.
 * 
 * Keep in mind that the indexes begin with 0!
 * 
 * @author SMD_ASY
 *
 */

public class IterationsNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] toSearch = { 0, 3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36, 39, 42, 45, 48 };
		int value = toSearch[6];
		System.out.println(getIndex(toSearch, value));
	}

	private static int getIndex(int[] toSearch, int value) {

		int left = 0;
		int right = toSearch.length - 1;
		int middle = 0;
		int count = 0;

		while (left <= right) {
			count++;
			middle = (left + right) / 2;
			if (toSearch[middle] == value) {
				return count;
			} else if (toSearch[middle] > value) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
		}

		return count;
	}

}
