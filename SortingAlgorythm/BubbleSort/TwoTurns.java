package SortingAlgorythm.BubbleSort;

import java.util.Arrays;

public class TwoTurns {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[] { 12, 20, 11, 10, 14, 16, 15, 10 };
		System.out.println("Ascedning order sorting algorythm");
		bubbleSortAscendingOrder(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println("Descedning order sorting algorythm");
		arr = new int[] {1, 2, 3, 4, 5, 6};
		bubbleSortDescendingOrder(arr);
		System.out.println(Arrays.toString(arr));
	}

	private static void bubbleSortDescendingOrder(int[] arr) {
		// TODO Auto-generated method stub
		int turns = 0;
		boolean isSorted = false;
		while (!isSorted) {
			isSorted = true;
			for (int i = 0; i < arr.length - 1 - turns; i++) {
				if (arr[i] < arr[i + 1]) {
					int t = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = t;
					isSorted = false;
				}
			}
			turns++;
			if (turns == 3) {
				System.out.println(Arrays.toString(arr));
			}
		}
	}

	private static void bubbleSortAscendingOrder(int[] arr) {
		int turns = 0;
		boolean isSorted = false;
		while (!isSorted) {
			isSorted = true;
			for (int i = 0; i < arr.length - 1 - turns; i++) {
				if (arr[i] > arr[i + 1]) {
					int t = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = t;
					isSorted = false;
				}
			}
			turns++;
			if (turns == 2) {
				System.out.println(Arrays.toString(arr));
			}
		}
	}

}
