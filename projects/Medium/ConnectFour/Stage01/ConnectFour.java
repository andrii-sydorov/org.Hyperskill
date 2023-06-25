package projects.Medium.ConnectFour.Stage01;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ConnectFour extends JFrame {

    public ConnectFour() {

        super("Connect Four");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(null);

        int raw = 6;
        int column = 7;

        for (int i = raw; i != 0; i--) {
            for (char c = 'A'; c <= 'G'; c++) {
                Cell jb = new Cell();
                String text = String.format("%c%d", c, i);
                jb.setText(text);
                jb.setName("Button" + text);
                jb.setFocusPainted(false);
                add(jb);
            }
        }

        setVisible(true);
        setLayout(new GridLayout(raw, column, 0, 0));

    }
}

class Cell extends JButton {

}
