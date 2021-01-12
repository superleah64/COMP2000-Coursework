import com.sun.codemodel.internal.JOp;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;

public class CashPayment extends JFrame {
    private JTextField cashTxt;
    private JPanel cashPanel;
    private JLabel changeLbl;
    private JButton confirmBtn;
    private JButton receiptBtn;
    private JLabel amountLbl;

    public static float changePublic;

    public static Object cashForm;

    public static DecimalFormat decimal = new DecimalFormat("0.00");

    public CashPayment() {
        setContentPane(cashPanel);
        setPreferredSize(new Dimension(800, 400));
        pack();

        amountLbl.setText("£" + String.valueOf(decimal.format(Kiosk.currentTotal)));

        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    // error messages appear if user doesn't insert any money or enough money
                    if(cashTxt.getText().isEmpty() || Kiosk.currentTotal > Float.parseFloat(cashTxt.getText())) {
                        JOptionPane.showMessageDialog(null, "Not enough cash inserted. Please insert more.");
                    }

                    else{
                        // converts inputted amount of cash to float
                        float cash =  Float.parseFloat(cashTxt.getText());

                        // gets the total from the kiosk class
                        float total = Kiosk.currentTotal;

                        // subtracts total from the amount of cash inserted to get the change
                        changePublic = cash - total;

                        changeLbl.setText("£" + String.valueOf(decimal.format(changePublic)));

                        // allows user to click receipt button if the cash payment was successful
                        receiptBtn.setEnabled(true);
                    }
                }
                catch(IndexOutOfBoundsException exception){

                }
            }
        });

        // opens the receipt form when clicked
        receiptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    CashThread cashThread = new CashThread();
                    cashThread.run();
            }
        });
    }
}
