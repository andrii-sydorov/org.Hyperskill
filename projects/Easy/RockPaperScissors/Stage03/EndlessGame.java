package projects.Easy.RockPaperScissors.Stage03;

import java.util.Random;
import java.util.Scanner;

/**
 * Stage 3/5: Endless game
 * 
 * Description
 * 
 * We came up with a really cool idea in the previous stage. But the game is
 * really short. Nobody plays a single shot of rock paper scissors. We need to
 * find a way to run the game forever. Not literally, though — let's implement a
 * way to stop your program.
 * 
 * Improve your program so that it will take an unlimited number of inputs until
 * users enter !exit. After entering !exit, the program should print Bye! and
 * terminate. Also, let's try to handle invalid inputs: your program should be
 * ready to handle typos in user input, or when there's a mishmash instead of a
 * normal command. So, if the input doesn't correspond to any known command (an
 * option or !exit), your program should output the following line: Invalid
 * input.
 * 
 * Objectives
 * 
 * Your program should:
 * 
 * - Take input from users;
 * - If the input contains !exit, output Bye! and stop the game;
 * - If the input is the name of the option, then pick a random option and output
 *   a line with the result of the game in the following format (<option> is the
 *   name of the option chosen by the program):
 *    - Loss: Sorry, but the computer chose <option>
 *    - Draw: There is a draw (<option>)
 *    - Win: Well done. The computer chose <option> and failed
 * - If the input corresponds to anything else, output Invalid input;
 * - Repeat it all over again.
 * 
 * Example
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Example 1:
 * 
 * > rock
 * Well done. The computer chose scissors and failed
 * > paper
 * Well done. The computer chose rock and failed
 * > paper
 * There is a draw (paper)
 * > scissors
 * Sorry, but the computer chose rock
 * > rokc
 * Invalid input
 * > xit!
 * Invalid input
 * > !exit
 * Bye!
 * 
 * @author SMD_ASY
 *
 */

public class EndlessGame {

    private static String[] items = { "rock", "paper", "scissors" };

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userChoice = sc.nextLine();
            if (userChoice.equals("!exit")) {
                System.out.println("Bye!");
                break;
            }
            int computerIndex = generateIndex();
            String computerChoice = items[computerIndex];
            String computerToWin = userChoice.equals("rock") ? "paper"
                    : userChoice.equals("scissors") ? "rock" : userChoice.equals("paper") ? "scissors" : null;
            if (computerToWin == null) {
                System.out.println("Invalid input");
            } else if (computerChoice.equals(userChoice)) {
                System.out.printf("There is a draw (%s)\n", computerChoice);
            } else if (computerChoice.equals(computerToWin)) {
                System.out.printf("Sorry, but the computer chose %s\n", computerChoice);
            } else {
                System.out.printf("Well done. The computer chose %s and failed\n", computerChoice);
            }
        }
        sc.close();
    }

    public static int findIndex(String s) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }

    public static int generateIndex() {
        Random r = new Random();
        int index = r.nextInt(items.length); // without minus 1 because exclusive should be
        return index;
    }

}
