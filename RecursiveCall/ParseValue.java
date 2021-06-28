package RecursiveCall;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Wow! This problem is kind of tricky. If you're ready to put your thinking cap
 * on, brace yourself and good luck! Otherwise, you can skip it for now and
 * return any time later
 * 
 * Each number can be broken down into parts, or addends. For example, number 3
 * may be broken down into such addends as 1 + 1 + 1, 2 + 1, 3. This procedure
 * is known as decomposition. In this task, you'll need to find out all
 * decompositions of number N N N (1≤N≤40 1 \leq N \leq 40 1≤N≤40) and list its
 * positive addends. The decomposition should be printed in lexicographical
 * order. For example:
 * 
 * 1 1 1 1 1 
 * 2 2 1 
 * 3 1 1 
 * ... 
 * 
 * Each decomposition should consist of the addends in
 * a descending order, where each subsequent number of the list is equal or less
 * than the previous one.
 * 
 * Sample Input 1:
 * 
 * 5
 * 
 * Sample Output 1:
 * 
 * 1 1 1 1 1 
 * 2 1 1 1 
 * 2 2 1 
 * 3 1 1 
 * 3 2 
 * 4 1 
 * 5
 * 
 * 
 * @author SMD_ASY
 *
 */

public class ParseValue {

	public static int count;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your number's:");
		int n = sc.nextInt();
		parseValue(n);
		printPartitions(n, n, "");
		System.out.println("number of dispersion " + count);
	}
	
    public static void printPartitions(int target, int maxValue, String suffix) {
        if (target == 0) {
            System.out.println(suffix);
        } else {
            if (maxValue > 1) {
                printPartitions(target, maxValue - 1, suffix);
            }
            if (maxValue <= target) {
                printPartitions(target - maxValue, maxValue, suffix + " " + maxValue);
            }
        }
    }

	private static void parseValue(int n) {
		// TODO Auto-generated method stub
		if (n <= 0) {
			return;
		}
		List<Integer> ls = new ArrayList<>();
		ls = buildListOfOnes(n);
		disparceList(ls, n);

	}

	private static void disparceList(List<Integer> ls, int n) {
		// TODO Auto-generated method stub
		List<Integer> maxElements = new ArrayList<>();
		List<Integer> minElements = new ArrayList<>();
		List<Integer> resElements = new ArrayList<>();
		printList(ls);
		if (ls.size() == 1) {
			return;
		} else {
			int minIndex = findIndexOfMinElement(ls);
			maxElements = buildListOfMaxElements(ls, minIndex);
			minElements = buildListOfMinElements(maxElements, n);
			resElements.addAll(maxElements);
			resElements.addAll(minElements);
			disparceList(resElements, n);
		}

	}

	private static List<Integer> buildListOfMinElements(List<Integer> ls, int n) {
		List<Integer> lsMin = new ArrayList<>();
		int sum = 0;
		for (int i = 0; i < ls.size(); i++) {
			sum += ls.get(i);
		}
		int dif = n - sum;
		while (dif > 0) {
			lsMin.add(1);
			dif--;
		}
		return lsMin;
	}

	private static List<Integer> buildListOfMaxElements(List<Integer> ls, int minIndex) {
		List<Integer> lsMax = new ArrayList<>();
		for (int i = 0; i <= minIndex; i++) {
			if (i == minIndex) {
				int temp = ls.get(i);
				temp++;
				lsMax.add(temp);
			} else {
				lsMax.add(ls.get(i));
			}
		}
		return lsMax;
	}

	private static int findIndexOfMinElement(List<Integer> ls) {
		int index = 0;
		int minElement = ls.get(index);
		for (int i = 1; i < ls.size() - 1; i++) {
			if (minElement > ls.get(i)) {
				index = i;
				minElement = ls.get(i);
			}
		}
		return index;
	}

	private static void printList(List<Integer> ls) {
		for (int i = 0; i < ls.size(); i++) {
			System.out.print(ls.get(i) + " ");
		}
		System.out.println();
		count++;
	}

	private static List<Integer> buildListOfOnes(int n) {
		List<Integer> ls = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			ls.add(1);
		}
		return ls;
	}

}
