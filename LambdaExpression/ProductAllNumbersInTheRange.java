package LambdaExpression;

import java.util.function.LongBinaryOperator;

/**
 * Production of all numbers in the range 
 * 
 * Write a lambda expression that
 * accepts two long arguments as a range's borders and calculates (returns) the
 * product of all numbers in this range (inclusively). It's guaranteed that 0 <=
 * left border <= right border. If left border == right border, then the result
 * is any border.
 * 
 * Solution format. Submit your lambda expression in any valid format with ; at
 * the end.
 * 
 * Examples: (x, y) -> x + y; (x, y) -> { return x + y; };
 * 
 * Report a typo Sample 
 * 
 * Input 1:
 * 
 * 0 1 Sample 
 * 
 * Output 1:
 * 
 * 0 
 * 
 * Sample Input 2:
 * 
 * 2 2 
 * 
 * Sample Output 2:
 * 
 * 2 
 * 
 * Sample Input 3:
 * 
 * 1 4 
 * 
 * Sample Output 3:
 * 
 * 24 
 * 
 * Sample Input 4:
 * 
 * 5 15 
 * 
 * Sample Output 4:
 * 
 * 54486432000
 * 
 * @author SMD_ASY
 *
 */

public class ProductAllNumbersInTheRange {

	public static LongBinaryOperator binaryOperator = (x, y) -> {
		long prod = 1;
		for (long i = x; i <= y; i++) {
			prod *= i;
		}
		return prod;
	};

}
