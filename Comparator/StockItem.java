package Comparator;

import java.util.Collections;
import java.util.List;
import java.util.function.*;

/**
 * Most valuable items 
 * 
 * This time we have the following StockItem class
 * which has three fields: String name, double pricePerUnit, and int quantity.
 * 
 * class StockItem { 
 * 
 * 	private String name; 
 * 	private double pricePerUnit; 
 * 	private int quantity;
 * 
 * 	public StockItem(String name, double pricePerUnit, int quantity) { 
 * 		this.name = name; 
 * 		this.pricePerUnit = pricePerUnit; 
 * 		this.quantity = quantity; 
 * 	}
 * 
 * @Override 
 * 	public String toString() { 
 * 		return name + ": " + pricePerUnit + ", "
 *           + quantity + ";"; 
 *  }
 * 
 *  // getters 
 *  
 *  
 * } 
 *  
 *  
 *  Write a method that accepts a list of StockItem
 *  objects, sorts them by their total value in descending order, and
 *  returns the sorted list. You don't need to read or write anything
 *  from or to the console, just implement the method.
 * 
 *  Sample Input 1:
 * 
 *  nails: 0.01, 512; hammers: 7.5, 24; screws: 0.02, 318 
 *  
 *  Sample Output 1:
 * 
 *  hammers: 7.5, 24; screws: 0.02, 318; nails: 0.01, 512 
 *  
 *  Sample Input 2:
 * 
 *  pens: 3.72, 40; pencils: 1.45, 75; notebooks: 0.84, 130 
 *  
 *  Sample Output 2:
 * 
 * pens: 3.72, 40; notebooks: 0.84, 130; pencils: 1.45, 75
 * 
 * 
 * @author SMD_ASY
 *
 */

public class StockItem {

	private String name;
	private int quantity;
	private double price;

	public StockItem(String name, int quantity, double price) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPricePerUnit() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}

class UtilsSort {
	public static List<StockItem> sort(List<StockItem> stockItems) {
		stockItems.sort((x, y) -> x.getQuantity() * x.getPricePerUnit() > y.getQuantity() * y.getPricePerUnit() ? 1
				: x.getQuantity() * x.getPricePerUnit() < y.getQuantity() * y.getPricePerUnit() ? -1 : 0);
		Collections.reverse(stockItems);
		return stockItems;
	}
}
