package projects.Medium.ConnectFour.Stage04;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ConnectFour extends JFrame {

    private static String mark = " ";
    public static JButton[][] jbArray = new JButton[6][7];

    public ConnectFour() {

        super("Connect Four");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        int raw = 6;
        int column = 7;

        JPanel jPanelField = new JPanel();
        jPanelField.setLayout(new GridLayout(raw, column, 0, 0));

        int x = 0;
        for (int i = raw; i != 0; i--) {
            int y = 0;
            for (char c = 'A'; c <= 'G'; c++) {
                JButton jb = new JButton(" ");
                String text = String.format("%c%d", c, i);
                jb.setName("Button" + text);
                jb.setFocusPainted(false);
                jb.setBackground(Color.LIGHT_GRAY);
                jPanelField.add(jb);
                jbArray[x][y] = jb;
                jb.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (Tools.gameIsFinished == true) {
                            mark = " ";
                            return;
                        }
                        JButton jbAnother = Tools.getJButton(jb);
                        if (jbAnother == null) {
                            return;
                        }
                        mark = mark.equals("X") ? "O" : "X";
                        jbAnother.setText(mark);
                        jbAnother.setBackground(Color.MAGENTA);
                        Tools.checkWin();
                    }
                });

                y++;
            }
            x++;
        }

        add(jPanelField, BorderLayout.CENTER);

        JPanel jPanelReset = new JPanel();
        //jPanelReset.setLayout(null);
        //jPanelReset.setLayout(new BorderLayout());

        JButton ButtonReset = new JButton("");
        ButtonReset.setText("Reset");
        jPanelReset.add(ButtonReset);
        ButtonReset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Tools.resetButton();
            }
        });

        add(jPanelReset, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static class Tools {

        private static int x;
        private static int y;
        private static boolean gameIsFinished;

        public static JButton getJButton(JButton jb) {
            Character column = jb.getName().charAt(jb.getName().length() - 2);
            int c = Character.getNumericValue(column - 17);
            int r = 5;

            while (true && r >= 0) {
                if (jbArray[r][c].getText().equals(" ")) {
                    x = r;
                    y = c;
                    return jbArray[r][c];
                }
                r--;
            }

            return null;
        }

        public static void resetButton() {
            for (int i = 0; i < jbArray.length; i++) {
                for (int j = 0; j < jbArray[i].length; j++) {
                    jbArray[i][j].setText(" ");
                    jbArray[i][j].setBackground(Color.LIGHT_GRAY);
                }
            }
            mark = " ";
            gameIsFinished = false;
        }

        public static void checkWin() {
            String mark = jbArray[x][y].getText();
            List<JButton> ls = new ArrayList<>();

            // check horizontal
            if (checkHorizontalCondition(mark, ls)) {
                markJButton(ls);
                gameIsFinished = true;
            }
            // check vertical
            if (checkVerticalCondition(mark, ls)) {
                markJButton(ls);
                gameIsFinished = true;
            }
            // check main diagonal
            if (checkMainDiagonalCondition(mark, ls)) {
                markJButton(ls);
                gameIsFinished = true;
            }
            // check side diagonal
            if (checkSideDiagonalCondition(mark, ls)) {
                markJButton(ls);
                gameIsFinished = true;
            }

        }

        private static boolean checkHorizontalCondition(String mark, List<JButton> ls) {

            /**
             * leftLimit = left column
             * rightLimit = right column
             * raw is always the same
             */
            int leftLimit = y - 3 >= 0 ? y - 3 : 0; // left column
            int rightLimit = y + 3 < jbArray[0].length ? y + 3 : jbArray[0].length - 1; // right column

            for (int i = leftLimit; i <= rightLimit; i++) {
                if (jbArray[x][i].getText().equals(mark)) {
                    ls.add(jbArray[x][i]);
                } else {
                    ls.clear();
                }
                if (ls.size() == 4) {
                    return true;
                }
            }
            ls.clear();
            return false;
        }

        private static boolean checkVerticalCondition(String mark, List<JButton> ls) {

            /**
             * upperLimit = upper raw
             * lowerLimit = lower raw
             * column is always the same
             */
            int upperLimit = x - 3 >= 0 ? x - 3 : 0;
            int lowerLimit = x + 3 < jbArray.length ? x + 3 : jbArray.length - 1;

            for (int i = upperLimit; i <= lowerLimit; i++) {
                if (jbArray[i][y].getText().equals(mark)) {
                    ls.add(jbArray[i][y]);
                } else {
                    ls.clear();
                }
                if (ls.size() == 4) {
                    return true;
                }
            }
            ls.clear();
            return false;
        }

        private static boolean checkMainDiagonalCondition(String mark, List<JButton> ls) {

            Set<JButton> set = new LinkedHashSet<>();

            /**
             * the main formula the raws and columns should be increased and decreased
             * at the same time
             */
            int r = 0;
            int c = 0;
            for (int i = 3; i > -4; i--) {
                r = x - i;
                c = y - i;
                if (r < 0 || c < 0 || r > jbArray.length - 1 || c > jbArray[r].length - 1) {
                    continue;
                }
                // r = x - i >= 0 ? x - i : 0;
                // c = y - i >= 0 ? y - i : 0;
                // r = x - i < jbArray.length ? x - i : jbArray.length - 1;
                // c = y - i < jbArray[0].length ? y - i : jbArray[0].length - 1;
                set.add(jbArray[r][c]);
            }

            for (JButton jb : set) {
                if (jb.getText().equals(mark)) {
                    ls.add(jb);
                } else {
                    ls.clear();
                }
                if (ls.size() == 4) {
                    return true;
                }
            }
            ls.clear();
            return false;
        }

        private static boolean checkSideDiagonalCondition(String mark, List<JButton> ls) {

            Set<JButton> set = new LinkedHashSet<>();

            /**
             * the main formula the raws and columns should be increased and decreased
             * at the same time
             */
            int r = 0;
            int c = 0;
            for (int i = 3; i > -4; i--) {
                r = x - i;
                c = y + i;
                if (r < 0 || c < 0 || r > jbArray.length - 1 || c > jbArray[r].length - 1) {
                    continue;
                }
                set.add(jbArray[r][c]);
            }

            for (JButton jb : set) {
                if (jb.getText().equals(mark)) {
                    ls.add(jb);
                } else {
                    ls.clear();
                }
                if (ls.size() == 4) {
                    return true;
                }
            }
            ls.clear();
            return false;
        }

        public static void markJButton(List<JButton> ls) {
            for (JButton jb : ls) {
                jb.setBackground(Color.GREEN);
            }

        }
    }

}
