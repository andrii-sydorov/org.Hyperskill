package CustomThread;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Writing a thread for printing numbers
 * Write a class named NumbersThread that extends the Thread class. The class
 * must have a constructor that takes two integer numbers from and to as range
 * borders.
 * 
 * Implement the method run. It must print all numbers from the given range
 * (inclusive) to the standard output.
 * 
 * Use the provided template.
 * 
 * Report a typo
 * Sample Input 1:
 * 
 * 1 3
 * 
 * Sample Output 1:
 * 
 * 1
 * 2
 * 3
 * 
 * Sample Input 2:
 * 
 * 2 2
 * 
 * Sample Output 2:
 * 
 * 2
 * 
 * @author SMD_ASY
 *
 */

public class PrintNumbers {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int[] limit = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::valueOf).toArray();
        Thread t = new NumbersThread(limit[0], limit[1]);
        sc.close();
        t.start();
    }

}

class NumbersThread extends Thread {

    private int from;
    private int to;

    public NumbersThread(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        for (int i = from; i <= to; i++) {
            System.out.println(i);
        }
    }
}
