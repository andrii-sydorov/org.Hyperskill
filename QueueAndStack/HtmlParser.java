package QueueAndStack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HtmlParser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String in = sc.nextLine();
		parseHtml(in);
		System.out.println();
		interestingSolution(in);
		sc.close();
	}

	private static void parseHtml(String in) {
		List<String> ls = buildListOfTags(in);
		Map<String, String> map = new LinkedHashMap<>();
		for (int i = 0; i < ls.size() / 2; i++) {
			map.put(ls.get(i + ls.size() / 2), ls.get(i));
		}
		List<String> lsStrings = buildListString(in, map);
		lsStrings.forEach(x -> System.out.println(x));
		// return ls.toString();
	}

	private static List<String> buildListOfTags(String in) {
		boolean start = false;
		StringBuilder sb = new StringBuilder();
		List<String> ls = new ArrayList<>();
		for (int i = 0; i < in.length(); i++) {
			String t = Character.toString(in.charAt(i));
			if (t.equals("<")) {
				start = true;
			}
			if (start) {
				sb.append(t);
			}
			if (t.equals(">")) {
				start = false;
				ls.add(sb.toString());
				sb.setLength(0);
			}
		}
		Collections.sort(ls);
		return ls;
	}

	private static List<String> buildListString(String in, Map<String, String> map) {
		List<String> ans = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		String temp = null;
		boolean b = false;
		for (int i = 0; i < in.length(); i++) {
			String s = Character.toString(in.charAt(i));
			if (s.equals("<")) {
				b = true;
			}
			if (b) {
				sb.append(s);
			}
			if (s.equals(">")) {
				b = false;
				String st = sb.toString();
				if (!st.contains("/")) {
					String s1 = map.get(st);
					// String s2 = mySubstring(in, start, end, i - st.length());
					temp = in.substring(i - st.length() + 1);
					String s2 = temp.substring(temp.indexOf(st) + st.length(), temp.indexOf(s1));
					if (contains(s2, map)) {
						List<String> ls = buildListString(s2, map);
						ans.addAll(ls);
						ans.add(s2);
					} else {
						ans.add(s2);
					}
					i += s2.length() + s1.length();
				}
				sb.setLength(0);
			}
		}
		return ans;

	}

	private static boolean contains(String s, Map<String, String> map) {
		Set<String> set = map.keySet();
		for (String s1 : set) {
			if (s.contains(s1)) {
				return true;
			}
		}
		return false;
	}

	private static void interestingSolution(String str) {
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '<') {
				if (str.charAt(i + 1) != '/') {
					stack.push(str.indexOf('>', i) + 1);
				} else {
					int indexOpen = stack.pop();
					System.out.println(str.substring(indexOpen, i));
				}
			}
		}
	}

}
