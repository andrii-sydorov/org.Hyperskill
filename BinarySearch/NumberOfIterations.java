package BinarySearch;

/**
 * How many iterations
 * 
 * Suppose we have the following sorted array:
 * 
 * [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] 
 * How many iterations would it take to find the first element of the array?
 * 
 * @author SMD_ASY
 *
 */

public class NumberOfIterations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] toSearch = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int value = toSearch[7];
		System.out.println(numberIterations(toSearch, value));
	}

	private static int numberIterations(int[] toSearch, int value) {

		int left = 0;
		int right = toSearch.length - 1;
		int count = 0;
		int middle = 0;

		while (left <= right) {
			count++;
			middle = (left + right) / 2;
			if (toSearch[middle] == value) {
				break;
			} else if (toSearch[middle] > value) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
		}

		System.out.println(middle);
		return count;
	}

}
