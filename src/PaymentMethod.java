import javax.swing.*;
import java.awt.*;

public class PaymentMethod extends JFrame {
    private JButton cashBtn;
    private JButton cardBtn;
    private JPanel paymentmethod;

    public PaymentMethod(){
        setContentPane(paymentmethod);
        setPreferredSize(new Dimension(800,400));
        pack();
    }
}
