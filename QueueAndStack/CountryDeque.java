package QueueAndStack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Deque<String> states = new ArrayDeque<String>();
 * 
 * states.add("Germany"); 
 * states.add("France"); 
 * states.push("UK");
 * states.offerLast("Norway");
 * 
 * String sPop = states.pop(); 
 * String sPeek = states.peek(); 
 * String sPeekLast = states.peekLast(); 
 * states.offer(sPop); 
 * String sPollLast = states.pollLast();
 * 
 * while (states.peek() != null) { 
 * System.out.print(states.pop()); 
 * }
 * 
 * @author SMD_ASY
 *
 */

public class CountryDeque {
	public static void main(String[] args) {
		Deque<String> country = new ArrayDeque<>();
		country.add("Germany");
		country.add("France");
		country.push("UK");
		country.offerLast("Norway");

		String sPop = country.pop();
		String sPeek = country.peek();
		String sPeekLast = country.peekLast();
		country.offer(sPop);
		String sPollLast = country.pollLast();

		while (country.peek() != null) {
			System.out.println(country.pop());
		}
	}
}
