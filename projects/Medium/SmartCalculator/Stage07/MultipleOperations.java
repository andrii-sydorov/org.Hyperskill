package projects.Medium.SmartCalculator.Stage07;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Stage 7/8: I’ve got the power
 * 
 * $1. Description
 * 
 * In the final stage, it remains to add operations: multiplication *, integer
 * division / and parentheses (...). They have a higher priority than addition +
 * and subtraction -.
 * 
 * Here is an example of an expression that contains all possible operations:
 * 
 * 3 + 8 * ((4 + 3) * 2 + 1) - 6 / (2 + 1)
 * 
 * The result is 121.
 * 
 * A general expression can contain many parentheses and operations with
 * different priorities. It is difficult to calculate such expressions if you do
 * not use special methods. Fortunately, there is a fairly effective and
 * universal solution, using a stack, to calculate the most general expressions.
 * 
 * From infix to postfix
 * 
 * Earlier we processed expressions written in infix notation. This notation is
 * not very convenient if an expression has operations with different
 * priorities, especially when brackets are used. But we can use postfix
 * notation, also known as Reverse Polish notation (RPN). In this notation,
 * operators follow their operands. See several examples below.
 * 
 * Infix notation 1:
 * 3 + 2 * 4
 * 
 * Postfix notation 1:
 * 3 2 4 * +
 * 
 * Infix notation 2:
 * 2 * (3 + 4) + 1
 * 
 * Postfix notation 2:
 * 2 3 4 + * 1 +
 * 
 * To better understand the postfix notation, you can play with a converter.
 * 
 * As you can see, in postfix notation operations are arranged according to
 * their priority and parentheses are not used at all. So, it is easier to
 * calculate expressions written in postfix notation.
 * 
 * You can use a stack (LIFO) to convert an expression from infix to postfix
 * notation. The stack is used to store operators for reordering. Here are some
 * rules that describe how to create an algorithm that converts an expression
 * from infix to postfix notation.
 * 
 * 1. Add operands (numbers and variables) to the result (postfix notation) as they
 *    arrive.
 * 2. If the stack is empty or contains a left parenthesis on top, push the
 *    incoming operator on the stack.
 * 3. If the incoming operator has higher precedence than the top of the stack,
 *    push it on the stack.
 * 4. If the precedence of the incoming operator is lower than or equal to that of
 *    the top of the stack, pop the stack and add operators to the result until you
 *    see an operator that has smaller precedence or a left parenthesis on the top
 *    of the stack; then add the incoming operator to the stack.
 * 5. If the incoming element is a left parenthesis, push it on the stack.
 * 6. If the incoming element is a right parenthesis, pop the stack and add
 *    operators to the result until you see a left parenthesis. Discard the pair of
 *    parentheses.
 * 7. At the end of the expression, pop the stack and add all operators to the
 *    result.
 *    
 * No parentheses should remain on the stack. Otherwise, the expression has
 * unbalanced brackets. It is a syntax error.
 * 
 * Calculating the result
 * 
 * When we have an expression in postfix notation, we can calculate it using
 * another stack. To do that, scan the postfix expression from left to right:
 * 
 * - If the incoming element is a number, push it into the stack (the whole
 *   number, not a single digit!).
 * - If the incoming element is the name of a variable, push its value into the
 *   stack.
 * - If the incoming element is an operator, then pop twice to get two numbers and
 *   perform the operation; push the result on the stack.
 * - When the expression ends, the number on the top of the stack is a final
 *   result.
 *   
 * Here you can find an example and additional explanations on postfix
 * expressions.
 * 
 * $2. Objectives
 * 
 * - Your program should support multiplication *, integer division / and
 *   parentheses (...). To do this, use infix to postfix conversion algorithm
 *   above and then calculate the result using stack.
 * - Do not forget about variables; they, and the unary minus operator, should
 *   still work.
 * - Modify the result of the /help command to explain all possible operators. You
 *   can write the output for the command in free form.
 * - The program should not stop until the user enters the /exit command.
 * - Note that a sequence of + (like +++ or +++++) is an admissible operator that
 *   should be interpreted as a single plus. A sequence of - (like -- or ---) is
 *   also an admissible operator and its meaning depends on the length. If a user
 *   enters a sequence of * or /, the program must print a message that the
 *   expression is invalid.
 * - As a bonus, you may add the power operator ^ that has a higher priority than
 *   * and /.
 *   
 * > 2^2
 * 4
 * > 2*2^3
 * 16
 * 
 * $3. Examples
 * 
 * The greater-than symbol followed by a space (>) represents the user input.
 * 
 * > 8 * 3 + 12 * (4 - 2)
 * 48
 * > 2 - 2 + 3
 * 3
 * > 4 * (2 + 3
 * Invalid expression
 * > -10
 * -10
 * > a=4
 * > b=5
 * > c=6
 * > a*2+b*3+c*(2+3)
 * 53
 * > 1 +++ 2 * 3 -- 4
 * 11
 * > 3 *** 5
 * Invalid expression
 * > 4+3)
 * Invalid expression
 * > /command
 * Unknown command
 * > /exit
 * Bye!
 * 
 * @author SMD_ASY
 *
 */

