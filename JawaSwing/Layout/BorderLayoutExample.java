package JawaSwing.Layout;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class BorderLayoutExample extends JFrame {

    public BorderLayoutExample() {
        super("Border Layout Example");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        add(new JButton("North"), BorderLayout.NORTH);
        add(new JButton("West"), BorderLayout.WEST);
        add(new JButton("Center"), BorderLayout.CENTER);
        add(new JButton("East"), BorderLayout.EAST);
        add(new JButton("South"), BorderLayout.SOUTH);

//        JPanel panel = new JPanel();
//        panel.add(new JButton("One"));
//        panel.add(new JButton("Two"));
//        add(panel, BorderLayout.NORTH);

        setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        Runnable r = new Runnable() {

            @Override
            public void run() {
                new BorderLayoutExample();
            }
        };

        SwingUtilities.invokeAndWait(r);
    }

}
