package DivideAndConqenuer;

import java.util.Arrays;

public class SumOfArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 1, 4, 2, 8, 3, 1, 6 };
		System.out.println(sum(arr));
		System.out.println(max(arr));
		System.out.println(pow(2, 6));
	}

	private static int sum(int[] arr) {
		if (arr.length == 0) {
			return 0;
		}
		if (arr.length == 1) {
			return arr[0];
		}
		return sum(Arrays.copyOfRange(arr, 0, arr.length / 2))
				+ sum(Arrays.copyOfRange(arr, arr.length / 2, arr.length));
	}

	private static int max(int[] arr) {
		if (arr.length == 0) {
			return 0;
		}
		if (arr.length == 1) {
			return arr[0];
		}
		if (arr.length == 2) {
			return Math.max(arr[0], arr[1]);
		}
		return Math.max(max(Arrays.copyOfRange(arr, 0, arr.length / 2)),
				max(Arrays.copyOfRange(arr, arr.length / 2, arr.length)));
	}

	private static int pow(int x, int n) {
		if (n == 0) {
			return 1;
		}
		return x * pow(x, n - 1);
	}

}
