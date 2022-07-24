package JumpSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SearchOrder {
	public static void main(String[] args) {

		int[] toSearch = buildArrayToSearchFor(1, 98);
		int[] searchFor = { 1, 2, 10, 11, 12, 20, 32 };

		Map<Integer, Integer> map = buildMap(toSearch, toSearch);

		map.forEach((k, v) -> System.out.println(k + " " + v));

		List<Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
		list.sort(Entry.comparingByValue());

		list.forEach(x -> System.out.println(x));
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
		int last = toSearch.length;

		while (toSearch[curr] < value) {
			if (curr == last) {
				return count;
			}
			prev = count;
			curr = Math.min(curr + step, last - 1);
			count++;
		}

		while (toSearch[curr] > value) {
			if (curr == prev) {
				return count;
			}
			curr--;
			count++;
		}

		return count;
	}
}