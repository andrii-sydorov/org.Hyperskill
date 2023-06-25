package projects.Medium.ConnectFour.Stage02;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * <h5>Stage 2/4: Placing the pieces
 * <h5>
 * 
 * <h6>$1. Description</h6>
 * 
 * <p>
 * An empty game board isn't of much use if we can't do anything with it. In
 * this stage, we will add two important features. First, we need to allow
 * players to place the game pieces. In the board game, they are of different
 * colors. In this desktop version, we will use different letters — <b>X</b> and
 * <b>O</b>.
 * </p>
 * 
 * <p>
 * Second, we need to alternate player turns so that each person can get a fair
 * chance. At this stage, place a piece in the cell where players click and then
 * alternate turns after every click, starting with <b>X</b>.
 * </p>
 * 
 * <h6>$2. Objectives</h6>
 * 
 * <p>
 * In this stage, your program should:
 * </p>
 * 
 * <ol>
 * <li>Display an empty game board, where each cell displays a single space "<b> </b>"
 * as its label;</li>
 * <li>When a player clicks on a cell, change that cell's label to either <b>X</b> or <b>O</b>
 * depending on the turn, starting with <b>X</b>.</li>
 * </ol>
 * 
 * <h6>&3. Examples</h6>
 * <p>Example 1: the game starts</p>
 * 
 * <p>the Connect Four game starts, the field is empty</p>
 * 
 * <p>Example 2: a click on A1</p>
 * 
 * <p>Connect Four game: user clicks on A1</p>
 * 
 * <p>Example 3: a click on A2</p>
 * 
 * <p>Connect Four game: user clicks on A2</p>
 * 
 * <p>Example 4: filling the entire board</p>
 * 
 * <p>Connect Four game: filling the entire board</p>
 */

public class ConnectFour extends JFrame {

    private static String mark = " ";

    public ConnectFour() {

        super("Connect Four");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(null);
        int raw = 6;
        int column = 7;

        for (int i = raw; i != 0; i--) {
            for (char c = 'A'; c <= 'G'; c++) {
                JButton jb = new JButton(" ");
                String text = String.format("%c%d", c, i);
                jb.setName("Button" + text);
                jb.setFocusPainted(false);
                add(jb);
                jb.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        jb.setText(getMark());
                    }
                });
            }
        }

        setVisible(true);
        setLayout(new GridLayout(raw, column, 0, 0));

    }

    public static String getMark() {
        switch (mark) {
            case " ":
            case "O":
                mark = "X";
                break;
            case "X":
                mark = "O";
                break;
        }
        return mark;
    }
}
