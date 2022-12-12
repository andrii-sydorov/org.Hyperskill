package MultiThread.CustomThread;

import java.util.Scanner;

/**
 * String processor
 * Write a class with the name StringProcessor that extends the Thread class.
 * The class must have a method that reads strings (line by line) from the
 * standard input. If a read string has a character in lower case, the processor
 * must output the string in the upper case; otherwise, the processor must
 * output "FINISHED" and stop the processing.
 * 
 * Use the provided template for your class. Write any additional methods if you
 * need them.
 * 
 * The testing system will start the processor as a regular thread.
 * 
 * Sample Input:
 * line
 * LINE
 * 
 * Sample Output:
 * LINE
 * FINISHED
 * 
 * 
 * Sample Input:
 * threadS
 * PROCeSSES
 * TASKS
 * 
 * Sample Output:
 * THREADS
 * PROCESSES
 * FINISHED
 * 
 * @author SMD_ASY
 *
 */

public class Processor {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Thread t = new StringProcessor();
        t.start();
    }

}

class StringProcessor extends Thread {

    final Scanner sc = new Scanner(System.in);

    @Override
    public void run() {
        while (true) {
            String s = sc.nextLine();
            if (s.toUpperCase().equals(s)) {
                sc.close();
                break;
            }
            System.out.println(s.toUpperCase());
        }
        System.out.println("FINISHED");
    }

}
