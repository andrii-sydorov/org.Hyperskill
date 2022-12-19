package MultiThread.ThreadSynchronization;

public class SynchronizedCounter {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        Counter counter = new Counter();
        Thread t1 = new ThreadCounter(counter);
        Thread t2 = new ThreadCounter(counter);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter.getCount());
    }

}

class ThreadCounter extends Thread {

    Counter counter;

    public ThreadCounter(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            counter.incrementt();
        }
    }

}

class Counter {

    private int count;

    public synchronized void incrementt() {
        count++;
    }

    /**
     * If we have a guarantee that the reading thread successfully returns from join
     * on all writing threads when it reads a field. That's true about the code
     * above and we can remove the synchronized keyword from the declaration of
     * getValue.
     * If a shared field is declared with the volatile keyword. In that case, we
     * will always see the actual value of this field.
     * Be extra careful when you decide not t
     * 
     * @return
     */
    public synchronized int getCount() {
        return this.count;
    }
}