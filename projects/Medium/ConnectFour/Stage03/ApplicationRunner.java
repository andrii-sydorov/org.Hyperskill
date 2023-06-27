package projects.Medium.ConnectFour.Stage03;

/**
 * <h5>Stage 3/4: Filling the board</h5>
 * 
 * <h6>$1. Description</h6>
 * 
 * <p>Have you noticed the issues in the last stage? When you click on a cell in
 * the top row, it fills that particular cell even if the cells below are empty.
 * In Connect Four, pieces are placed column by column. They are placed onto
 * either the uppermost game piece in that column or, if the column is empty,
 * onto the board bottom. In this stage, we will change the way players place
 * the pieces. We need to designate a separate class that can check the first
 * free cell in each column. The rest remains the same as in the previous stage.</p>
 * 
 * <h6>$2. Objectives</h6>
 * 
 * <p>In this stage, your program should:</p>
 * 
 * <ol>
 * <li>Fill the cells according to the rule above — not by the position of the click
 * but by the column that was clicked on;</li>
 * <li>Continue to alternate between the <b>X</b> and <b>O</b> pieces after each click.</li>
 *</ol> 
 *
 * <h6>$3. Example</h6>
 * 
 * <p>Example 1: the start of the application</p>
 * 
 * <p>Desktop Connect Four blank game</p>
 * 
 * <p>Example 2: the first click on A6</p>
 * 
 * <p>Desktop Connect Four first move</p>
 * 
 * <p>Example 3: the second click on A5</p>
 * 
 * <p>Desktop Connect Four second move</p>
 * 
 * <p>Example 4. the board is full</p>
 * 
 * <p>Desktop Connect Four board filled</p>
 */

public class ApplicationRunner {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new ConnectFour();
    }

}
