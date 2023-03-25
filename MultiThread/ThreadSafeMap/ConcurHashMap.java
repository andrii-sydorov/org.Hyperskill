package MultiThread.ThreadSafeMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurHashMap {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        Map<Integer, String> map = new ConcurrentHashMap<>();
        Thread writer1 = new Thread(() -> {
            addPositiveNumbers(map);
        });
        Thread writer2 = new Thread(() -> {
            addNegativeNumbers(map);
        });

        writer1.start();
        writer2.start();

        // Here, in the main thread, we can use Iterator, retrieve values or update the
        // map

        writer1.join(); // wait for writer1 thread
        writer2.join(); // wait for writer2 thread
        System.out.println(map.size()); // allways is 20000
        
        Map<Integer, String> newMap = Collections.synchronizedMap(new HashMap<>());
        Thread t3 = new Thread(() -> addPositiveNumbers(newMap));
        Thread t4 = new Thread(() -> addNegativeNumbers(newMap));
        t3.start();
        t4.start();
        t3.join();
        t4.join();
        System.out.println(newMap.size());

    }

    private static void addPositiveNumbers(Map<Integer, String> map) {
        for (int i = 0; i < 10000; i++) {
            map.put(i, "Number is " + i);
        }
    }

    private static void addNegativeNumbers(Map<Integer, String> map) {
        for (int i = -10000; i < 0; i++) {
            map.put(i, "Numbers is " + i);
        }
    }

}
