package Pseudocode;

import java.util.Scanner;

public class Penguins {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int k = Integer.parseInt(sc.nextLine());
		sc.close();
		int[] a = { 10, 9, 13, 1, 13, 2, 8, 5, 7, 1, 20, 6, 4, 18, 19, 17, 5, 19, 9, 18 };
		System.out.println(peng(k, a));
		int[] b = {677,591,153,356,617,337,195,948,440,657,631,546,148,678};
		System.out.println(findMinimumOddElement(b));
		int n = 10;
		System.out.println(function(n));
		
	}

	private static String peng(int k, int[] a) {
		StringBuilder sb = new StringBuilder();
		boolean f = false;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == k) {
				f = true;
			}
		}
		if (f) {
			sb.append("quack");
		} else {
			sb.append("wave");
		}
		return sb.toString();
	}
	
	private static int findMinimumOddElement(int[] b) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i< b.length; i++) {
			if (b[i] % 2 == 0 && b[i] < min) {
				min = b[i];
			}
		}
		return min;
	}
	
	private static int function(int n) {
		int x = 0;
		int a = 1;
		int b = 1;
		for (int j = 2; j <= n -1; j++) {
			x = a + b;
			a = b;
			b = x;
		}
		return x;
	}

}
