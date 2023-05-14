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
        while (!isRunning) {
            String data = sc.nextLine();
            if (data.isEmpty()) {
                continue;
            }
            // checking the menu
            Pattern p = Pattern.compile("/.+");
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
            p = Pattern.compile(".+[a-zA-Z0-9]+[+-*/^]+");
            m = p.matcher(data);
            if (m.matches()) {
                System.out.println("Invalid expression");
                continue;
            }
            // shouldn't have multiple multiplication or divide signs
            p = Pattern.compile("[*/]{2,}");
            m = p.matcher(data);
            if (m.find()) {
                System.out.println("Invalid expression");
                continue;
            }
            // should have the correct sequence of brackets
            if (!checkBrackets(data)) {
                System.out.println("Invalid expression");
                continue;
            }
            // digits shouldn't follow after literal in left parts of equations
            p = Pattern.compile(".+\\w+\\d+.*=?|");
            m = p.matcher(data);
            if (m.find()) {
                System.out.println("Invalid identifier");
                continue;
            }
            // right part of equation also shouldn't have digits after literals
            p = Pattern.compile("=.*\\w+\\d+.*");
            m = p.matcher(data);
            if (m.find()) {
                System.out.println("Invalid assignment");
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
            
        }

        sc.close();
        
        List<String> ls = Arrays.stream(data.split("\\s+")).collect(Collectors.toList());
        // ls.forEach(x -> System.out.println(x));
        Deque<String> postfix = makePostfix(ls);
        System.out.println(postfix);
        int result = calculate(postfix);
        System.out.println(result);
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

    public static Deque<String> makePostfix(List<String> ls) {
        Deque<String> postfix = new ArrayDeque<String>();
        Stack<String> operations = new Stack<String>();
        for (int i = 0; i < ls.size(); i++) {
            String data = ls.get(i);
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
