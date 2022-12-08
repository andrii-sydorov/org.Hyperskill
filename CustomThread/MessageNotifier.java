package CustomThread;

/**
 * Message notifier as a thread
 * 
 * Write a service that takes a message and the number of its repetitions as
 * constructor parameters and prints the message in the standard output the
 * specified number of times.
 * 
 * Use the provided template for your class.
 * 
 * The testing system will start the service as a regular thread.
 * 
 * Report a typo
 * 
 * Sample Input 1:
 * 
 * Hello, Mary
 * 3
 * 
 * Sample Output 1:
 * 
 * Hello, Mary
 * Hello, Mary
 * Hello, Mary
 * 
 * @author SMD_ASY
 *
 */

public class MessageNotifier {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Thread t = new MessageNote(3, "Hello, Mary");
        t.start();
    }

}

class MessageNote extends Thread {

    private int repeats;
    private String message;

    MessageNote(int repeats, String message) {
        this.repeats = repeats;
        this.message = message;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.repeats; i++) {
            System.out.println(this.message);
        }
    }
}
