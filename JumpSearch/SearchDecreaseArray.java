package JumpSearch;

/**
 * Search in a decreasing array Medium 148 users solved this problem. Latest
 * completion was 4 minutes ago. Download the file. The first line contains an
 * array of integers sorted in descending order. The second line contains the
 * numbers you need to find in the first array. Change the search function so
 * that it works on a decreasing array. As the answer, enter the results of the
 * search for numbers from the second row in the array from the first row.
 * 
 * If the element is not found, return -1.
 * 
 * Report a typo Sample Input 1:
 * 
 * 28 27 26 25 24 23 22 20 17 15 14 11 10 7 6 5 4 3 2 0 
 * 0 3 5 8 11 12 13 15 16 27 
 * 
 * Sample Output 1:
 * 
 * 19 17 15 -1 11 -1 -1 9 -1 1
 * 
 * @author SMD_ASY
 *
 */

public class SearchDecreaseArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] searchIn = { 28, 27, 26, 25, 24, 23, 19, 18, 16, 15, 14, 13, 12, 9, 6, 5, 4, 3, 1, 0 };
		int[] searchFor = { 2, 7, 10, 12, 13, 15, 16, 17, 20, 29 };
		searchMethod(searchIn, searchFor);
	}

	private static void searchMethod(int[] searchIn, int[] searchFor) {

		for (int i = 0; i < searchFor.length; i++) {
			int valueToFind = searchFor[i];
			System.out.print(findIndexDescOrder(searchIn, valueToFind) + " ");
		}

	}

	private static int findIndexAscOrder(int[] searchIn, int valueToFind) {
		int step = (int) Math.sqrt(searchIn.length);
		int curr = 0;
		int prev = 0;
		int last = searchIn.length - 1;

		while (searchIn[curr] < valueToFind) {
			if (curr == last) {
				return -1;
			}
			prev = curr;
			curr = Math.min(curr + step, last);
		}

		while (searchIn[curr] > valueToFind) {
			curr--;
			if (curr <= prev) {
				return -1;
			}
		}

		if (searchIn[curr] == valueToFind) {
			return curr;
		}

		return -1;
	}

	private static int findIndexDescOrder(int[] searchIn, int valueToFind) {
		int step = (int) Math.sqrt(searchIn.length);
		int curr = 0;
		int prev = 0;
		int last = searchIn.length;

		while (searchIn[curr] > valueToFind) {

			prev = curr;
			curr = Math.min(curr + step, last - 1);
			if (curr == last) {
				return -1;
			}

		}

		while (searchIn[curr] < valueToFind) {
			curr--;
			if (curr <= prev) {
				return -1;
			}
		}

		if (searchIn[curr] == valueToFind) {
			return curr;
		}

		return -1;
	}

}
