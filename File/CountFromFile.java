package File;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CountFromFile {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String path = bf.readLine();
        System.out.println(countEvenNumbers(path));
        path = bf.readLine();
        bf.close();
        System.out.println(countIncreasePopulation(path));
        System.out.println(countSumFromFile(".\\src\\File\\dataset_91033.txt"));
        System.out.println(theBiggestIntegersFromFile(".\\src\\File\\dataset_91007.txt"));
        System.out.println(countNumbersEqualsTo(".\\src\\File\\dataset_91022.txt"));
    }

    /**
     * Here is a file containing a sequence of integers. Each number begins a new
     * line.
     * 
     * Download the file and write a Java program that counts the number of even
     * numbers in this file. You should stop counting either if you get 0 or the
     * last number is reached.
     * 
     * Enter the result.
     * 
     * @param path
     * @return
     * @throws IOException
     */
    private static int countEvenNumbers(String path) throws IOException {
        File f = new File(path);
        Scanner sc = new Scanner(f);
        int count = 0;
        int index = 1;
        while (sc.hasNext()) {
            int temp = Integer.valueOf(sc.nextLine());
            if (temp == 0) {
                break;
            } else if (temp % 2 == 0) {
                count++;
            }
            index++;
        }
        sc.close();
        return count;
    }

    /**
     * Here's a file that stores data on the world population since 1950, according
     * to the United States Census Bureau (2017).
     * 
     * Download the file and write a Java program to find out in what year the
     * largest increase in population occurred as compared to the previous year.
     * 
     * The file has two columns: year and population. Take a look at it to
     * understand the format better.
     * 
     * Enter the resulting year.
     * 
     * @param path
     * @return
     * @throws IOException
     */

    private static long countIncreasePopulation(String path) throws IOException {
        File f = new File(path);
        Scanner sc = new Scanner(f);
        long diff = 0;
        long year = 0;
        List<long[]> ls = new ArrayList<>();
        while (sc.hasNext()) {
            long[] data = null;
            try {
                data = Arrays.stream(sc.nextLine().replaceAll(",", "").split("\\s+")).mapToLong(Long::valueOf)
                        .toArray();
            } catch (NumberFormatException nfe) {
                continue;
            }
            ls.add(data);
        }
        sc.close();
        for (int i = 0; i < ls.size() - 1; i++) {
            if (ls.get(i + 1)[1] - ls.get(i)[1] > diff) {
                year = ls.get(i + 1)[0];
                diff = ls.get(i + 1)[1] - ls.get(i)[1];
            }
        }
        return year;
    }

    /**
     * Here's a file containing a sequence of integers. Each number starts with a
     * new line.
     * 
     * Download it and write a Java program that calculates the sum of these
     * numbers.
     * 
     * Enter the result.
     * 
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    private static long countSumFromFile(String path) throws FileNotFoundException {
        long sum = 0;
        File f = new File(path);
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            sum += Long.valueOf(sc.nextLine());
        }
        sc.close();
        return sum;
    }

    /**
     * Here is a file containing a sequence of integers separated by spaces.
     * 
     * Download it and write a Java program that finds the greatest number in this
     * file.
     * 
     * Enter the result.
     * 
     * @param path
     * @return
     * @throws FileNotFoundException
     */

    private static int theBiggestIntegersFromFile(String path) throws FileNotFoundException {
        int max = Integer.MIN_VALUE;
        File f = new File(path);
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            int temp = sc.nextInt();
            if (max < temp) {
                max = temp;
            }
        }
        sc.close();
        return max;
    }

    private static int countNumbersEqualsTo(String path) throws IOException{
        String data = readAllLines(path);
        int n = 9999;
        int count = 0;
        String[] numbers = data.split("\\s+"); 
        for (int i = 0; i < numbers.length; i++) {
            if (n < Integer.valueOf(numbers[i])) {
                count++;
            }
        }
        return count;
    }

    private static String readAllLines(String fileName) throws IOException{
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

}
