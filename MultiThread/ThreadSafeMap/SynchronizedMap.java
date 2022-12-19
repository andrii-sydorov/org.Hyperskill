package MultiThread.ThreadSafeMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SynchronizedMap {

    public static void main(String[] args) throws InterruptedException{
        // TODO Auto-generated method stub
        Map<Integer, String> map = Collections.synchronizedMap(new HashMap<>());
        Thread writer = new Thread(() -> {
            addPositiveNumbers(map);
        });
        writer.start();
        addNegativeNumbers(map);
        writer.join();
        System.out.println(map.size()); // allways 200000
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
