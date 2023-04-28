package RegexExp;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RemoveComments {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        String codeWithComments = scanner.nextLine();
        String regex = "(/[\\*].*?\\*/)|(//.+$)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(codeWithComments);
        System.out.println(m.replaceAll(""));
        scanner.close();
    }

}