public class MultipleOperations {

    public static Map<String, Integer> map = new HashMap<>();
    public static Map<Character, Character> brackets = new HashMap<>();
    public static Map<String, String> variable = new HashMap<>();

    static {
        map.put("+", 1);
        map.put("-", 1);
        map.put("*", 2);
        map.put("/", 2);
        map.put("^", 3);

        brackets.put(')', '(');
        brackets.put('}', '{');
        brackets.put(']', '[');
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            String data = sc.nextLine().trim();
            if (data.isEmpty()) {
                continue;
            }
            // checking the menu
            Pattern p = Pattern.compile("/.*");
            Matcher m = p.matcher(data);
            if (m.matches()) {
                switch (data) {
                    case "/help":
                        System.out.println(
                                "The program calculates the sum, difference, multiplication and divide of numbers");
                        break;
                    case "/exit":
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Unknown command");
                }
                continue;
            }
            // shouldn't ends with signs for digits and literals
            p = Pattern.compile(".*[^a-zA-Z0-9()]+");
            m = p.matcher(data);
            if (m.matches()) {
                System.out.println("Invalid expression");
                // System.out.println("Shouldn't end with mathoperations");
                continue;
            }
            // shouldn't have multiple multiplication or divide signs
            p = Pattern.compile("[*/]{2,}");
            m = p.matcher(data);
            if (m.find()) {
                System.out.println("Invalid expression");
                // System.out.println("Shouldn't have multiple multiplication and divide
                // operations");
                continue;
            }
            // two operaunds shouldn't have a space beetwen operaunds
            p = Pattern.compile("\\w+\\s+\\w+");
            m = p.matcher(data);
            if (m.find()) {
                System.out.println("Invalid expression");
                System.out.println("No operators beetwen operands");
                continue;
            }
            // should have the correct sequence of brackets
            if (!checkBrackets(data)) {
                System.out.println("Invalid expression");
                // System.out.println("Invalid number of brackets");
                continue;
            }
            // right part of equation also shouldn't have digits after literals
            p = Pattern.compile("=.*(\\d*[a-zA-Z]+\\d+|\\d+[a-zA-Z]+\\d*).*");
            m = p.matcher(data);
            if (m.find()) {
                System.out.println("Invalid assignment");
                continue;
            }
            // digits shouldn't follow after literal in left parts of equations
            p = Pattern.compile(".*(\\d*[a-zA-Z]+\\d+|\\d+[a-zA-Z]+\\d*).*=?");
            m = p.matcher(data);
            if (m.find()) {
                System.out.println("Invalid identifier");
                continue;
            }
            // digits shouldn't be in left and right part of equation
            p = Pattern.compile(".*\\d+.*=.*\\d+.*");
            m = p.matcher(data);
            if (m.find()) {
                System.out.println("Invalid assignment");
                continue;
            }
            // TODO work with variables and equation signs
            // remove duplicate operations
            String ss = multipleSigns(data.replaceAll("\\s+", "").split(""));
            p = Pattern.compile("\\s*[a-zA-Z]+\\s*=.*");
            m = p.matcher(ss);
            if (m.find()) {
                String[] ar = ss.split("=");
                String key = ar[0];
                // working with right part ar[1]
                String value = readyForCalculate(ar[1]);
                try {
                    Integer.valueOf(value);
                    variable.put(key, value);
                } catch (NumberFormatException nfe) {
                    System.out.println("Unknown variable");
                }
                continue;
            }
            // calculation
            String result = readyForCalculate(ss);
            System.out.println(result == null ? "Unknown variable" : result);
            continue;
        }

        sc.close();

