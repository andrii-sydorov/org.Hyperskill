package JumpSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JumpAndLinearSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] toSearch = buildArrayToSearch(1, 101);
		int[] forSearch = buildArrayToSearch(1, 101);

		Map<Integer, Integer> map = buildMap(forSearch, toSearch);
		
		int sum = 0;
		for (Map.Entry<Integer, Integer> entr : map.entrySet()) {
			sum += entr.getValue();
		}
		System.out.println(sum);
		
		List<Integer> ls = makeList(map);
		ls.forEach(x -> System.out.println(x));

	}

	private static List<Integer> makeList(Map<Integer, Integer> map) {
		List<Integer> ls = new ArrayList<Integer>();
		for (Map.Entry<Integer, Integer> entr : map.entrySet()) {
			if (entr.getKey() == entr.getValue()) {
				ls.add(entr.getKey());
			}
		}
		return ls;
	}

	private static Map<Integer, Integer> buildMap(int[] toSearch, int[] searchFor) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < toSearch.length; i++) {
			map.put(searchFor[i], numberOfComparison(searchFor[i], toSearch));
		}
		return map;
	}

	private static int[] buildArrayToSearch(int start, int stop) {
		int[] ans = new int[stop];
		for (int i = 0; i < stop; i++) {
			ans[i] = i + start;
		}
		return ans;
	}

	private static int numberOfComparison(int value, int[] searchFor) {

		int curr = 0;
		int prev = 0;
		int count = 1;
		int last = searchFor.length;
		int step = (int) Math.sqrt(searchFor.length);

		while (searchFor[curr] < value) {
			if (curr == last) {
				return count;
			}
			prev = curr;
			curr = Math.min(curr + step, last - 1);
			count++;
		}

		while (searchFor[curr] > value) {
			if (curr == prev) {
				return count;
			}
			count++;
			curr--;
		}

		return count;
	}

}
