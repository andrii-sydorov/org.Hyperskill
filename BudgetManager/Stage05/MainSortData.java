package BudgetManager.Stage05;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class MainSortData {
	SortingTool st;
	public static NumberFormat nfe = NumberFormat.getInstance(Locale.US);

	public MainSortData(SortingTool st) {
		this.st = st;
		nfe.setMaximumFractionDigits(2);
		nfe.setMinimumFractionDigits(2);
	}

	public void sort(List<Purchases> p) {
		st.sortData(p);
	}

	public static String format(double d) {
		return nfe.format(d);
	}

}
