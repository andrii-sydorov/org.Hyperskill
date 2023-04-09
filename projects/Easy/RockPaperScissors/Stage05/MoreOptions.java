package projects.Easy.RockPaperScissors.Stage05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Stage 5/5: More options
 * 
 * Description
 * 
 * How about new game rules? The original game has a fairly small choice of
 * options.
 * 
 * The extended version of the game makes it hard to draw. Now, your program
 * should accept alternative lists of options, like Rock, Paper, Scissors,
 * Lizard, Spock, and so on. You can take the following options (don't take
 * their relations into account; we'll speak about them further on):
 * 
 * 
 * In this stage, before the game starts, users can choose the options. After
 * entering the name, they should provide a list of the options separated by a
 * comma. For example:
 * 
 * rock,paper,scissors,lizard,spock
 * 
 * If users input an empty line, start the game with default options: rock,
 * paper, and scissors.
 * 
 * Once the game options are defined, output Okay, let's start.
 * 
 * Regardless of the chosen options, your program, obviously, should identify
 * which option beats which. You can use the following algorithm.
 * 
 * Let's imagine that the following options are involved in the game "Rock Fire
 * Scissors Sponge Paper Air Water". Order is important.
 * 
 * 
 * 
 * Let us represent this line as a closed circle:
 * 
 * 
 * 
 * First, every option produces a draw when opposed to itself.
 * 
 * 
 * 
 * If Rock opposed Rock - it's a draw.
 * 
 * Secondly, every option beats half of the other options and is defeated by
 * another half.
 * 
 * For "Rock":
 * 
 * 
 * The first half of the options after Rock are Fire Scissors and Sponge (Rock
 * beat it). Another half (after first half) - Paper, Air and Water are defeated
 * Rock.
 * 
 * For "Fire":
 * 
 * 
 * The first half of the options after Fire are Scissors, Sponge and Paper (Fire
 * beat it). Another half (after first half) - Air, Water and Rock are defeated
 * Fire.
 * 
 * And so on
 * 
 * How to determine which options are stronger or weaker? Take the list of
 * options provided by the user and pick the option that you want to know the
 * relationships of. Take all other options from the user's list. Add them to
 * the list of options that precede the chosen option. Now, you have another
 * list of options that don't include the user's option with a different order
 * of elements inside. First are the options that follow the chosen one in the
 * original list; then, there are the ones that precede it. So, in this "new"
 * list, the first half of the options defeat the "chosen" option, and the
 * second half is beaten by it.
 * 
 * Once again, never mind the "links" between the options in the picture above.
 * They are only for illustration purposes
 * For example, the user's list of options is rock,paper,scissors,lizard,spock.
 * You want to know what options are weaker than lizard. By looking at the list
 * spock,rock,paper,scissors you realize that spock and rock beat lizard. Paper
 * and scissors are defeated by it. For spock, it'll be almost the same, but
 * it'll get beaten by rock and paper, and prevail over scissors and lizard.
 * 
 * Of course, this is not the most efficient way to determine which option
 * prevails over which. You are welcome to try to develop other methods of
 * tackling this problem.
 * 
 * Objectives
 * 
 * Your program should:
 * 
 * - Output a line Enter your name: . Users enter their names on the same line
 *   (not the one following the output);
 * - Read the input with the username and output Hello, <name>;
 * - Read rating.txt and check whether it contains an entry with the current
 *   username. If yes, use the score specified in the file as a starting point. If
 *   not, start the score from 0;
 * - Read the input with the list of options for the game (options are separated
 *   by comma). If the input is an empty line, play with the default options;
 * - Output a line Okay, let's start;
 * - Play the game by the rules defined in the previous stages and read the user's
 *   input;
 * - If the input is !exit, output Bye! and stop the game;
 * - If the input is the name of the option, then pick a random option and output
 *   a line with the result of the game in the following format (<option> is the
 *   name of the option chosen by the program):
 *    - Loss: Sorry, but the computer chose <option>
 *    - Draw: There is a draw (<option>)
 *    - Win: Well done. The computer chose <option> and failed
 * - For each draw, add 50 points to the score. For each user's win, add 100 to
 *   their score. In case of a loss, don't change the score;
 * - If input corresponds to anything else, output Invalid input;
 * - Restart the game (with the same options defined before the start of the
 *   game).
 *   
 * Examples
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Example 1:
 * 
 * Enter your name: > Tim
 * Hello, Tim
 * >
 * rock,gun,lightning,devil,dragon,water,air,paper,sponge,wolf,tree,human,snake,scissors,fire
 * Okay, let's start
 * > rock
 * Sorry, but the computer chose air
 * > !rating
 * Your rating: 0
 * > rock
 * Well done. The computer chose sponge and failed
 * > !rating
 * Your rating: 100
 * > !exit
 * Bye!
 * 
 * Example 2:
 * 
 * Enter your name: > Tim
 * Hello, Tim
 * >
 * Okay, let's start
 * > rock
 * Well done. The computer chose scissors and failed
 * > paper
 * Well done. The computer chose rock and failed
 * > paper
 * There is a draw (paper)
 * > scissors
 * Sorry, but the computer chose rock
 * > !exit
 * Bye!
 * 
 * @author SMD_ASY
 *
 */

public class MoreOptions {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        Player p = createPlayer(name);
        System.out.printf("Hello, %s\n", name);
        System.out.println("Okay, let's start");
        String input = sc.nextLine();
        String[] items = input.isEmpty() ? new String[] { "rock", "paper", "scissors" } : input.split(",");
        String userChoice = null;
        while (true) {
            userChoice = sc.nextLine();
            if (userChoice.equals("!exit")) {
                System.out.println("Bye!");
                break;
            } else if (userChoice.equals("!rating")) {
                System.out.printf("Your rating: %d\n", p.getScore());
            } else if (findIndex(userChoice, items) >= 0) {
                int computerIndex = generateIndex(items);
                String computerChoice = items[computerIndex];
                List<String> computerLoose = makeList(items, computerIndex);
                if (computerChoice.equals(userChoice)) {
                    System.out.printf("There is a draw (%s)\n", computerChoice);
                    p.setScore(p.getScore() + 50);
                } else if (computerLoose.contains(userChoice)) {
                    System.out.printf("Well done. The computer chose %s and failed\n", computerChoice);
                    p.setScore(p.getScore() + 100);
                } else {
                    System.out.printf("Sorry, but the computer chose %s\n", computerChoice);
                }
            } else {
                System.out.println("Invalid input");
            }
        }
        sc.close();
    }

    public static List<String> makeList(String[] items, int computerIndex) {
        List<String> ans = new ArrayList<>();
        int count = 0;
        int limit = items.length % 2 == 0 ? items.length / 2 - 1 : items.length / 2;
        for (int i = (computerIndex + 1) % items.length; count < limit; i++) {
            ans.add(items[i % items.length]);
            count++;
        }
        return ans;
    }

    public static int findIndex(String s, String[] items) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }

    public static Player createPlayer(String name) {
        int score = 0;
        File f = new File("./src/projects/Easy/RockPaperScissors/Stage04/rating.txt");
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNext()) {
                String[] data = sc.nextLine().split("\\s+");
                if (name.equals(data[0])) {
                    score = Integer.valueOf(data[1]);
                    break;
                }
            }
        } catch (FileNotFoundException nfe) {
            System.out.println("File was not found!");
            return null;
        }
        return new Player(name, score);
    }

    public static int generateIndex(String[] items) {
        Random r = new Random();
        int index = r.nextInt(items.length); // without minus 1 because exclusive should be
        return index;
    }
}

class Player {

    private int score;
    private String name;

    public Player(String name, int score) {
        super();
        this.score = score;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
