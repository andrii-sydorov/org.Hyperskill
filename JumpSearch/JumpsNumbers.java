package JumpSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class JumpsNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 101;
		int[] array = makeArray(n);
		System.out.println(Arrays.toString(array));
		System.out.println(jumpSearch(array, 49));
		Map<Integer, Integer> map = makeMap(array, array);
		List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
		Comparator<Entry<Integer, Integer>> comp = (x, y) -> x.getValue().compareTo(y.getValue());
		list.sort(comp);
		list.forEach(x -> System.out.println(x));
//		System.out.println(jumpSearchRecursive(array, 0, array.length - 1, 49));
	}

	private static int[] makeArray(int n) {
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = i + 1;
		}
		return ans;
	}

	private static Map<Integer, Integer> makeMap(int[] arrayToSearch, int[] arraySearchFor) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arraySearchFor.length; i++) {
			int value = arraySearchFor[i];
			map.put(value, jumpSearch(arrayToSearch, value));
		}
		return map;
	}

	private static int jumpSearch(int[] array, int value) {

		int curr = 0;
		int prev = 0;
		int last = array.length - 1;
		int step = (int) Math.sqrt(array.length);
		int count = 0;

		while (array[curr] < value) {
			count++;
			if (curr == last) {
				return -1;
			}
			prev = curr;
			curr = Math.min(curr + step, last);
		}

		while (array[curr] > value) {
			count++;
			if (curr == prev) {
				return -1;
			}
			curr--;
		}

		if (array[curr] == value) {
			count++;
			return curr;
			// return count;
		}

		return -1;
	}

	private static int jumpSearchRecursive(int[] searchArray, int start, int stop, int value) {

		int step = (int) Math.sqrt(stop - start);
		int curr = start;

		if (searchArray[curr] == value) {
			return curr;
		}

		if (searchArray[curr] < value) {
			if (curr == stop) {
				return -1;
			}
			int tempIndex = jumpSearchRecursive(searchArray, curr + step, stop, value);
			if (tempIndex < 0) {
				return -1;
			}
			return step + tempIndex;
		}

		if (searchArray[curr] > value) {
			if (curr == start) {
				return -1;
			}
			int tempIndex = jumpSearchRecursive(searchArray, curr - step, curr, value);
			if (tempIndex < 0) {
				return -1;
			}
			return curr - tempIndex;
		}

		if (searchArray[curr] == value) {
			return curr;
		}

		return -1;
	}

}
