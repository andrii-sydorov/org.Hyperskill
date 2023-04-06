package projects.Easy.RockPaperScissors.Stage02;

import java.util.Scanner;
import java.util.Random;

/**
 * Stage 2/5: Equalizing chances
 * 
 * Description
 * 
 * Well, now let's do something more tangible. Nobody wants to play the game
 * where you always lose. We can use the power of the Random class to make the
 * game a bit more challenging.
 * 
 * Write a program that reads input from users, chooses a random option, and
 * then says who won: a user or the computer.
 * There are a few examples below to provide the output for any outcome
 * (<option> is the option chosen by your program):
 * 
 * - Loss: Sorry, but the computer chose <option>;
 * - Draw: There is a draw (<option>);
 * - Win: Well done. The computer chose <option> and failed;
 * 
 * Objectives
 * 
 * Your program should:
 * 
 * - Read the user input that includes an option;
 * - Choose a random option;
 * - Compare the options and determine the winner;
 * - Output one of the lines above depending on the result of the game.
 * 
 * Examples
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Example 1:
 * 
 * > rock
 * Well done. The computer chose scissors and failed
 * 
 * Example 2:
 * 
 * > scissors
 * There is a draw (scissors)
 * 
 * Example 3:
 * 
 * > paper
 * Sorry, but the computer chose scissors
 * 
 * @author SMD_ASY
 *
 */

public class EqualChance {

    private static String[] items = { "rock", "paper", "scissors" };

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        String userChoice = sc.nextLine();
        int userIndex = findIndex(userChoice);
        int computerIndex = generateIndex();
        String computerChoice = items[computerIndex];
        if (userIndex == computerIndex) {
            System.out.printf("There is a draw (%s)", userChoice);
        } else if (userIndex > computerIndex) {
            if (userIndex - computerIndex > 1) {
                System.out.printf("Sorry, but the computer chose %s", computerChoice); // user lose
            } else {
                System.out.printf("Well done. The computer chose %s and failed", computerChoice); // user win
            }
        } else { // computerIndex > userIndex
            if (computerIndex - userIndex > 1) {
                System.out.printf("Well done. The computer chose %s and failed", computerChoice); // user win
            } else {
                System.out.printf("Sorry, but the computer chose %s", computerChoice); // user lose
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
