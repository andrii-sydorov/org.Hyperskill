package MapInterface;

import java.util.Map;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class MapUtils {

//	public static void mapShare(Map<String, String> map) {
//		String t = null;
//		for (Map.Entry<String, String> en : map.entrySet()) {
//			if (en.getKey().equals("a")) {
//				t = en.getValue();
//				continue;
//			} else if (en.getKey().equals("c")) {
//				map.remove(en.getKey());
//			} else {
//				if (t != null) {
//					en.setValue(t);
//				}
//			}
//		}
//	}

//	public static void mapShare(Map<String, String> map) {
//		String t = null;
//		Iterator<?> iter = map.entrySet().iterator();
//		while (iter.hasNext()) {
//			Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next();
//			if (entry.getKey().equals("a")) {
//				t = entry.getValue();
//				map.put("b", t);
//			} else if (entry.getKey().equals("c")) {
//				iter.remove();
//			}
//		}
//	}

	public static void mapShare(Map<String, String> map) {
		if (map.containsKey("a")) {
			map.put("b", map.get("a"));
		}
		map.remove("c");

	}

	public static SortedMap<String, Integer> wordCount(String[] line) {
		SortedMap<String, Integer> map = new TreeMap<>();
		for (int i = 0; i < line.length; i++) {
			if (map.containsKey(line[i])) {
				map.put(line[i], map.get(line[i]) + 1);
			} else {
				map.put(line[i], 1);
			}

		}
		return map;
	}

	public static void printMap(Map<String, Integer> map) {
		for (String s : map.keySet()) {
			System.out.println(s + " : " + map.get(s));
		}
	}

	public static Map<Integer, String> getSubMap(TreeMap<Integer, String> map) {
		Map<Integer, String> res = new LinkedHashMap<>();
		boolean even = map.firstKey() % 2 == 0;
		int start = even ? map.lastKey() - 4 : map.firstKey();
		int stop = even ? map.lastKey() + 1 : map.firstKey() + 5;
		SortedMap<Integer, String> temp = map.subMap(start, stop);
		int key = temp.lastKey();
		while (true) {
			if (temp.containsKey(key)) {
				res.put(key, temp.get(key));

			}
			if (key == temp.firstKey()) {
				break;
			}
			key--;
		}
		return res;
	}
	
	/**
	 * It's only in teaching purpose, how to get 4 elements from Map
	 * @param <K> must be Integer, otherwise return null.		
	 * @param <V> any Object's.
	 * @param map see description above.
	 * @param descendingOrder flag, how to build order.
	 * @return map, which consist exactly from 4 elements of previous collection.
	 */
	public static <K, V> Map<K, V> getFourElements(TreeMap<K, V> map, boolean descendingOrder) {
		if (map.firstKey() instanceof Integer) {
			boolean even = Integer.valueOf(map.firstKey().toString()) % 2 == 0 ? true : false;
			int size = 4;
			int index = 0;
			TreeMap<K, V> tree = new TreeMap<>();
			K key = null;
			if (even) {
				key = map.lastKey();
				while (true) {
					tree.put(key, map.get(key));
					key = map.lowerKey(key);
					index++;
					if (index == size) {
						break;
					}
				}
			} else {
				key = map.firstKey();
				while (true) {
					tree.put(key, map.get(key));
					key = map.higherKey(key);
					index++;
					if (index == size) {
						break;
					}
				}
			}
			return descendingOrder ? tree.descendingMap() : tree;
		}
		return null;
	}
}
