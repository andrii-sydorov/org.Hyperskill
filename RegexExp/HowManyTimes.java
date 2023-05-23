package RegexExp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * How many times it returns true
 * Given a fragment of a program with a regex:
 * 
 * String str = "Aaab 123 ab 787 abba 12ab 55AB";
 * Pattern pattern = Pattern.compile("[ab]+");
 * Matcher matcher = pattern.matcher(str);
 * 
 * How many times will the invocation of matcher.find() return true?
 * 
 * @author SMD_ASY
 *
 */

public class HowManyTimes {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String s = "Aaab 123 ab 787 abba 12ab 55AB";
        Pattern pattern = Pattern.compile("[ab]+");
        Matcher matcher = pattern.matcher(s);
        int count = 0;
        while (matcher.find()) {
            count++;
            System.out.println(matcher.group());
        }
        System.out.println(count);
    }

}
