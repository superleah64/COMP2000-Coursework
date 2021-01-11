import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CashPayment extends JFrame {
    private JTextField cashTxt;
    private JPanel cashPanel;
    private JLabel changeLbl;
    private JButton confirmBtn;
    private JButton receiptBtn;

    public static DecimalFormat decimal = new DecimalFormat("0.00");

    public CashPayment() {
        setContentPane(cashPanel);
        setPreferredSize(new Dimension(800, 400));
        pack();

        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cashTxt.equals("")) {
                    JOptionPane.showMessageDialog(null,"No cash inserted. Please insert cash before continuing.");
                }
                else{
                    // converts inputted amount of cash to float
                    float cash =  Float.parseFloat(cashTxt.getText());

                    // gets the total from the kiosk class
                    float total = Kiosk.currentTotal;

                    // subtracts total from the amount of cash inserted to get the change
                    float change = cash - total;

                    changeLbl.setText("£" + String.valueOf(decimal.format(change)));

                   // if (total > cashTxt) {
                   //  JOptionPane.showMessageDialog(null, "Not enough cash inserted. Please insert more.");
                   //  }
                }
            }
        });
        receiptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (confirmBtn.getModel().isPressed())
                {
                    Receipt receipt = new Receipt();
                    receipt.setVisible(true);
                }
            }
        });
    }
    // if cashTxt >= total, confirmBtn.enabled(true);
}
