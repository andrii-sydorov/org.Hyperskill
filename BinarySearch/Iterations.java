package BinarySearch;

public class Iterations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] toSearchFirst = { 700, 810, 930, 1100, 1230, 1250, 1310, 1330 };
		int valueFirst = 1230;
		System.out.println(binarySearch(toSearchFirst, valueFirst));
		System.out.println();
		int[] toSearchSecond = { 11, 13, 15, 17, 19, 21, 23, 25, 27 };
		int valueSecond = 26;
		System.out.println(binarySearch(toSearchSecond, valueSecond));
	}

	private static int binarySearch(int[] toSearch, int value) {

		int left = 0;
		int right = toSearch.length - 1;
		int iterations = 0;

		while (left <= right) {

			iterations++;

			int middle = (right + left) / 2;
			if (toSearch[middle] == value) {
				System.out.println("Number of iterations " + iterations);
				return middle;
			} else if (toSearch[middle] > value) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}

		}
		System.out.println("Number of iterations " + iterations);
		return -1;
	}

}
