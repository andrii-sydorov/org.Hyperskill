package MergeSort;

import java.util.Arrays;
import java.util.Random;
import InsertationSort.*;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// int[] arrayToSort = makeArray();
		//int[] arrayToSort = { 38, 27, 43, 3, 9, 82, 10 };
		int[] arrayToSort = { 20, 18, 16, 15, 14, 12, 10  };
		int[] bubbleSortArray = Arrays.copyOfRange(arrayToSort, 0, arrayToSort.length);
		bubbleSort(bubbleSortArray);
		int[] quickSortArray = Arrays.copyOfRange(arrayToSort, 0, arrayToSort.length);
		quickSort(quickSortArray, 0, quickSortArray.length - 1);
		int[] inseratitonSortArray = Arrays.copyOfRange(arrayToSort, 0, arrayToSort.length);
		insertationSort(inseratitonSortArray);
		int[] mergeSortArray = Arrays.copyOfRange(arrayToSort, 0, arrayToSort.length);
		mergeSort(mergeSortArray);
		Arrays.sort(arrayToSort);
		System.out.println("Comparison default sort with bubble sort: " + Arrays.equals(arrayToSort, bubbleSortArray));
		System.out.println("Comparison default sort with quick sort: " + Arrays.equals(arrayToSort, quickSortArray));
		System.out.println(
				"Comparison default sort with insertation sort: " + Arrays.equals(arrayToSort, inseratitonSortArray));
		System.out.println("Comparison default sort with merge sort: " + Arrays.equals(arrayToSort, mergeSortArray));
		System.out.println(Arrays.toString(quickSortArray));
	}

	private static void mergeSort(int[] array) {

		if (array.length < 2) {
			return;
		}

		int middle = array.length / 2;
		int[] leftArray = new int[middle];
		int[] rightArray = new int[array.length - middle];

		for (int i = 0; i < middle; i++) {
			leftArray[i] = array[i];
		}

		for (int i = middle; i < array.length; i++) {
			rightArray[i - middle] = array[i];
		}

		mergeSort(leftArray);
		mergeSort(rightArray);

		merge(array, leftArray, rightArray);
	}

	private static void merge(int[] array, int[] leftArray, int[] rightArray) {

		int leftPointer = 0;
		int rightPointer = 0;
		int index = 0;

		while (leftPointer < leftArray.length && rightPointer < rightArray.length) {
			if (leftArray[leftPointer] <= rightArray[rightPointer]) {
				array[index] = leftArray[leftPointer];
				leftPointer++;
			} else {
				array[index] = rightArray[rightPointer];
				rightPointer++;
			}
			index++;
		}

		while (leftPointer < leftArray.length) {
			array[index] = leftArray[leftPointer];
			index++;
			leftPointer++;
		}

		while (rightPointer < rightArray.length) {
			array[index] = rightArray[rightPointer];
			rightPointer++;
			index++;
		}
	}

	private static void insertationSort(int[] array) {

		int currIndex = 1;

		while (currIndex < array.length) {

			int prevIndex = currIndex - 1;
			int currValue = array[currIndex];

			while (prevIndex >= 0 && array[prevIndex] > currValue) {
				array[prevIndex + 1] = array[prevIndex];
				prevIndex--;
			}

			array[prevIndex + 1] = currValue;
			currIndex++;
		}
	}

	private static void quickSort(int[] array, int start, int stop) {

		if (start >= stop) {
			return;
		}

		int leftPointer = start;
		int pivot = stop;
		int rightPointer = stop;

		while (true) {

			if (array[pivot] >= array[leftPointer]) {
				leftPointer++;
			} else if (array[pivot] > array[rightPointer]) {
				int t = array[rightPointer];
				array[rightPointer] = array[leftPointer];
				array[leftPointer] = t;
			} else {
				rightPointer--;
			}
			
			if (leftPointer == rightPointer) {
				if (array[leftPointer] > array[pivot]) {
					int t = array[leftPointer];
					array[leftPointer] = array[pivot];
					array[pivot] = t;
				}
				break;
			}

		}

		quickSort(array, start, leftPointer - 1);
		quickSort(array, leftPointer + 1, stop);
	}

	private static int[] makeArray() {
		Random r = new Random();
		int length = 100;
		int[] ans = new int[length];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = i + r.nextInt(i + 1);
		}
		return ans;
	}

	private static void bubbleSort(int[] array) {
		int turn = 0;
		boolean isSorted = false;
		while (!isSorted) {
			isSorted = true;
			for (int i = 0; i < array.length - 1 - turn; i++) {
				if (array[i] > array[i + 1]) {
					int t = array[i + 1];
					array[i + 1] = array[i];
					array[i] = t;
					isSorted = false;
				}
			}
			turn++;
		}
	}

}
