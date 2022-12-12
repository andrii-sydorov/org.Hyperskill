package MultiThread.CustomThread;

import java.util.Scanner;

/**
 * Numbers filter
 * Write a class with the name NumbersFilter that extends the Thread class and
 * overrides method run. It should read integer numbers from the standard input
 * (line by line). If the number is even, the worker must print it to the
 * standard output (each number on a new line), if a number is 0, the worker
 * must stop.
 * 
 * Use the provided template for your class.
 * 
 * 
 * The testing system will start your class as a regular thread.
 * 
 * 
 * Sample Input:
 * 1
 * 2
 * 3
 * 4
 * 0
 * 
 * Sample Output:
 * 2
 * 
 * @author SMD_ASY
 *
 */

public class Filter {

    public static void main(String[] args) {
        Thread t = new NumbersFilter();
        t.start();
    }
}

class NumbersFilter extends Thread {

    private static final Scanner sc = new Scanner(System.in);

    @Override
    public void run() {
        int t = Integer.valueOf(sc.nextLine());
        while (t != 0) {
            if (t % 2 == 0) {
                System.out.println(t);
                ;
            }
            t = Integer.valueOf(sc.nextLine());
        }
        sc.close();
    }
}
