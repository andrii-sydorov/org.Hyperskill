package MultiThread.CustomThread;

/**
 * Multiple threads
 * Here's a class that extends the Thread class.
 * 
 * class WorkerThread extends Thread {
 * 
 *  @Override
 *  public void run() {
 *  // the method does something
 *  }
 * }
 * 
 * 1) Create two instances of the given class and set the names like
 * "worker-X", where X is any suffix (use the constructor to set
 * name).
 * 
 * 2) Start created threads. The method run of each instance must be
 * executed in a new thread.
 * 
 * Note: the given class will be added to your solution automatically.
 * @author SMD_ASY
 *
 */

public class MultipleThread {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Thread t1 = new WorkerThread("worker- X");
        Thread t2 = new WorkerThread("worker- Y");
        t1.start();
        t2.start();
    }

}

class WorkerThread extends Thread {

    private final static int NUMBER_OF_LINES = 3;

    public WorkerThread(String name) {
        super(name);
    }

    @Override
    public void run() {

        final String name = Thread.currentThread().getName();

        if (!name.startsWith("worker-")) {
            return;
        }

        for (int i = 0; i < NUMBER_OF_LINES; i++) {
            System.out.println(name + " do something ..");
        }
    }
}
