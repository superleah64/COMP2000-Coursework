import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentMethod extends JFrame {
    private JButton cashBtn;
    private JButton cardBtn;
    private JPanel paymentMethod;

    public PaymentMethod(){
        setContentPane(paymentMethod);
        setPreferredSize(new Dimension(800,400));
        pack();

        // opens the bank verification screen when the card button is clicked
        cardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardVerification verification = new CardVerification();
                verification.setVisible(true);
            }
        });

        // opens the cash form when the cash button is clicked
        cashBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CashPayment cashPayment = new CashPayment();
                cashPayment.setVisible(true);
            }
        });
    }
}
