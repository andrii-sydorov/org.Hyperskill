package MultiThread.ThreadSynchronization;

public class GraineSynchonization {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SomeClass sc = new SomeClass();
        Thread t1 = new ThreadFirstMethod(sc);
        Thread t2 = new ThreadSecondMethod(sc);
        t1.start();
        t2.start();
    }

}

/**
 * Sometimes a class has several fields that are never used together. It's
 * possible to protect these fields by using the same monitor, but in this case,
 * only one thread will be able to access one of these fields, despite their
 * independence. To improve the concurrency rate it's possible to use an idiom
 * with additional objects as monitors.
 * 
 * @author SMD_ASY
 *
 */

class SomeClass {

    private int numberOfCallingMethod1 = 0;
    private int numberOfCallingMethod2 = 0;

    Object lock1 = new Object();
    Object lock2 = new Object();

    public void firstMethod() {
        System.out.println("method 1.....");
        synchronized (lock1) {
            numberOfCallingMethod1++;
        }
    }

    public void secondMethod() {
        System.out.println("method 2.....");
        synchronized (lock2) {
            numberOfCallingMethod2++;
        }
    }

    public int getNumberOfCallingMethod1() {
        return numberOfCallingMethod1;
    }

    public int getNumberOfCallingMethod2() {
        return numberOfCallingMethod2;
    }

}

class ThreadFirstMethod extends Thread {

    SomeClass sc;

    public ThreadFirstMethod(SomeClass sc) {
        this.sc = sc;
    }

    @Override
    public void run() {

        sc.firstMethod();

    }
}

class ThreadSecondMethod extends Thread {

    SomeClass sc;

    public ThreadSecondMethod(SomeClass sc) {
        this.sc = sc;
    }

    @Override
    public void run() {

        sc.secondMethod();

    }
}
