package LambdaExpression;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Apply function to all elements 
 * 
 * You need to implement a method that
 * applies the given function to all elements of the input array.
 * 
 * Do not create a new array, just change the elements of the original one.
 * 
 * @author SMD_ASY
 *
 */

public class ApplyFunctionToAllElemetns {

	public static <T> void applyFunction(T[] array, Function<T, T> func) {
		for (int i = 0; i < array.length; i++) {
			array[i] = func.apply(array[i]);
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String method = scanner.nextLine();
		String[] array = scanner.nextLine().split(" ");
		applyFunction(array, "lower".equals(method) ? String::toLowerCase
				: "upper".equals(method) ? String::toUpperCase
						: "new".equals(method) ? String::new : "trim".equals(method) ? String::trim : String::intern);
		Arrays.stream(array).forEach(e -> System.out.print(e + " "));
		scanner.close();
	}

}
