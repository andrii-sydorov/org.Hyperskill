package InsertationSort;

import java.util.Arrays;

public class StringSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] str = {"z", "e", "l", "k", "a", "x"};
		String[] ascendingSort = Arrays.copyOfRange(str, 0, str.length);
		System.out.println("Sorting in ascending order");
		insertationSortAscendingOrder(ascendingSort);
		System.out.println(Arrays.toString(ascendingSort));
		System.out.println();
		String[] descendingSort = Arrays.copyOfRange(str, 0, str.length);
		System.out.println("Sorting in descending order");
		insertationSortDescendingOrder(descendingSort);
		System.out.println(Arrays.toString(descendingSort));
	}
	
	
	// a e k l z x
	private static void insertationSortAscendingOrder(String[] array) {
		int currIndex = 1;
		while(currIndex < array.length) {
			String currValue = array[currIndex];
			int prevIndex = currIndex - 1;
			while (prevIndex >= 0 && array[prevIndex].compareTo(currValue) > 0) {
				array[prevIndex + 1] = array[prevIndex];
				prevIndex--;
			}
			array[prevIndex + 1] = currValue;  //prevIndex could be changed in loop, thats why prevIndex + 1, not currIndex
			currIndex++;
		}
	}
	
	private static void insertationSortDescendingOrder(String[] array) {
		int currIndex = 1;
		while(currIndex < array.length) {
			String currValue = array[currIndex];
			int prevIndex = currIndex - 1;
			while (prevIndex >= 0 && array[prevIndex].compareTo(currValue) < 0) {
				array[prevIndex + 1] = array[prevIndex];
				prevIndex--;
			}
			array[prevIndex + 1] = currValue;  //prevIndex could be changed in loop, thats why prevIndex + 1, not currIndex
			currIndex++;
		}
	}

}
