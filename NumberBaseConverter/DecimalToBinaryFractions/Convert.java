package NumberBaseConverter.DecimalToBinaryFractions;

import java.util.Locale;
import java.util.Scanner;

public class Convert {
	
	private static final int base = 2;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter the number's:");
		Scanner sc = new Scanner(System.in).useLocale(Locale.US);
		float d = sc.nextFloat();
		sc.close();
		System.out.println(toBinaryConverter(d));
	}
	
	private static String toBinaryConverter(double d) {
		System.out.println("From double method!");
		String fractPart = convertFractPart(d);
		String wholePart = convertWholePart(d);
		return wholePart + "." + fractPart;
	}
	
//	private static String toBinaryConverter(float d) {
//		String fractPart = convertFractPart(d);
//		String wholePart = convertWholePart(d);
//		return wholePart + "." + fractPart;
//	}
	
	private static String convertWholePart(double d) {
		String wholePart = "";
		int whole = (int) d;
		while (true) {
			if (whole < base) {
				wholePart = whole % base + wholePart;
				break;
			}
			wholePart = whole % base + wholePart;
			whole /= base;
		}
		return wholePart;
	}

	private static String convertFractPart(double d) {
		String fractPart = "";
		double rest = d - (int) d;
		while (rest != 1) {
			rest *= base;
			fractPart += (int) rest;
			if (rest > 1) {
				rest -= 1;
			}
		}
		return fractPart;
	}

}
