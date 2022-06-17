package BudgetManager.Stage05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SortCertainData implements SortingTool {

	@Override
	public void sortData(List<Purchases> ps) {
		//
		// TODO Auto-generated method stub two digit after dots!!!
		
		System.out.println();
		Purchases p = ps.get(0);
		if (p.map.size() == 0) {
			System.out.println("The purchase list is empty!");
			return;
		}
		HashMap<String, Double> map = new HashMap<>();
		for (Map.Entry<String, Double> entr : p.map.entrySet()) {
			map.put(entr.getKey(), entr.getValue());
		}

		List<Entry<String, Double>> list = new ArrayList<>(map.entrySet());
		list.sort(Entry.comparingByValue());
		Collections.reverse(list);
		System.out.println(p.getDescription() + ":");
		list.forEach(x -> System.out.println(x.getKey() + " $" + MainSortData.format(x.getValue())));
		System.out.println("Total sum: $" + MainSortData.format(p.getBalance()));
	}

}
