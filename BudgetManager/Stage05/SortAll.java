package BudgetManager.Stage05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SortAll implements SortingTool{

	@Override
	public void sortData(List<Purchases> p) {
		System.out.println();
		Map <String, Double> map = new HashMap<>();
		double totalPurchases = 0;
		for (Purchases ps : p) {
			for (Entry<String, Double> entr : ps.map.entrySet()) {
				map.put(entr.getKey(), entr.getValue());
				totalPurchases += entr.getValue();
			}
		}
		if (map.size() == 0) {
			System.out.println("The purchase list is empty!");
			return;
		}
		List<Entry<String, Double>> list = new ArrayList<>(map.entrySet());
		list.sort(Entry.comparingByValue());
		Collections.reverse(list);
		System.out.println("All:");
		list.forEach(x -> System.out.println(x.getKey() + " $" + MainSortData.format(x.getValue())));
		System.out.println("Total sum: $" + MainSortData.format(totalPurchases));
	}
	
	
	
}
