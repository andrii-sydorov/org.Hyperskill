package AnonymousClasses;

import java.util.Scanner;

/**
 * Capture variables 
 * 
 * here is an interface Returner:
 * 
 * interface Returner {
 * 
 * 		public String returnString();
 * 
 * 		public int returnInt(); 
 * } 
 * 
 * You should create an anonymous class that
 * implements the interface and assign the instance to the variable returner.
 * The anonymous class must override both methods of the interface. The method
 * returnString should capture the string variable str from the context and
 * return it, the second method should capture the integer variable number from
 * the context and return it. These variables will be accessible during testing.
 * 
 * @author SMD_ASY
 *
 */

public class CaptureVariable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		int number = Integer.valueOf(sc.nextLine());
		Returner returner = new Returner() {
			@Override
			public String returnString() {
				return str;
			}

			@Override
			public int returnInt() {
				return number;
			}
		};
		System.out.println(returner.returnString());
		System.out.println(returner.returnInt());
		sc.close();
	}

}

interface Returner {
	public String returnString();

	public int returnInt();
}
