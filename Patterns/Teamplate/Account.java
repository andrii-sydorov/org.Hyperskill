package Patterns.Teamplate;

import java.util.HashMap;
import java.util.Map;

public class Account {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "Verify your identity\r\n" + "Your standard account identification number: ST-01\r\n"
				+ "Thank you for creating a new customer account!\r\n" + "You received 50 Gems";
		String s1 = "Verify your identity\r\n" + "Your standad account identification number: ST-01\r\n"
				+ "Thank you for creating a new customer account!\r\n" + "You received 50 Gems";
		System.out.println(s.equals(s1));
		System.out.println(checkString(s, s1));
	}

	public static String checkString(String s, String s1) {
		StringBuilder sb = new StringBuilder();
		String[] sen1 = s.split("\\s+");
		String[] sen2 = s.split("\\s+");
		for (int j = 0; j < Math.max(sen1.length, sen2.length); j++) {
			for (int i = 0; i < Math.max(sen1[j].length(), sen2[j].length()); i++) {
				try {
					if (s.charAt(i) != s1.charAt(i)) {
						sb.append("{" + Character.toString(s.charAt(i)) + " " + i + "; ");
						sb.append(Character.toString(s1.charAt(i)) + " " + i + "}");
						sb.append("\n");
					}
				} catch (ArrayIndexOutOfBoundsException nfe) {
					sb.append("Exception!");
				}
			}
		}
		return sb.toString();
	}

}
