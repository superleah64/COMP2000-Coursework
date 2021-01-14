import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardVerification extends JFrame {
    private JPanel verification;
    private JButton acceptBtn;
    private JButton denyBtn;

    public static Object cardForm;

    public CardVerification() {
        setContentPane(verification);
        setPreferredSize(new Dimension(800, 400));
        pack();
        acceptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // subtract the basketcount from the stockcount and write the new stockcount back to the text file

                JOptionPane.showMessageDialog(null,"Transaction complete. Your receipt will now be printed.");
                CardThread cardThread = new CardThread();
                cardThread.run();
            }
        });
        denyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Transaction cancelled. You will be returned to the start screen.");
                Start start = new Start();
                start.setVisible(true);
            }
        });
    }
}
