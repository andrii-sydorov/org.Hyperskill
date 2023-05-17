package projects.Medium.SmartCalculator.Stage07;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
        // System.out.println(multipleSigns("--9".replaceAll("\\s+", "").split("")));
        // System.exit(0);
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            String data = sc.nextLine();
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
                        System.out.println("Unknow command");
                }
                continue;
            }
            // shouldn't ends with signs for digits and literals
            // .*[a-zA-Z0-9]+
            p = Pattern.compile(".*[^a-zA-Z0-9]+");
            m = p.matcher(data);
            if (m.matches()) {
                System.out.println("Invalid expression");
                System.out.println("Shouldn't end with mathoperations");
                continue;
            }
            // shouldn't have multiple multiplication or divide signs
            p = Pattern.compile("[*/]{2,}");
            m = p.matcher(data);
            if (m.find()) {
                System.out.println("Invalid expression");
                System.out.println("Shouldn't have multiple multiplication and divide operations");
                continue;
            }
            // should have the correct sequence of brackets
            if (!checkBrackets(data)) {
                System.out.println("Invalid expression");
                System.out.println("Invalid number of brackets");
                continue;
            }
            // right part of equation also shouldn't have digits after literals
            p = Pattern.compile("=.*\\w+\\d+.*");
            m = p.matcher(data);
            if (m.find()) {
                System.out.println("Invalid assignment");
                continue;
            }
            // digits shouldn't follow after literal in left parts of equations
            p = Pattern.compile(".*\\w+\\d+.*=?");
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
                if (value == null) {
                    System.out.println("Unknown variable");
                } else {
                    variable.put(key, value);
                }
                continue;
            }
            // calculation
            System.out.println(readyForCalculate(ss));
            continue;
        }

        sc.close();

        System.out.println("Bye!");
    }

    public static String readyForCalculate(String ar) {
        String[] digitsLiterals = ar.split("[^A-Za-z0-9]+");
        String[] operations = ar.split("\\w+");
        String[] digits = new String[digitsLiterals.length];
        for (int i = 0; i < digits.length; i++) {
            try {
                Integer.valueOf(digitsLiterals[i]);
                digits[i] = digitsLiterals[i];
            } catch (NumberFormatException nfe) {
                if (variable.get(digitsLiterals[i]) == null) {
                    return null;
                } else {
                    digits[i] = variable.get(digitsLiterals[i]);
                }
            }
        }
        // for postfix operations
        String[] result = makeResult(digits, operations);
        Deque<String> postfix = makePostfix(result);
        String value = String.valueOf(calculate(postfix));
        return value;
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
            if (sb.isEmpty()) {
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
