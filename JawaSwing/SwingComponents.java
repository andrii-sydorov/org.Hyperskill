package JawaSwing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class SwingComponents extends JFrame {

    public SwingComponents() {
        
        super("Hello App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        
        initComponents();
        
        setLocationRelativeTo(null);
        setLayout(null);

        setVisible(true);
    }

    private void initComponents() {
        JLabel nameLabel = new JLabel();
        nameLabel.setText("Your name");
        nameLabel.setBounds(40, 20, 100, 30);
        add(nameLabel);

        JTextField nameTextField = new JTextField();
        nameTextField.setBounds(160, 20, 100, 30);
        add(nameTextField);

        JPanel greenPanel = new JPanel();
        greenPanel.setBounds(40, 150, 220, 70);
        greenPanel.setLayout(new BorderLayout());
        greenPanel.setBackground(Color.GREEN);
        add(greenPanel);

        JLabel helloLabel = new JLabel("Hello");
        helloLabel.setBounds(50, 20, 100, 30);
        helloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        helloLabel.setVerticalAlignment(SwingConstants.CENTER);

        Font f = new Font("Courier", Font.BOLD, 12);
        helloLabel.setFont(f);
        helloLabel.setFont(helloLabel.getFont().deriveFont(16f));
        greenPanel.add(helloLabel);

        JButton acceptButton = new JButton("Accept");
        acceptButton.setBounds(100, 70, 100, 30);
        add(acceptButton);

        acceptButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String helloText = "Hello";
                String name = nameTextField.getText();
                if (name != null && name.trim().length() > 0) {
                    helloText += String.format(", %s", name);
                }
                helloLabel.setText(helloText);
            }
        });
    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        Runnable r = new Runnable() {

            @Override
            public void run() {
                new SwingComponents();
            }
        };

        SwingUtilities.invokeAndWait(r);
    }

}
