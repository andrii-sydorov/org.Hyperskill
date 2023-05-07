package RegexExp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Like a lion
 * 
 * Gabe N. works at a zoo and has a special keyboard that replaces all
 * occurrences of "lion" with "guinea pig". He decided to write a Java program
 * that would simulate this keyboard and replace all occurrences of the "lion"
 * substring in a user-entered string with the "guinea pig" substring. Help Gabe
 * N. write this program using regular expressions and Matcher. Implement the
 * replace method that takes a string, replaces all the words "lion" with
 * "guinea pig", and returns the result.
 * 
 * Sample input 1:
 * 
 * like a lion
 * Sample output 1:
 * 
 * like a guinea pig
 * Sample input 2:
 * 
 * In Africa, a lion runs across the savannah in search of prey.
 * Sample output 2:
 * 
 * In Africa, a guinea pig runs across the savannah in search of prey.
 * 
 * @author SMD_ASY
 *
 */

public class DayliTask {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // String s = "lionlionlion";
        String s = "In Africa, a lion runs across the savannah in search of prey.";
        String ans = s.replaceAll("lion", "my word");
        Pattern p = Pattern.compile("lion");
        Matcher m = p.matcher(s);
        System.out.println(m.replaceAll("my word"));
        System.out.println(ans);
    }

}
