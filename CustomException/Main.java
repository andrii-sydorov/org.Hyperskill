package CustomException;

import java.util.Scanner;

/**
 * Checked exception with constructor
 * Write a program that creates a checked exception named CustomException and
 * has a constructor with an Exception argument.
 * 
 * Sample Input 1:
 * 
 * smth went wrong
 * Sample Output 1:
 * 
 * java.lang.Exception: smth went wrong
 * 
 * @author SMD_ASY
 *
 */

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        Exception e = new Exception(sc.nextLine());

        Exception customException = new CustomException(e);

        System.out.println(customException.getMessage());
    }

}

class CustomException extends Exception {

//    public CustomException(Exception e) {
//        super(e.getClass().getName() + ": " + e.getMessage());
//    }
    
    CustomException(Exception e) {
        super(e);
    }
}
