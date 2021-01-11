import javax.swing.*;
import java.awt.*;

public class Receipt extends JFrame{

    private JPanel receipt;
    private JList receiptList;

    // if cashBtn was clicked, display change
    public Receipt() {
        setContentPane(receipt);
        setPreferredSize(new Dimension(300, 600));
        pack();

        // in the receiptList display the item name, how many of that item was purchased, the price, the change if necessary, and the total
    }
}
