package declaringAMethod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * The following method implements an algorithm to find the greatest common
 * divisor (GCD) of two positive numbers.
 * 
 * @author SMD_ASY
 *
 */

public class GCD {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter your numbers: ");
		try {
			System.out.println(gcd(Integer.valueOf(bf.readLine()), Integer.valueOf(bf.readLine())));
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		} finally {
			bf.close();
		}
	}

	public static int gcd(int a, int b) {
		while (b > 0) {
			int c = a % b;
			a = b;
			b = c;
		}
		return a;
	}

}
