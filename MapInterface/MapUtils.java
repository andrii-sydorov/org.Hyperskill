package MapInterface;

import java.util.Map;
import java.util.Iterator;
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
}
