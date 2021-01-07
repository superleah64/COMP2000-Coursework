import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Kiosk extends JFrame{
    private JButton adminBtn;
    private JButton addBtn;
    private JButton removeBtn;
    private JTextArea shoppingList;
    private JButton checkoutBtn;
    private JTextField barcode;
    private JTextArea itemInformation; //displays the information of the scanned item
    private JTextField totalCost;
    private JPanel Kiosk;

     public Kiosk(){
         setContentPane(Kiosk);
         setPreferredSize(new Dimension(800,800));
         pack();

         adminBtn.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.setVisible(true);
             }
         });

         checkoutBtn.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 PaymentMethod paymentMethod = new PaymentMethod();
                 paymentMethod.setVisible(true);
             }
         });
     }
}
