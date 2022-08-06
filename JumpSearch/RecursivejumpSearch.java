package JumpSearch;

import java.util.Arrays;
import java.util.Random;

public class RecursivejumpSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int length = 100;
		int limit = 1000;
		int[] searchArray = { 918, 297, 862, 311, 913, 616, 998, 337, 718, 943, 533, 864, 503, 684, 497, 238, 111, 863,
				749, 657, 313, 79, 116, 83, 726, 268, 439, 765, 114, 1, 206, 976, 680, 376, 811, 120, 388, 121, 74, 742,
				654, 97, 981, 608, 543, 179, 163, 847, 587, 901, 736, 348, 15, 954, 464, 135, 28, 799, 517, 110, 697,
				636, 793, 271, 335, 194, 597, 707, 341, 307, 831, 686, 820, 2, 674, 319, 613, 872, 231, 400, 127, 192,
				170, 810, 252, 40, 391, 137, 771, 52, 115, 767, 928, 353, 576, 792, 343, 562, 248, 306

		};

		// int[] searchArray = generateArray(length, limit);

		System.out.println(Arrays.toString(searchArray));
		sortArray(searchArray);
		System.out.println(Arrays.toString(searchArray));
		int loopIndex = jumpSearchLoops(searchArray, 116);
		System.out.println(loopIndex);
		int recursiveIndex = jumpSearchRecursive(searchArray, 116);
		System.out.println(recursiveIndex);
		
		for (int i = 0; i < searchArray.length; i++) {
			if (jumpSearchLoops(searchArray, searchArray[i]) != jumpSearchRecursive(searchArray, searchArray[i])) {
				System.out.println(i);
			}
		}

		System.out.println(jumpSearchLoops(searchArray, 998));
		System.out.println(jumpSearchRecursive(searchArray, 998));
		
		for (int i = 0; i < 100000; i++) {
			if (jumpSearchLoops(searchArray, i) != jumpSearchRecursive(searchArray, i)) {
				System.out.println(i);
			}
		}
	}

	private static int jumpSearchRecursive(int[] searchArray, int value) {
		// TODO Auto-generated method stub

		int step = (int) Math.sqrt(searchArray.length);
		int curr = 0;
		int prev = 0;

		if (searchArray.length == 1 && searchArray[curr] != prev) {
			return -1;
		}

		if (searchArray[curr] < value) {
			if (searchArray[curr + step] < value) {
				if (jumpSearchRecursive(Arrays.copyOfRange(searchArray, step, searchArray.length), value) != -1) {
					return step + jumpSearchRecursive(Arrays.copyOfRange(searchArray, step, searchArray.length), value);
				} else {
					return -1;
				}
			} else if (searchArray[curr + step] == value) {
				return curr + step;
			}

			else {
				return jumpSearchRecursive(Arrays.copyOfRange(searchArray, 0, step), value);
			}
		}

		if (searchArray[curr] > value) {
			return jumpSearchRecursive(Arrays.copyOfRange(searchArray, 0, step), value);
		}

		return curr;
	}

	private static int[] generateArray(int length, int limit) {
		Random r = new Random();
		int[] ans = new int[length];
		for (int i = 0; i < length; i++) {
			ans[i] = r.nextInt(limit);
		}
		return ans;
	}

	private static void sortArray(int[] searchArray) {
		boolean isSorted = false;
		int turns = 0;
		while (!isSorted) {
			isSorted = true;
			for (int i = 0; i < searchArray.length - 1 - turns; i++) {
				if (searchArray[i] > searchArray[i + 1]) {
					int temp = searchArray[i];
					searchArray[i] = searchArray[i + 1];
					searchArray[i + 1] = temp;
					isSorted = false;
				}
			}
			turns++;
		}
		System.out.println(turns);
	}

	private static int jumpSearchLoops(int[] searchArray, int value) {

		int step = (int) Math.sqrt(searchArray.length);
		int curr = 0;
		int prev = 0;
		int last = searchArray.length - 1;

		while (searchArray[curr] < value) {
			if (last == curr) {
				return -1;
			}
			prev = curr;
			curr = Math.min(step + curr, last);
		}

		while (searchArray[curr] > value) {
			if (curr == prev) {
				return -1;
			}
			curr--;
		}

		if (searchArray[curr] == value) {
			return curr;
		}
		
		return -1;
	}

}
