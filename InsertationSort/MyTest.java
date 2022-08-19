package InsertationSort;

import java.util.Arrays;

public class MyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// int[] arrayToSort = { 12, 20, 11, 10, 14, 16, 15, 10 };
		int[] arrayToSort = { 20, 18, 16, 15, 14, 12, 10 };

		int[] bubbleSortAlgorithm = Arrays.copyOfRange(arrayToSort, 0, arrayToSort.length);
		bubbleSort(bubbleSortAlgorithm);
		System.out.println(Arrays.toString(bubbleSortAlgorithm));
		int[] quickSortAlgorithm = Arrays.copyOfRange(arrayToSort, 0, arrayToSort.length);
		quickSort(quickSortAlgorithm, 0, quickSortAlgorithm.length - 1);
		System.out.println(Arrays.toString(quickSortAlgorithm));
		int[] insertationSortAlgorithm = Arrays.copyOfRange(arrayToSort, 0, arrayToSort.length);
		insertationSort(insertationSortAlgorithm);
		System.out.println(Arrays.toString(insertationSortAlgorithm));
	}

	private static void insertationSort(int[] array) {
		int i = 1;
		while (i < array.length) {
			int x = array[i];
			int j = i - 1;
			while (j >= 0 && array[j] > x) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = x;
			i++;
		}
	}

	private static void bubbleSort(int[] array) {
		boolean isSorted = false;
		int turn = 0;
		while (!isSorted) {
			isSorted = true;
			for (int i = 0; i < array.length - 1 - turn; i++) {
				if (array[i] > array[i + 1]) {
					int t = array[i];
					array[i] = array[i + 1];
					array[i + 1] = t;
					isSorted = false;
				}
			}
			turn++;
		}
	}

	public static void quickSort(int[] array, int start, int stop) {

		if (start >= stop) {
			return;
		}

		int pivot = array[stop];
		int lp = start;
		int rp = stop;

		while (true) {
			if (pivot >= array[lp]) {
				lp++;
			} else if (array[rp] < pivot) {
				int t = array[rp];
				array[rp] = array[lp];
				array[lp] = t;
			} else {
				rp--;
			}

			if (lp == rp) {
				if (pivot < array[lp]) {
					int t = array[stop];
					array[stop] = array[lp];
					array[lp] = t;
					break;
				} else if (pivot >= array[lp]) {
					break;
				}
			}
		}

		quickSort(array, start, lp - 1);
		quickSort(array, lp + 1, stop);

	}

}
