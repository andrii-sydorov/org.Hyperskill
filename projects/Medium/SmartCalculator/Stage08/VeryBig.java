package projects.Medium.SmartCalculator.Stage08;

import java.math.BigInteger;
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

public class VeryBig {

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
                                "The program calculates the sum, difference, multiplication and divide of very big numbers");
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
            // two operands shouldn't have a space between operands
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
                    new BigInteger(value);
                    variable.put(key, value);
                } catch (NullPointerException | NumberFormatException nfe) {
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
                        // try to create instance of BigInteger, when success add it to List, when not
                        // it's operation or value from map, checked later
                        new BigInteger(ss);
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
                new BigInteger(ss);
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
                new BigInteger(data);
                postfix.add(data);
            } catch (NumberFormatException nfe) {

//                if (m.matches() && (postfix.isEmpty() || operations.peek().equals("("))) {
//                    postfix.add("0");
//                }
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

    public static BigInteger calculate(Deque<String> postfix) {
        Deque<BigInteger> digits = new ArrayDeque<>();
        while (!postfix.isEmpty()) {
            String s = postfix.pollFirst();
            try {
                BigInteger bi = new BigInteger(s);
                digits.add(bi);
            } catch (NumberFormatException nfe) {
                BigInteger second = digits.pollLast();
                BigInteger first = digits.pollLast();
                switch (s) {
                    case "+":
                        digits.add(first.add(second));
                        break;
                    case "-":
                        digits.add(first.subtract(second));
                        break;
                    case "*":
                        digits.add(first.multiply(second));
                        break;
                    case "/":
                        digits.add(first.divide(second));
                        break;
                    case "^":
                        digits.push(first.pow(second.intValue()));
                }
            }
        }
        return digits.pop();
    }
}
