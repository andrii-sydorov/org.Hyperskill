package MultiThread.ThreadManagement;

import java.util.Scanner;

/**
 * Summing with threads
 * The following code is supposed to find the general sum of two inclusive
 * integer ranges in parallel. Unfortunately, this code doesn't work correctly.
 * Try and fix it. Read the comments for a better understanding.
 * 
 * Additional explanations: the implemented class RangeSummator extends Thread
 * and overrides the method run. It sums the range passed to the constructor
 * (left and right limits as integers). The class has a getter getResult() that
 * returns the calculated sum or 0 if the thread has not calculated the sum yet.
 * 
 * Note: you do not need to write the RangeSummator class. It will be added to
 * your solution automatically.
 * 
 * Sample Input 1:
 * 
 * 1 5
 * 2 4
 * 
 * Sample Output 1:
 * 
 * 24
 * 
 * @author SMD_ASY
 *
 */

public class SumWithThread {

    private static long mainThreadId = Thread.currentThread().getId();

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        int from1Incl = scanner.nextInt(); // left limit of the first range
        int to1Incl = scanner.nextInt(); // right limit of the first range

        int from2Incl = scanner.nextInt(); // left limit of the second range
        int to2Incl = scanner.nextInt(); // right limit of the second range

        scanner.close();

        RangeSummator summator1 = new RangeSummator(from1Incl, to1Incl); // first summator
        RangeSummator summator2 = new RangeSummator(from2Incl, to2Incl); // second summator

        summator1.start();
        summator2.start();
        summator1.join();
        summator2.join();

        long partialSum1 = summator1.getResult();
        long partialSum2 = summator2.getResult();

        long sum = partialSum1 + partialSum2; // the sum is 0, fix it!

        System.out.println(sum);
    }

    // Don't change the code below
    static class RangeSummator extends Thread {

        int fromIncl;
        int toIncl;

        private volatile long result = 0;

        public RangeSummator(int fromIncl, int toIncl) {
            this.fromIncl = fromIncl;
            this.toIncl = toIncl;
        }

        @Override
        public void run() {
            final long currentId = Thread.currentThread().getId();

            if (currentId == mainThreadId) {
                throw new RuntimeException("You must start a new thread!");
            }

            long sum = 0;
            for (int i = fromIncl; i <= toIncl; i++) {
                sum += i;
            }

            result = sum;
        }

        public long getResult() {
            return result;
        }
    }
}
