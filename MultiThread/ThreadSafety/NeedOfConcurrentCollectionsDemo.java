package MultiThread.ThreadSafety;

import java.util.ArrayList;
import java.util.List;

public class NeedOfConcurrentCollectionsDemo {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        List<Integer> numbers = new ArrayList<>();
        Thread t = new Thread(() -> {
            addNumbers(numbers);
        });

        t.start(); // add numbers from the additional thread
        addNumbers(numbers); // add number from the main thread
        //deleteNumbers(numbers);
        t.join(); // wait for writer thread
        
        System.out.println(numbers.size()); // the result can be any
    }

    private static void addNumbers(List<Integer> ls) {
        for (int i = 0; i < 10_000; i++) {
            ls.add(i);
        }
    }

    private static void deleteNumbers(List<Integer> ls) {
        for (Integer i : ls) {
            if (i % 11 == 0) {
                ls.remove(i);
            }
        }
    }

}
