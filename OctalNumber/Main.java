package OctalNumber;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your's number:");
		int n = sc.nextInt();
		System.out.println(decToOct(n));
		Integer a = Integer.valueOf(n);
		System.out.println(decToOct(n).equals(Integer.toOctalString(n)));
	}

	public static String decToOct(int n) {
		String ans = "";
		while(true) {
			if(n < 8) {
				ans = n % 8 + ans;
				break;
			} else {
				ans = n % 8 + ans;
				n /= 8;
			}
		}
		return ans;
	}
	
	//TODO Convert the octal number 305683056_830568​ to the binary system. If necessary, use the table from the topic. Write your answer below:

}
