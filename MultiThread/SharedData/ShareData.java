package MultiThread.SharedData;

public class ShareData {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        // expected behavior one thread, then another
        Counter counter = new Counter();
        Thread t1 = new MyThread(counter);
        Thread t2 = new MyThread(counter);

        t1.start();
        t1.join();

        t2.start();
        t2.join();
        // we would have in all cases 2, becuase the first one thread, then the second
        System.out.println(counter.getCount());

        // initial the value of counter
        counter.count = 0;

        Thread t3 = new MyThread(counter);
        t3.start();
        Thread t4 = new MyThread(counter);
        t4.start();
        // here could be any value fron 0 to 2, because increment isn't atomic
        // operation: consists of three action: read value, increment it, write value
        // back;
        System.out.println(counter.getCount());
    }

}

class Counter {

    public int count = 0;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }

}

class MyThread extends Thread {

    private final Counter counter;

    public MyThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.increment();

    }
}
