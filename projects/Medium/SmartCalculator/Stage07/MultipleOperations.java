package projects.Medium.SmartCalculator.Stage07;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

public class MultipleOperations {

    public static Map<String, Integer> map = new HashMap<>();
    public static Map<Character, Character> brackets = new HashMap<>();

    static {
        map.put("+", 1);
        map.put("-", 1);
        map.put("*", 2);
        map.put("/", 2);
        map.put("^", 3);
        
        brackets.put(')','(');
        brackets.put('}', '{');
        brackets.put(']', '[');
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine();
        sc.close();
        if (!checkBrackets(data)) {
            System.out.println("Invalid expression");
            return;
        }
        List<String> ls = Arrays.stream(data.split("\\s+")).collect(Collectors.toList());
        //ls.forEach(x -> System.out.println(x));
        Deque<String> postfix = makePostfix(ls);
        System.out.println(postfix);
        int result = calculate(postfix);
        System.out.println(result);
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
                while(true) {
                    if (operations.isEmpty() || operations.peek().equals("(") || data.equals("(")) {
                        operations.push(data);
                        break;
                    }
                    if (data.equals(")")) {
                        while(!operations.peek().equals("(")) {
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
        while(!postfix.isEmpty()) {
            String s = postfix.pollFirst();
            try {
                int i = Integer.valueOf(s);
                digits.add(i);
            } catch (NumberFormatException nfe) {
                int second  = digits.pollLast();
                int first = digits.pollLast();
                switch(s) {
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
                        digits.push((int)Math.pow(first, second));
                }
            }
        }
        return digits.pop();
    }

}
