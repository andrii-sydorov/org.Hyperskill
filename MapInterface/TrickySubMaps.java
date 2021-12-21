package MapInterface;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.SortedMap;

public class TrickySubMaps {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		TreeMap<Integer, String> map = new TreeMap<>();
		Arrays.stream(scanner.nextLine().split("\\s")).forEach(s -> {
			String[] pair = s.split(":");
			map.put(Integer.parseInt(pair[0]), pair[1]);
		});

		Map<Integer, String> res = MapUtils.getSubMap(map);
		res.forEach((k, v) -> System.out.println(k + " : " + v));
		
		scanner.close();
	}

	public static <K, V> SortedMap<K, V> buildMap(SortedMap<K, V> map, boolean even) {
		Set<K> set = map.keySet();
		int l = set.size();
		K start = null;
		K stop = null;
		int index = 0;

		for (K k : set) {
			if (even) {
				if (index == l - 5) {
					start = k;
				}
				if (index == l - 1) {
					stop = k;
				}
			} else {
				if (index == 0) {
					start = k;
				}
				if (index == 4) {
					stop = k;
				}
			}
			index++;
		}
		return map.subMap(start, stop);
	}

//		SortedMap<Integer, String> newMap = buildMap(map, even);
//		newMap.forEach((key, value) -> System.out.println(key + " " + value));

}

//	public static void buildMap(SortedMap<Integer, String> map, boolean even) {
//
//	}
