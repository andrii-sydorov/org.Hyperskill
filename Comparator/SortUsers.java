package Comparator;

import java.util.Comparator;

/**
 * Sort users 
 * 
 * 
 * We have the User class shown in the following code snippet
 * and we want to sort users by their names in the lexicographic order.
 * 
 * class User { 
 * 
 * 	private String name;
 * 
 * 	public User(String name) { 
 * 		this.name = name; 
 * 	}
 * 
 * 	public String getName() { 
 * 		return name; 
 * 	}
 * 
 * 	@Override 
 * 	public String toString() { 
 * 		return name; 
 * 	} 
 * } 
 * 
 * Implement UserComparator that we will use to sort users. 
 * You don't need to read or write anything from or to the console, 
 * just implement the method.
 * 
 * Sample Input 1:
 * 
 * Mike Ginger Alice Bob 
 * 
 * Sample Output 1:
 * 
 * Alice Bob Ginger Mike 
 *
 * Sample Input 2:
 * 
 * microprogrammer Moses Chloe user 
 * 
 * Sample Output 2:
 * 
 * Chloe Moses microprogrammer user
 * 
 * @author SMD_ASY
 *
 */

public class SortUsers {
	public static void main(String[] args) {

	}
}

class UserToSort {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;

	public UserToSort(String name) {
		this.name = name;
	}
}

class UserComaparator implements Comparator<User> {

	@Override
	public int compare(User o1, User o2) {
		String u1 = o1.getName();
		String u2 = o2.getName();
		return u1.compareTo(u2);
	}

}