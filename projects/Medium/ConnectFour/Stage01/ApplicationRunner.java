package projects.Medium.ConnectFour.Stage01;

/**
 * <h5>Stage 1/4: Setting up the board</h5>
 * <h6>$1. Description</h6>
 * 
 * <p>The first and, of course, very natural step in developing a game is setting
 * up the board! What is a game board in our case? Connect Four requires a set
 * of rows and columns that intersect each other and form cells to store game
 * pieces. They help us organize the pieces to easily see when a player has
 * connected four pieces in a row. You can see an example below:</p>
 * 
 * player has connected four pieces in a row in the Connect Four game
 * 
 * 
 * <p>We will use the same board for our game. At this stage, we won't concern
 * ourselves with the pieces, but the only thing you need to do is to create an
 * empty board with rows and columns. We also need to label the columns with
 * uppercase letters and numbers for the rows.</p>
 * 
 * <p>Objectives</p>
 * 
 * <p>Your program should display an empty game board as described below.</p>
 * <ol>
 * <li>The main <b>Connect4</b> class should extend the <b>JFrame</b> class, set its title as
 * Connect Four, and exit on close;</li>
 * <li>The cells should extend the JButton class. Hint: Set the painted focus to
 * <b>false</b> to remove the highlighting on the clicked cells;</li>
 * <li>Create 6 rows and 7 columns on the board;</li>
 * <li>Each cell should be visible and display the row and column as its label. For
 * example, <b>A1</b>, <b>G6</b>;</li>
 * <li>Each cell should be named <b>Button**</b> where <b>**</b> is the row and column designation
 * in the label;</li>
 * <li>Organize rows and columns as shown in the example below.</li>
 * </ol>
 * 
 * <h6>$2. Example</h6>
 */

public class ApplicationRunner {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new ConnectFour();
    }

}
