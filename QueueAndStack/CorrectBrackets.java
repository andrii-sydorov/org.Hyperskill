package QueueAndStack;

import java.util.*;

/**
 * Which brackets are balanced
 * 
 * You're dealing with a string consisting of brackets. Write a program to
 * examine whether the pairs of "{", "}", "(", ")", "[", "]" are correct or
 * balanced. This means that each opening bracket must have a corresponding
 * closing one (and vice versa) and they must go in the correct order.
 * 
 * For example, the program should print true for the string [()]{}{[()()]()}
 * and false for ()[]}.
 * 
 * The classic algorithm for solving this problem relies on using a stack.
 * 
 * 1. create an instance of a stack; 
 * 2. traverse the input string; 
 *   1. if the current character is a starting bracket "(" or "{" or "[" then push it to the stack;
 *   2. if the current is a closing bracket ")" or "}" or "]" then remove (pop) the
 * top element from the stack. If the popped bracket does not match the starting
 * bracket then parentheses are not balanced; 
 * 3. if there are some starting brackets left in the stack after completing traversal, then the parentheses
 * are not balanced.
 * 
 * Report a typo
 * 
 * Sample Input 1:
 * 
 * ([][])
 * 
 * Sample Output 1:
 * 
 * true
 * 
 * Sample Input 2:
 * 
 * ([](){([])})
 * 
 * Sample Output 2:
 * 
 * true
 * 
 * Sample Input 3:
 * 
 * {{[()]]
 * 
 * Sample Output 3:
 * 
 * false
 * 
 * @author SMD_ASY
 *
 */

public class CorrectBrackets {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		sc.close();
		System.out.println(correctBrackets(str));
		System.out.println(isBalanced(str));
	}

	private static boolean correctBrackets(String str) {
		Map<Character, Character> map = new HashMap<>();
		map.put('}', '{');
		map.put(']', '[');
		map.put(')', '(');
		Deque<Character> d = new ArrayDeque<>();
		for (int i = 0; i < str.length(); i++) {
			if (map.containsValue(str.charAt(i))) {
				d.push(str.charAt(i));
			} else if (map.containsKey(str.charAt(i))) {
				if (d.isEmpty() || d.pop() != map.get(str.charAt(i))) {
					return false;
				}
			}
		}
		return d.isEmpty();
	}
	
    public static boolean isBalanced(String s) {
        int x = s.length();
        s = s.replaceAll("\\(\\)|\\[]|\\{}", "");
        return s.length() != x && (s.length() == 0 || isBalanced(s));
    }

}
