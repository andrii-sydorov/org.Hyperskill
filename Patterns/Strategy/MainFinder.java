package Patterns.Strategy;

import java.util.Scanner;

/**
 * Use the strategy pattern to implement algorithms to find max and min values
 * in a given array. The basic structure of the provided classes is described
 * below: your job is to complete it.
 * 
 * The class Finder represents the general finding algorithm itself. It has
 * find(int[] numbers) method that returns the result of finding according to
 * the specified strategy.
 * 
 * The interface FindingStrategy provides getResult() method to write new
 * concrete finding strategies.
 * 
 * Please, do not change the interface FindingStrategy, and do not rename the
 * existing methods.
 * 
 * If the array is empty, the Finder should return Integer.MAX_VALUE in case of
 * finding the min value and Integer.MIN_VALUE in case of finding the max value.
 * 
 * @author SMD_ASY
 *
 */

public class MainFinder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String[] line = sc.nextLine().split("\\s+");
		int[] numbers = null;
		if (line[0].equals("EMPTY")) {
			numbers = new int[0];
		} else {
			numbers = new int[line.length];
			for (int i = 0; i < line.length; i++) {
				numbers[i] = Integer.parseInt(line[i]);
			}
		}
		String type = sc.nextLine();
		Finder f = null;
		switch (type) {
		case "MIN":
			f = new Finder(new MinFindingStrategy());
			break;
		case "MAX":
			f = new Finder(new MaxFindingStrategy());
			break;
		default:
			break;
		}
		sc.close();
		if (f == null) {
			throw new RuntimeException("Unknown strategy type passed. Please, write to the author of the problem.");
		}
		System.out.println(f.find(numbers));
	}

}

class Finder {
	private FindingStrategy strategy;

	public Finder(FindingStrategy strategy) {
		this.strategy = strategy;
	}

	public int find(int[] numbers) {
		return strategy.getResult(numbers);
	}
}

interface FindingStrategy {

	/**
	 * Returns search result
	 */
	int getResult(int[] numbers);
}

class MaxFindingStrategy implements FindingStrategy {
	public int getResult(int[] numbers) {
		if (numbers.length == 0) {
			return Integer.MIN_VALUE;
		}
		for (int i = 0; i < numbers.length - 1; i++) {
			for (int j = i + 1; j < numbers.length; j++) {
				if (numbers[i] > numbers[j]) {
					int temp = numbers[j];
					numbers[j] = numbers[i];
					numbers[i] = temp;
				}
			}
		}
		return numbers[numbers.length - 1];
	}
}

class MinFindingStrategy implements FindingStrategy {
	public int getResult(int[] numbers) {
		if (numbers.length == 0) {
			return Integer.MIN_VALUE;
		}
		for (int i = 0; i < numbers.length - 1; i++) {
			for (int j = i + 1; j < numbers.length; j++) {
				if (numbers[i] > numbers[j]) {
					int temp = numbers[j];
					numbers[j] = numbers[i];
					numbers[i] = temp;
				}
			}
		}
		return numbers[0];
	}
}
