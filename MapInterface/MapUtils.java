package MapInterface;

import java.util.Map;
import java.util.Iterator;

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
}
