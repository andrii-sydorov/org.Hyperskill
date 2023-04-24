package projects.Medium.SmartCalculator.Stage02;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Stage 2/8: 2+2+
 * 
 * Description
 * 
 * It is high time to improve the previous version of the calculator. What if we
 * have many pairs of numbers, the sum of which we need to find? It will be very
 * inconvenient to run the program every time. So then let's add a loop to
 * continuously calculate the sum of two numbers. Be sure to have a safeword to
 * break the loop. Also, It would be nice to think through situations where
 * users enter only one number or do not enter numbers at all.
 * 
 * Objectives
 * 
 * Write a program that reads two numbers in a loop and prints the sum in the
 * standard output.
 * If a user enters only a single number, the program should print the same
 * number. If a user enters an empty line, the program should ignore it.
 * When the command /exit is entered, the program must print "Bye!" (without
 * quotes), and then stop.
 * 
 * Examples
 * 
 * The greater-than symbol followed by a space (>) represents the user input.
 * 
 * > 17 9
 * 26
 * > -2 5
 * 3
 * >
 * > 7
 * 7
 * > /exit
 * 
 * @author SMD_ASY
 *
 */

public class AdvanceAdd {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("/exit")) {
            if (command.isEmpty()) {
                // got to the next step
            } else {
                int[] arr = Arrays.stream(command.split("\\s+")).mapToInt(Integer::valueOf).toArray();
                System.out.println(arr.length == 1 ? arr[0] : Arrays.stream(arr).sum());
            }
            command = sc.nextLine();
        }
        System.out.println("Bye!");
        sc.close();
    }

}
