package TicTacToe_03;

/**
 * Description 
 * 
 * It's time to make things more interesting by adding some game
 * variations. What if you want to play against a friend instead of the AI? How
 * about if you get tired of playing the game and want to see a match between
 * two AIs? You also need to give the user the option of going first or second
 * when playing against the AI.
 * 
 * It should be possible for the user to quit the game after the result is
 * displayed as well.
 * 
 * Objectives Your tasks for this stage are:
 * 
 * Write a menu loop, which can interpret two commands: start and exit.
 * Implement the command start. It should take two parameters: who will play X
 * and who will play O. Two options are possible for now: user to play as a
 * human, and easy to play as an AI. The exit command should simply end the
 * program. In later steps, you will add the medium and hard levels.
 * 
 * Don't forget to handle incorrect input! The message Bad parameters! should be
 * displayed if what the user enters is invalid.
 * 
 * The project was changed. Now the coordinates start from the upper left
 * corner. Look closely at the examples. Example The example below shows how
 * your program should work. The greater-than symbol followed by a space (> )
 * represents the user input. Note that it's not part of the input.
 * 
 * Input command: > start 
 * Bad parameters! 
 * Input command: > start easy 
 * Bad parameters! 
 * Input command: > start easy easy 
 * --------- 
 * |       | 
 * |       | 
 * |       | 
 * ---------
 * Making move level "easy" 
 * --------- 
 * |       | 
 * |     X | 
 * |       | 
 * --------- 
 * Making move level "easy" 
 * --------- 
 * |       | 
 * | O   X | 
 * |       | 
 * --------- 
 * Making move level "easy" 
 * ---------
 * |       | 
 * | O   X | 
 * |     X | 
 * --------- 
 * Making move level "easy" 
 * --------- 
 * |       | 
 * | O   X | 
 * |   O X | 
 * --------- 
 * Making move level "easy" 
 * --------- 
 * |       | 
 * | O X X | 
 * |   O X |
 * --------- 
 * Making move level "easy" 
 * --------- 
 * |     O | 
 * | O X X | 
 * |   O X |
 * --------- 
 * Making move level "easy" 
 * --------- 
 * | X   O | 
 * | O X X | 
 * |   O X |
 * --------- 
 * X wins
 * 
 * Input command: > start easy user 
 * --------- 
 * |       | 
 * |       | 
 * |       | 
 * --------- 
 * Making move level "easy" 
 * --------- 
 * |       | 
 * |       | 
 * |     X | 
 * --------- 
 * Enter the coordinates: > 2 2
 * --------- 
 * |       | 
 * |   O   | 
 * |     X | 
 * --------- 
 * Making move level "easy" 
 * --------- 
 * |   X   |
 * |   O   | 
 * |     X | 
 * --------- 
 * Enter the coordinates: > 3 1 
 * --------- 
 * |   X   | 
 * |   O   | 
 * | O   X | 
 * --------- 
 * Making move level "easy" 
 * --------- 
 * |   X X | 
 * |   O   | 
 * | O   X |
 * --------- 
 * Enter the coordinates: > 2 3 
 * --------- 
 * |   X X | 
 * |   O O | 
 * | O   X |
 * --------- 
 * Making move level "easy" 
 * --------- 
 * | X X X | 
 * |   O O | 
 * | O   X |
 * --------- 
 * X wins
 * 
 * Input command: > start user user 
 * --------- 
 * |       | 
 * |       | 
 * |       | 
 * --------- 
 * Enter the coordinates: > 3 1 
 * --------- 
 * |       | 
 * |       | 
 * | X     | 
 * --------- 
 * Enter the coordinates: > 2 2 
 * --------- 
 * |       | 
 * |   O   | 
 * | X     | 
 * --------- 
 * Enter the coordinates: > 2 1
 * --------- 
 * |       | 
 * | X O   | 
 * | X     | 
 * --------- 
 * Enter the coordinates: > 3 2 
 * ---------
 * |       | 
 * | X O   | 
 * | X O   | 
 * --------- 
 * Enter the coordinates: > 1 1 
 * --------- 
 * | X     | 
 * | X O   | 
 * | X O   | 
 * --------- 
 * X wins
 * 
 * Input command: > exit
 * 
 * @author SMD_ASY
 *
 */

public class Main {

	public static void main(String[] args) {
		Game g = new Game(2, 3);
		g.start();
	}

}