        System.out.println("Bye!");
    }

    public static String readyForCalculate(String ar) {
//        String[] digitsLiterals = ar.split("[^A-Za-z0-9]+");
//        String[] operations = ar.split("[a-zA-Z0-9]*");
        String[] test = makeArrays(ar);
        if (test == null) {
            return null;
        }
        Deque<String> postfix = makePostfix(test);
        String value = String.valueOf(calculate(postfix));
        return value;
    }

    // TODO find more optimal solutions with no such code replication
    public static String[] makeArrays(String ar) {
        List<String> result = new ArrayList<>();
        Pattern p = Pattern.compile("[a-zA-Z0-9]+");
        StringBuilder sbDigitsLiterals = new StringBuilder();
        for (int i = 0; i < ar.length(); i++) {
            String s = Character.toString(ar.charAt(i));
            Matcher m = p.matcher(s);
            if (m.matches()) {
                sbDigitsLiterals.append(s);
            } else {
                if (sbDigitsLiterals.length() > 0) {
                    String ss = sbDigitsLiterals.toString();
                    try {
                        Integer.valueOf(ss);
                        result.add(ss);
                    } catch (NumberFormatException nfe) {
                        if (variable.get(ss) == null) {
                            return null;
                        } else {
                            result.add(variable.get(ss));
                        }
                    }
                    sbDigitsLiterals.setLength(0);
                }
                result.add(s);
            }
        }
        if (sbDigitsLiterals.length() > 0) {
            String ss = sbDigitsLiterals.toString();
            try {
                Integer.valueOf(ss);
                result.add(ss);
            } catch (NumberFormatException nfe) {
                if (variable.get(ss) == null) {
                    return null;
                } else {
                    result.add(variable.get(ss));
                }
            }
        }
        return result.toArray(new String[0]);
    }

    public static String[] makeResult(String[] digits, String[] operations) {
        int firstIndex = 0;
        int secondIndex = 0;
        String[] result = new String[operations.length + digits.length];
        for (int i = 0; i < result.length; i++) {
            if (digits.length > operations.length) {
                if (i % 2 == 0) {
                    result[i] = digits[firstIndex];
                    firstIndex++;
                } else {
                    result[i] = operations[secondIndex];
                    secondIndex++;
                }
            } else {
                if (i % 2 == 0) {
                    result[i] = operations[secondIndex];
                    secondIndex++;
                } else {
                    result[i] = digits[firstIndex];
                    firstIndex++;
                }
            }
        }
        return result;
    }

    public static String multipleSigns(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (sb.length() == 0) {
                sb.append(arr[i]);
                continue;
            }
            if (arr[i].equals("-")) {
                String s = Character.toString(sb.charAt(sb.length() - 1));
                if (s.equals("-")) {
                    sb.setCharAt(sb.length() - 1, '+');
                } else if (s.equals("+")) {
                    sb.setCharAt(sb.length() - 1, '-');
                } else {
                    sb.append(arr[i]);
                }
            } else if (arr[i].equals("+")) {
                String s = Character.toString(sb.charAt(sb.length() - 1));
                if (s.equals("-")) {
                    sb.setCharAt(sb.length() - 1, '-');
                } else if (s.equals("+")) {
                    continue;
                } else {
                    sb.append(arr[i]);
                }
            } else {
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }

    public static boolean checkBrackets(String s) {
        Stack<Character> br = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (brackets.values().contains(s.charAt(i))) {
                br.push(s.charAt(i));
                continue;
            }
            if (brackets.keySet().contains(s.charAt(i))) {
                if (br.isEmpty()) {
                    return false;
                }
                if (brackets.get(s.charAt(i)) == (br.peek())) {
                    br.pop();
                } else {
                    return false;
                }
            }

        }
        return br.isEmpty();
    }

    // -(-(4+2)/3) to implement
    public static Deque<String> makePostfix(String[] arr) {
        Pattern p = Pattern.compile("[+-]");
        Matcher m = p.matcher(arr[0]);
        Deque<String> postfix = new ArrayDeque<String>();
        Stack<String> operations = new Stack<String>();
        if (m.matches()) {
            postfix.add("0");
        }
        for (int i = 0; i < arr.length; i++) {
            String data = arr[i];
            try {
                Integer.valueOf(data);
                postfix.add(data);
            } catch (NumberFormatException nfe) {
                while (true) {
                    if (operations.isEmpty() || operations.peek().equals("(") || data.equals("(")) {
                        operations.push(data);
                        break;
                    }
                    if (data.equals(")")) {
                        while (!operations.peek().equals("(")) {
                            postfix.add(operations.pop());
                        }
                        operations.pop();
                        break;
                    }
                    if (map.get(data) <= map.get(operations.peek())) {
                        postfix.add(operations.pop());
                    } else {
                        operations.push(data);
                        break;
                    }
                }
            }
        }
        while (!operations.isEmpty()) {
            String s = operations.pop();
            postfix.add(s);
        }
        return postfix;
    }

    public static int calculate(Deque<String> postfix) {
        Deque<Integer> digits = new ArrayDeque<>();
        while (!postfix.isEmpty()) {
            String s = postfix.pollFirst();
            try {
                int i = Integer.valueOf(s);
                digits.add(i);
            } catch (NumberFormatException nfe) {
                int second = digits.pollLast();
                int first = digits.pollLast();
                switch (s) {
                    case "+":
                        digits.add(first + second);
                        break;
                    case "-":
                        digits.add(first - second);
                        break;
                    case "*":
                        digits.add(first * second);
                        break;
                    case "/":
                        digits.add(first / second);
                        break;
                    case "^":
                        digits.push((int) Math.pow(first, second));
                }
            }
        }
        return digits.pop();
    }

}
