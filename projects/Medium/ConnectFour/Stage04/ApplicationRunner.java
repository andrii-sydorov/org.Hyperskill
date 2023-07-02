package projects.Medium.ConnectFour.Stage04;

/**
 * <h5>Stage 4/4: Players Ready</h5>
 * 
 * <h6>$1. Description</h6>
 * 
 * <p>In this stage, we will finalize our game to play it with a friend! For this,
 * we need to come up with an algorithm to check if a player has connected four
 * pieces in a row after every click. The check must be carried out in three
 * directions: horizontal, vertical, and diagonal. When the algorithm detects a
 * winning move, the program should highlight these four cells so that the
 * players can easily see who's won.</p>
 * 
 * <p>We will also add the baseline background color for the cells from the start
 * of the game and a distinct color for the cells that form a win condition. You
 * can choose any colors that you want as long as the baseline and winning
 * colors are different.</p>
 * 
 * <p>Ensure that your program processes invalid moves in the following way. A
 * click on a filled column should do nothing, and the player must be given
 * another try. Additionally, a click on any cell after a game has been finished
 * should lead to nothing.</p>
 * 
 * <p>Finally, don't forget to add the reset button!</p>
 * 
 * <h6>$2. Objectives</h6>
 * 
 * <p>In this stage, implement the following features:</p>
 * <ol>
 * <li>Add the baseline color for all cells at the start of the application;</li>
 * <li>Add a reset button that extends the <b>JButton</b> named <b>ButtonReset</b>. The button
 * should be enabled. Clicking on it should clear all cells and return them to
 * their baseline color;</li>
 * <li>Check if either player has connected four in a row vertically, horizontally,
 * or diagonally after each move;</li>
 * <li>Once a winner has been detected, change the color of the four winning cells
 * to the winning color. The baseline color and the winning color should be two
 * different colors;</li>
 * <li>A click on any board cell in any filled column must lead to nothing. The
 * gameplay continues with the same player's turn;</li>
 * <li>A click on any board cell once a game is finished must lead to nothing.</li>
 * </ol>
 * 
 * <h5>$3. Examples</h5>
 * 
 * <p>Example 1: starting the application</p>
 * 
 * <p>Desktop Connect Four empty game board</p>
 * 
 * <p>Example 2: the first click on A6</p>
 * 
 * <p>Desktop Connect Four first move</p>
 * 
 * <p>Example 3: the second click on A5</p>
 * 
 * <p>Desktop Connect Four second move</p>
 * 
 * <p>Example 4: several moves later</p>
 * 
 * <p>Desktop Connect Four O wins horizontal</p>
 * 
 * <p>Example 5: after pressing the reset button</p>
 * 
 * <p>Desktop Connect Four empty game board</p>
 * 
 * <p>Example 6: several moves later</p>
 * 
 * <p>Desktop Connect Four "X" wins vertical</p>
 * 
 * <p>Example 7: after resetting and several moves</p>
 * 
 * <p>Desktop Connect Four "O" wins diagonal</p>
 */

public class ApplicationRunner {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new ConnectFour();
    }

}
