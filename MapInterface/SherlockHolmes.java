package MapInterface;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SherlockHolmes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your word's: ");
		String[] data = Arrays.asList(sc.nextLine().split("\\s+")).stream().map(String::toLowerCase)
				.toArray(String[]::new);
		System.out.println(toDelete(data));
		sc.close();
	}

	public static int toDelete(String[] data) {
		Map<String, Integer>[] arraysOfMaps = new HashMap[data.length];
		for (int i = 0; i < data.length; i++) {
			arraysOfMaps[i] = new HashMap<>();
			for (int j = 0; j < data[i].length(); j++) {
				String st = Character.toString(data[i].charAt(j));
				if (arraysOfMaps[i].containsKey(st)) {
					arraysOfMaps[i].put(st, arraysOfMaps[i].get(st) + 1);
				} else {
					arraysOfMaps[i].put(st, 1);
				}
			}
		}
		Map<String, Integer> mapFirst = arraysOfMaps[0];
		Map<String, Integer> mapSecond = arraysOfMaps[1];
		int count = 0;
		int ans = 0;
		for (Map.Entry<String, Integer> entrFirst : mapFirst.entrySet()) {
			count = 0;
			for (Map.Entry<String, Integer> entrSecond : mapSecond.entrySet()) {
				if (entrFirst.getKey().equals(entrSecond.getKey())) {
					count = Math.abs(entrFirst.getValue() - entrSecond.getValue());
					break;
				} else {
					count = entrFirst.getValue();
				}
			}
			ans += count;
		}
		return ans;
	}

}


