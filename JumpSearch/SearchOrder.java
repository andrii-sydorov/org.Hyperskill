package JumpSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SearchOrder {
	public static void main(String[] args) {

		// int[] toSearch = buildArrayToSearchFor(1, 98);
		int[] toSearch = { 1, 2, 3, 4, 5, 6, 7, 12, 13, 14, 15, 16, 18, 19, 21, 24, 25, 27, 29, 31, 32, 33, 34, 36, 37,
				38, 39, 40, 41, 44, 45, 46, 47, 48, 49 };
		int[] searchFor = { 0, 5, 18, 23, 30, 31, 34, 40, 44, 48 };

		Map<Integer, Integer> map = buildMap(toSearch, searchFor);

		map.forEach((k, v) -> System.out.println(k + " " + v));
		// sort map according number of comparisons
		List<Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
		list.sort(Entry.comparingByValue());

		list.forEach(x -> System.out.println(x));
		// find sum of all comparison operations
		int sum = 0;
		for (Map.Entry<Integer, Integer> entr : map.entrySet()) {
			sum += entr.getValue();
		}
		System.out.println(sum);
	}

	private static int[] buildArrayToSearchFor(int start, int stop) {
		int[] ans = new int[stop];
		for (int i = 0; i < stop; i++) {
			ans[i] = i + start;
		}
		return ans;
	}

	private static Map<Integer, Integer> buildMap(int[] toSearch, int[] searchFor) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < searchFor.length; i++) {
			map.put(searchFor[i], numberOfComparison(toSearch, searchFor[i]));
		}
		return map;
	}

	private static int numberOfComparison(int[] toSearch, int value) {

		int step = (int) Math.sqrt(toSearch.length);
		int count = 1;
		int curr = 0;
		int prev = 0;
		int last = toSearch.length - 1;

		while (toSearch[curr] < value) {
			if (curr == last) {
				return count;
			}
			prev = curr;
			curr = Math.min(curr + step, last);
			count++;
		}

		while (toSearch[curr] > value) {
			if (curr == prev) {
				return count;
			}
			curr--;
			count++;
		}
		
		if (toSearch[curr] == value) {
			count++;
		}

		return count;
	}
}