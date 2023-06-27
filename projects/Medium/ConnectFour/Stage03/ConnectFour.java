package projects.Medium.ConnectFour.Stage03;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ConnectFour extends JFrame {

    private static String mark = " ";
    public static JButton[][] jbArray = new JButton[6][7];

    public ConnectFour() {

        super("Connect Four");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(null);
        int raw = 6;
        int column = 7;

        // should be 0 by init operation
        int x = 0;

        for (int i = raw; i != 0; i--) {
            int y = 0;
            for (char c = 'A'; c <= 'G'; c++) {
                JButton jb = new JButton(" ");
                String text = String.format("%c%d", c, i);
                jb.setName("Button" + text);
                jb.setFocusPainted(false);
                add(jb);
                jbArray[x][y] = jb;
                jb.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        JButton jbAnother = Tools.getJbuuton(jb);
                        if (jbAnother == null) {
                            return;
                        }
                        mark = mark.equals("X") ? "O" : "X";
                        jbAnother.setText(mark);
                    }
                });

                y++;
            }
            x++;
        }

        setVisible(true);
        setLayout(new GridLayout(raw, column, 0, 0));

    }

}

class Tools {

    public static JButton getJbuuton(JButton jb) {
        Character column = jb.getName().charAt(jb.getName().length() - 2);
        int c = Character.getNumericValue(column - 17);
        int r = 5;

        while (true && r >= 0) {
            if (ConnectFour.jbArray[r][c].getText().equals(" ")) {
                return ConnectFour.jbArray[r][c];
            }
            r--;
        }

        return null;
    }
}
