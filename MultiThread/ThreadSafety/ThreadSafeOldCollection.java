package MultiThread.ThreadSafety;

import java.util.List;
import java.util.Stack;

public class ThreadSafeOldCollection {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        List<Integer> numbers = createSynchronizedList();
        Thread t1 = new Thread(() -> {
            addNumbers(numbers);
        });
        Thread t2 = new Thread(() -> {
            addNumbers(numbers);
        });
        
        Thread t3 = new Thread(() -> {
           for (Integer i : numbers) {
               
           }
        });
        
        t1.start();
        t2.start();
        addNumbers(numbers);
        //t3.start(); thread t3 will produce ConcurrentModificationException
        t1.join();
        t2.join();
        //t3.join();
        System.out.println(numbers.size()); // allways 30000 while we are using threadsafe collections
        
    }

    private static void addNumbers(List<Integer> ls) {
        for (int i = 0; i < 10000; i++) {
            ls.add(i);
        }
    }

    private static List<Integer> createSynchronizedList() {
        return new Stack<Integer>();
        // could be Vector, Hashtable
    }

}
