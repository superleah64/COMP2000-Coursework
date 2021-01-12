import javax.swing.*;
import java.awt.*;

public class Receipt extends JFrame{

    private JPanel receipt;
    private JTextArea receiptArea;

    public void setText(String text){
        receiptArea.append(text);
    }

    // if cashBtn was clicked, display change
    public Receipt() {
        setContentPane(receipt);
        setPreferredSize(new Dimension(300, 600));
        pack();
    }
}
