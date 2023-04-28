package RegexExp;

import java.util.Scanner;

/**
 * Remove HTML tags
 * For a given string you should remove all HTML tags from it. An HTML tag
 * starts with the symbol "<" and ends with the symbol ">".
 * 
 * You should output the string without HTML tags.
 * 
 * Sample Input 1:
 * <h1>Simple header</h1>
 * Sample Output 1:
 * Simple header
 * 
 * Sample Input 2:
 * <h2>Header with <b>bold</b> text</h2>
 * Sample Output 2:
 * Header with bold text
 * 
 * @author SMD_ASY
 *
 */

public class RemoveHTMLTags {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        String stringWithHtmlTags = scanner.nextLine();
        String regex = "</?.*?>";
        System.out.println(stringWithHtmlTags.replaceAll(regex, ""));
        scanner.close();
    }

}
