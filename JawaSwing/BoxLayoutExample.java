package JawaSwing;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class BoxLayoutExample extends JFrame {

    public BoxLayoutExample() {
        super("Box Layout Example");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new JButton("First"));
        add(new JButton("Second"));
        add(new JButton("Third"));
        add(new JButton("Fourth"));
        add(new JButton("Fifth"));

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setVisible(true);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new BoxLayoutExample();
    }

}
