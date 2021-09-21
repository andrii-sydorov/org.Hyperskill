package NumberBaseConverter.ProcessingStrings;

import java.util.Scanner;

/**
 * Parse url 
 * You want to hack a website now. First, get all the available
 * parameters that you can find in the URL. Then print them in the "key : value"
 * format. If a parameter doesn't have value, print "not found".
 * 
 * If you find the password (parameter pass), you should print its value after
 * all parameters once again, but with a key password. If a URL does not contain
 * parameter pass, do not print anything after the listed parameters. However,
 * if pass parameter is present, its value cannot be empty.
 * 
 * Note: the order of parameters should be the same as in the URL. Advice: Check
 * examples for better understanding and carefully learn the structure of the
 * URL.
 * 
 * 
 * Sample Input:
 * https://target.com/index.html?pass=12345&port=8080&cookie=&host=localhost
 * 
 * Sample Output: 
 * pass : 12345 
 * port : 8080 
 * cookie : not found 
 * host : localhost
 * password : 12345
 * 
 * 
 * Sample Input: 
 * https://target.com/index.html?port=8080&cookie=&host=localhost
 * 
 * Sample Output: 
 * port : 8080 
 * cookie : not found 
 * host : localhost
 * 
 * @author SMD_ASY
 *
 */

public class ParseUrl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		String data = line.substring(line.indexOf("?") + 1);
		String[] result = parseUrl(data);
		for (String s : result) {
			System.out.println(s);
		}
		sc.close();
	}

	private static String[] parseUrl(String data) {
		String[] keyValue = data.split("&");
		int length = data.contains("pass") ? keyValue.length + 1 : keyValue.length;
		String[] ans = new String[length];
		for (int i = 0; i < keyValue.length; i++) {
			String[] t = keyValue[i].split("=");
			if (t.length != 2) {
				ans[i] = t[0] + " : " + "not found";
			} else {
				ans[i] = t[0] + " : " + t[1];
			}
			if (t[0].equals("pass")) {
				if (t.length == 2) {
					ans[length - 1] = "password : " + t[1];
				} else {
					ans[length - 1] = "password : " + "not found";
				}
			}
		}
		return ans;
	}

}
