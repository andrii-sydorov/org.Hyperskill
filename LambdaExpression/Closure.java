package LambdaExpression;

import java.util.function.UnaryOperator;

/**
 * Creating a closure  
 * 
 * Write a lambda expression that takes single
 * String argument, removes all whitespaces on its both ends and adds PREFIX
 * (before) and SUFFIX (after) to it. PREFIX and SUFFIX are final variables
 * provided in the code template.
 * 
 * 
 * @author SMD_ASY
 *
 */

public class Closure {

	public static final String PREFIX = "__pref__";
	public static final String SUFFIX = "__suff__";

	public static UnaryOperator<String> operator = x -> PREFIX + x.trim() + SUFFIX;

}
