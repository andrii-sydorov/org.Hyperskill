package BudgetManager.Stage05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SortByItems implements SortingTool {

	@Override
	public void sortData(List<Purchases> p) {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("Types:");
		Map<Purchases, Double> map = new HashMap<>();
		double totalPurchases = 0;
		for (Purchases ps : p) {
			map.put(ps, ps.getBalance());
			totalPurchases += ps.getBalance();
		}
		
		List<Entry<Purchases, Double>> list = new ArrayList<>(map.entrySet());
		list.sort(Entry.comparingByValue());
		Collections.reverse(list);
		list.forEach(x -> System.out.println(x.getKey().getDescription() + " - $" + MainSortData.format(x.getValue())));
		System.out.println("Total sum: $" + MainSortData.format(totalPurchases));
	}

}
