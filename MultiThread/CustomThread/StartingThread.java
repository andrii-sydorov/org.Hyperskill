package MultiThread.CustomThread;

/**
 * Starting threads
 * You have a class that implements the Runnable interface.
 * 
 * class RunnableWorker implements Runnable {
 * 
 * @Override
 *  public void run() {
 *  // the method does something
 *  }
 * }
 * 
 * 1) Create three threads using instances of the RunnableWorker. Set
 * names to these threads like "worker-X", where X is any suffix. Use
 * Thread(Runnable target, String name) constructor to pass a thread
 * name on thread creation.
 * 
 * 2) Start all created threads. The method run of each instance must
 * be executed in a new thread.
 * 
 * Note: you don't need to rewrite the given class; it will be added
 * to your solution automatically.
 * @author SMD_ASY
 *
 */

public class StartingThread {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Thread t1 = new Thread(new RunnableWorker(), "worker-X");
        Thread t2 = new Thread(new RunnableWorker(), "worker-Y");
        Thread t3 = new Thread(new RunnableWorker(), "worker-Z");
        t1.start();
        t2.start();
        t3.start();
    }

}

class RunnableWorker implements Runnable {

    @Override
    public void run() {
        // TODO Auto-generated method stub
        final String name = Thread.currentThread().getName();

        if (name.startsWith("worker-")) {
            System.out.println("to hard calculations ....");
        } else {
            return;
        }
    }

}
