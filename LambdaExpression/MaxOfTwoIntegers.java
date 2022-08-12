package LambdaExpression;

import java.util.function.IntBinaryOperator;

class MaxOfTwoIntegers {

    public static IntBinaryOperator binaryOperator = (x, y) -> x > y ? x : y;
}
