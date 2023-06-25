package JawaSwing.WindowListener;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CheckExitExample extends JFrame {

    public CheckExitExample() {
        super("Window Listener");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new CheckOnExit());
        setVisible(true);
    }

    public static void main(String[] args) {
        CheckExitExample demoWindow = new CheckExitExample();
        demoWindow.setVisible(true);
    }

    private class CheckOnExit extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            ConfirmWindow checker = new ConfirmWindow();
            checker.setVisible(true);
        }
    }

    private class ConfirmWindow extends JFrame implements ActionListener {

        public ConfirmWindow() {
            setSize(250, 100);
            setLayout(new BorderLayout());

            JLabel confirmLabel = new JLabel(
                    "Are you sure you want to exit?", SwingConstants.CENTER);
            add(confirmLabel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());

            JButton exitButton = new JButton("Yes");
            exitButton.addActionListener(this);
            buttonPanel.add(exitButton);

            JButton cancelButton = new JButton("No");
            cancelButton.addActionListener(this);
            buttonPanel.add(cancelButton);

            add(buttonPanel, BorderLayout.SOUTH);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            String action = e.getActionCommand();
            if (action.equals("Yes")) {
                System.exit(0);
            } else if (action.equals("No")) {
                dispose();
            }
        }

    }

}

/**
 * Count a window active time
 * Override a method of ActiveTimeCounterWindowAdapter that counts a number of
 * window activations.
 * 
 * To store the number use activationCounter field.
 */
class ActiveTimeCounterWindowAdapter extends WindowAdapter {
    private long activationCounter = 0; // do not change it

    public void windowActivated(WindowEvent e) {
        activationCounter++;
    }
}
