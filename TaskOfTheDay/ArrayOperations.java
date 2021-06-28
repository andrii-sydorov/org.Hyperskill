package TaskOfTheDay;

import java.util.Arrays;

/**
 * You need to implement printCorners method. It should print all corner
 * elements of the twoDimArray in the following order: left to right and top to
 * bottom.
 * 
 * The elements should be separated by space and printed as an example below.
 * 
 * Input array:
 * 
 * 1 0 2 
 * 0 0 0 
 * 3 0 4 
 * 
 * Printed corners:
 * 
 * 1 2 
 * 3 4 
 * 
 * Notice, that you should always print 4 corners, for example:
 * 
 * Input array:
 * 
 * 1 
 * 2 
 * 3 
 * 
 * Printed corners:
 * 
 * 1 1 3 3
 */

public class ArrayOperations {

    public static void main(String[] args) {
        printCorner(new int[][] { { 1, 0, 2 }, { 0, 0, 0 }, { 3, 0, 4 } });
        System.out.println();
        printCorner(new int[][] { { 1, 2 }, { 3, 4 } });
        System.out.println();
        printCorner(new int[][] { { 1 }, { 2 }, { 3 } });
    }

    public static void printCorner(int[][] twoDimArray) {
        int[] firstRow = twoDimArray[0];
        int[] lastRow = twoDimArray[twoDimArray.length - 1];
        int[][] cornerArray = new int[][] { firstRow, lastRow };
        int[][] result = new int[2][2];
        for (int i = 0; i < result.length; i++) {
            int[] temp = null;
            if (i == 0) {
                temp = cornerArray[0];
            } else {
                temp = cornerArray[cornerArray.length - 1];
            }
            for (int j = 0; j < result.length; j++) {
                if (j == 0) {
                    result[i][j] = temp[0];
                } else {
                    result[i][j] = temp[temp.length - 1];
                }
            }
        }

        System.out.println(Arrays.deepToString(result));

        for (int[] array : result) {
            if (!array.equals(result[result.length - 1])) {
                System.out.println(Arrays.toString(Arrays.stream(array).mapToObj(String::valueOf).toArray())
                        .replace("[", "").replace("]", "").replace(",", ""));
            } else {
                System.out.print(Arrays.toString(Arrays.stream(array).mapToObj(String::valueOf).toArray())
                        .replace("[", "").replace("]", "").replace(",", ""));
            }
        }
    }

}
