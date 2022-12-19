package MultiThread.ThreadSynchronization;

public class StaticSynchronization {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        System.out.println("Synchronization on static method:");
        Thread t1 = new StThread();
        Thread t2 = new StThread();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        Thread.sleep(500);
        System.out.println();

        System.out.println("Synchronization in instancemethod of one class:");
        InstanceClass ic = new InstanceClass();
        Thread t3 = new ThreadInstance(ic);
        Thread t4 = new ThreadInstance(ic);
        t3.start();
        t4.start();
        t3.join();
        t4.join();
        Thread.sleep(500);
        System.out.println();

        System.out.println("Synchronization on instance method of many classes: ");
        for (int i = 0; i < 15; i++) {
            InstanceClass ics = new InstanceClass();
            Thread t5 = new ThreadInstance(ics);
            t5.start();
        }
    }

}

class StaticClass {

    /**
     * When we synchronize static methods using the synchronized keyword the monitor
     * is the class itself. Only one thread can execute the body of a synchronized
     * static method at the same time. This can be summarized as "one thread per
     * class".
     */
    public static synchronized void doSomething() {
        String name = Thread.currentThread().getName();
        System.out.printf("Thread %s has entered the method\n", name);
        System.out.printf("Thread %s has left the method\n", name);
    }
}

class StThread extends Thread {

    @Override
    public void run() {
        StaticClass.doSomething();
    }
}

class InstanceClass {

    /**
     * Instance methods are synchronized on the instance (object). The monitor is
     * the current object (this) that owns the method. If we have two instances of a
     * class, each instance has a monitor for synchronizing.
     * 
     * Only one thread can execute code in a synchronized instance method of a
     * particular instance. But different threads can execute methods of different
     * objects at the same time. This can be summarized as "one thread per
     * instance".
     */
    public synchronized void doSomething() {
        String name = Thread.currentThread().getName();
        System.out.printf("Thread %s has entered the method\n", name);
        System.out.printf("Thread %s has left the method\n", name);
    }

}

class ThreadInstance extends Thread {

    InstanceClass ic;

    public ThreadInstance(InstanceClass ic) {
        this.ic = ic;
    }

    @Override
    public void run() {
        ic.doSomething();
    }
}
