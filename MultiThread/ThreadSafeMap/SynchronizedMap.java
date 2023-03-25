package MultiThread.ThreadSafeMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SynchronizedMap {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        Map<Integer, String> map = Collections.synchronizedMap(new HashMap<>());
        Thread writer = new Thread(() -> {
            addPositiveNumbers(map);
        });
        writer.start();
        addNegativeNumbers(map);
        writer.join();
        System.out.println(map.size()); // allways 200000

        Map<Integer, String> hashmap = new HashMap<>();
        for (int i = 1; i < 10; i++) {
            hashmap.put(i, " Square is " + i * i);
        }

        hashmap.forEach((x, y) -> System.out.println(x + y));

        Thread t1 = new Thread(() -> addNumber(hashmap));
        Thread t2 = new Thread(() -> iterate(hashmap));
    }

    private static void iterate(Map<Integer, String> map) {
        Iterator<Integer> it = map.keySet().iterator();
        while (it.hasNext()) {
            System.out.println(it.next() + map.get(it.next()));
        }

    }

    private static void addNumber(Map<Integer, String> map) {

        Iterator<Integer> it = map.keySet().iterator();
        while (it.hasNext()) {
            int key = it.next();
            String toInsert = map.get(key) + "some Words";
            map.put(key, toInsert);

        }

    }

    private static void addPositiveNumbers(Map<Integer, String> map) {
        for (int i = 0; i < 100_000; i++) {
            map.put(i, "Number is " + i);
        }
    }

    private static void addNegativeNumbers(Map<Integer, String> map) {
        for (int i = -100_000; i < 0; i++) {
            map.put(i, "Numbers is " + i);
        }
    }

}
