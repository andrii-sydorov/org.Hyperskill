package RegexExp;

import java.util.Scanner;

/**
 * Check IP address
 * An IP address consists of four numbers from 0 to 255, inclusive, divided by
 * the dots. For example, 127.0.0.1 is a valid IP address, but 256.0.0.1 and
 * 127.0.1 are invalid IP addresses.
 * 
 * For a given string output "YES" if this string is a valid IP address,
 * otherwise output "NO".
 * 
 * Sample Input 1:
 * 127.0.0.1
 * Sample Output 1:
 * YES
 * 
 * Sample Input 2:
 * 256.0.0.1
 * Sample Output 2:
 * NO
 * 
 * Sample Input 3:
 * 127.0.1
 * Sample Output 3:
 * NO
 * 
 * Sample Input 4:
 * 192.168.123.231
 * Sample Output 4:
 * YES
 * 
 * Sample Input 5:
 * -1.0.0.1
 * Sample Output 5:
 * NO
 * 
 * Sample Input 6:
 * 127.0.0.1.
 * Sample Output 6:
 * NO
 * 
 * @author SMD_ASY
 *
 */

public class CheckIpAddress {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        String data = scanner.nextLine();
        String pattern = "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\." +
                "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\." +
                "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\." +
                "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])$";
        System.out.println(data.matches(pattern) ? "YES" : "NO");
        scanner.close();
    }

}
