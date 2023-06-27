package projects.Medium.ConnectFour.Stage03;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SetUpBoard extends JFrame {

    public SetUpBoard() {
        super("Connect4");
        setSize(360, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        int raw = 6;
        int column = 7;

        for (int i = raw; i != 0; i--) {
            for (char c = 'A'; c <= 'G'; c++) {
                JButton jb = new JButton();
                String text = String.format("%c%d", c, i);
                jb.setText(text);
                jb.setFocusPainted(false);
                add(jb);
            }
        }

        setLayout(new GridLayout(raw, column, 0, 0));

        setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        Runnable r = new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                new SetUpBoard();
            }
        };

        SwingUtilities.invokeAndWait(r);
    }

}
