package JawaSwing;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class GridLayoutExample extends JFrame {

    public GridLayoutExample() {
        super("Grid Layout Example");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new JButton("First"));
        add(new JButton("Second"));
        add(new JTextField("Enter your text here"));
        add(new JLabel("This is a long label"));
        add(new JButton("Third"));
        add(new JLabel("This is another label"));

        setLayout(new GridLayout(3, 2, 5, 5));

        setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        Runnable r = new Runnable() {

            @Override
            public void run() {
                new GridLayoutExample();

            }
        };

        SwingUtilities.invokeAndWait(r);
    }

}
