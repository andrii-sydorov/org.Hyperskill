package JawaSwing.Layout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FlowLayoutExample extends JFrame {

    public FlowLayoutExample() {
        super("Flow Layout Example");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new JButton("First"));
        add(new JButton("Second"));
        add(new JTextField("Enter your text here"));
        add(new JLabel("This is a long label"));
        add(new JButton("Third"));

        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

        setVisible(true);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new FlowLayoutExample();
    }

}
