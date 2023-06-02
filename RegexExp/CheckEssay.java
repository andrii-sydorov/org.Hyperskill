package RegexExp;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * any idea how to solves this shit!
 * @author SMD_ASY
 *
 */

public class CheckEssay {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        sc.close();
        Pattern p = Pattern.compile("The\\s+key\\s+is\\s+(([a-z0-9&&[^aeiou]]+|[?!#aeiou]+)[.!? ]{2,})", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        while(m.find()) {
            System.out.println(m.group(1).trim());
        }
    }

}
