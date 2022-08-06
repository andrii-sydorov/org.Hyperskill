package JumpSearch;

/**
 * Below is an array of 14 integers:
 * 
 * { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233 } 
 * Find the element 21 using jump search.
 * 
 * Write the number of comparisons that you needed to run in order to find it.
 * 
 * The jump length has the optimal value; start comparing with the first element
 * of the array.
 * 
 * Note: to calculate block sizes, use the floor function, not the ceil
 * function!
 * 
 * @author SMD_ASY
 *
 */

public class NumberIteration {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233 };
		int toSearch = 21;
		searchIndex(a, toSearch);
		int[] b = { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233 };
		toSearch = 234;
		searchIndex(b, toSearch);
	}

	private static void searchIndex(int[] a, int toSearch) {
		int step = (int) Math.sqrt(a.length);
		int curr = 0;
		int prev = 0;
		int last = a.length - 1;
		int count = 1;

		while (a[curr] < toSearch) {
			if (curr == last) {
				System.out.printf("Index is -1 and number of comparisons is %d", count);
				return;
			}
			prev = curr;
			curr = Math.min(curr + step, last);
			count++;
		}

		while (a[curr] > toSearch) {
			if (curr == prev) {
				System.out.printf("Index is -1 and number of comparisons is %d", count);
				return;
			}
			curr--;
			count++;
		}

		if (a[curr] == toSearch) {
			count++;
			System.out.printf("Index is %d and number of comparisons is %d", curr, count);
		}
		
		System.out.println();

	}

}
