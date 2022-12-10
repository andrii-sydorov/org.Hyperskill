package ThreadManagement;

/**
 * Ordering the execution of threads
 * 
 * Implement a method that takes three objects (instances of Thread or its
 * subclasses). The method must start passed objects as threads in a way that
 * the order of their execution goes like this: t3, t2, t1. These threads print
 * secret phrases to the standard output, their output must always be the same.
 * 
 * All given threads must be terminated before the implemented method is
 * completed.
 * 
 * Otherwise, the testing system will give you some hints on throwing exceptions
 * in the main thread, for example:
 * 
 * Exception in thread "main" java.lang.RuntimeException: All threads must be
 * terminated before ending the implemented method
 * 
 * @author SMD_ASY
 *
 */

public class ThreadOrdering {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        Thread t1 = new myThread("first thread");
        Thread t2 = new myThread("second thread");
        Thread t3 = new myThread("third thread");
        threadOrder(t1, t2, t3);
    }

    public static void threadOrder(Thread... t) throws InterruptedException {
        for (Thread ts : t) {
            ts.start();
            ts.join();
        }
    }

}

class myThread extends Thread {

    String threadName;

    public myThread(String threadName) {
        super(threadName);
    }

    @Override
    public void run() {
        System.out.println("Hello from " + Thread.currentThread().getName());
    }
}
