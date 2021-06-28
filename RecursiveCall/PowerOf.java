package RecursiveCall;

/**
 * It calculates b raised to the a-th power.
 */

import java.util.Scanner;

public class PowerOf {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the power of number:");
        int a = sc.nextInt();
        System.out.println("Enter the base number:");
        int b = sc.nextInt();
        sc.close();
        System.out.println(powerOfRecursion(b, a));
        System.out.println(powerOfLoops(b, a));
        System.out.println(powerOfRecursion(b, a) == powerOfLoops(b, a));
    }

    public static int powerOfRecursion(int b, int a) {
        if(a == 0) {
            return 1;
        } else if( a == 1) {
            return b;
        } else {
            return b * powerOfRecursion(b, a - 1); 
        }
    }

    public static int powerOfLoops(int b, int a) {
        int ans = 1;
        for(int i = 1; i <= a; i++) {
            ans *= b;
        }
        return ans;
    }
}
