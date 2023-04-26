package RegexExp;

import java.util.Scanner;

/**
 * Safe password
 * The password is hard to crack if it contains at least one uppercase letter,
 * at least one lowercase letter, at least one digit and includes 12 or more
 * symbols. For a given string you should output "YES" if this password is hard
 * to crack, otherwise output "NO".
 * 
 * Hint
 * 
 * Sample Input 1:
 * Password1234
 * Sample Output 1:
 * YES
 * 
 * Sample Input 2:
 * SuperSecretPass
 * Sample Output 2:
 * NO
 * 
 * @author SMD_ASY
 *
 */

public class SafePaasword {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        // start coding here
        String data = scanner.nextLine();
        String lowerCase = ".*[a-z]+.*";
        String upperCase = ".*[A-Z]+.*";
        String digits = ".*[0-9]+.*";
        // the best solution is under
        //final String regex = "(?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{12,}";
        scanner.close();
        System.out.println(data.matches(lowerCase) & data.matches(upperCase) & data.matches(digits) & data.length() >= 12? "YES" : "NO");
    }

}
