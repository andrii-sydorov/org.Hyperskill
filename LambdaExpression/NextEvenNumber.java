package LambdaExpression;

import java.util.function.LongUnaryOperator;

/**
 * Next even number 
 * 
 * Write a lambda expression that accepts a long value
 * and returns the next even number.
 * 
 * It is guaranteed that the input number is non-negative.
 * 
 * Solution format. Submit your lambda expression in any valid format with ; at
 * the end.
 * 
 * Examples: x -> x; (x) -> x; (x) -> { return x; };
 * 
 * Report a typo 
 * 
 * Sample Input 1:
 * 
 * 2 
 * 
 * Sample Output 1:
 * 
 * 4 
 * 
 * Sample Input 2:
 * 
 * 317 
 * 
 * Sample Output 2:
 * 
 * 318
 * 
 * @author SMD_ASY
 *
 */

class Operator {

	public static LongUnaryOperator unaryOperator = x -> x % 2 == 0 ? x + 2 : x + 1;

}