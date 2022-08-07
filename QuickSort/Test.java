package QuickSort;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ar = { 17, 25, 11, 16, 10, 13, 22, 14 };
		quickSort(ar, 0, ar.length - 1);
		System.out.println(Arrays.toString(ar));
		int[] array = makeArray(65);
		int[] arr2 = Arrays.copyOfRange(array, 0, array.length);
		System.out.println(Arrays.toString(array));
		quickSort(array, 0, array.length - 1);
		System.out.println(Arrays.toString(array));
		quickSort2(arr2, 0, arr2.length - 1);
		System.out.println(Arrays.toString(arr2));
		System.out.println(Arrays.equals(array, arr2));
	}

	private static int[] makeArray(int l) {
		int[] ans = new int[l];
		Random r = new Random();
		for (int i = 0; i < l; i++) {
			ans[i] = r.nextInt(10 + i);
		}
		return ans;
	}

	private static void quickSort(int[] arr, int start, int stop) {

		if (start >= stop) {
			return;
		}

		int pivot = arr[stop];
		int lp = start;
		int rp = stop;
		
		while (true) {
			if (pivot >= arr[lp]) {
				lp++;
			} else {
				if (arr[rp] < pivot) {
					int t = arr[rp];
					arr[rp] = arr[lp];
					arr[lp] = t;
				} else {
					rp--;
				}
			}
			if (lp == rp && pivot < arr[lp]) {
				int t = arr[stop];
				arr[stop] = arr[lp];
				arr[lp] = t;
				break;
			} else if (lp == rp && pivot >= arr[lp]) {
				break;
			}
		}

		quickSort(arr, start, lp - 1);
		quickSort(arr, lp + 1, stop);
	}

	private static void quickSort2(int[] arr, int lowIndex, int highIndex) {

		if (lowIndex >= highIndex) {
			return;
		}

		int pivot = arr[highIndex];

		int leftPointer = lowIndex;
		int rightPointer = highIndex - 1;

		while (leftPointer < rightPointer) {

			while (arr[leftPointer] <= pivot && leftPointer < rightPointer) {
				leftPointer++;
			}

			while (arr[rightPointer] > pivot && leftPointer < rightPointer) {
				rightPointer--;
			}

			swap(arr, leftPointer, rightPointer);
		}

		if (arr[leftPointer] > arr[highIndex]) {
			swap(arr, leftPointer, highIndex);
		} else {
			leftPointer = highIndex;
		}

		quickSort2(arr, lowIndex, leftPointer - 1); // search left from pivot
		quickSort2(arr, leftPointer + 1, highIndex); // search rigth from pivot
	}

	private static void swap(int[] arr, int index1, int index2) {
		int t = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = t;
	}

}
