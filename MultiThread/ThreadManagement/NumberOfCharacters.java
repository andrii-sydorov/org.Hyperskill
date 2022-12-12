package MultiThread.ThreadManagement;

import java.util.Scanner;
import java.util.Arrays;

/**
 * Calculate the number of distinct characters
 * 
 * Below is the code that reads a string from the standard input and calculates
 * the number of distinct characters in the string. For some reason, the
 * developer Rebecca decided to perform calculations in a new thread. Sadly, her
 * solution doesn't work correctly. Try to fix it.
 * 
 * Additional explanations: the implemented class SlowStringProcessor extends
 * Thread and overrides the method run. It calculates the number of distinct
 * characters passed to the constructor. The class has a getter
 * getNumberOfUniqueCharacters() that returns the calculated number or 0 if the
 * thread has not calculated the number yet. Keep in mind that
 * SlowStringProcessor works quite slow.
 * 
 * Note: you don't need to write SlowStringProcessor. It will be added to your
 * solution automatically.
 * 
 * Sample Input 1:
 * 
 * multithreading
 * 
 * Sample Output 1:
 * 
 * 12
 * 
 * @author SMD_ASY
 *
 */

public class NumberOfCharacters {

    private static long mainThreadId = Thread.currentThread().getId();

    // Fix this method
    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        SlowStringProcessor processor = new SlowStringProcessor(str);
        processor.start();
        processor.join();
        scanner.close();
        System.out.println(processor.getNumberOfUniqueCharacters());
    }

    // Don't change the code below
    static class SlowStringProcessor extends Thread {

        private final String s;
        private volatile long numberOfUniqueCharacters = 0;

        public SlowStringProcessor(String s) {
            this.s = s;
        }

        @Override
        public void run() {

            final long currentId = Thread.currentThread().getId();

            if (currentId == mainThreadId) {
                throw new RuntimeException("You must start a new thread!");
            }

            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                throw new RuntimeException("Do not interrupt the processor", e);
            }

            this.numberOfUniqueCharacters = Arrays.stream(s.split("")).distinct().count();
        }

        public long getNumberOfUniqueCharacters() {
            return numberOfUniqueCharacters;
        }
    }
}
