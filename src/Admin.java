import javax.swing.*;
import java.awt.*;

public class Admin extends JFrame {
    private JTextArea stockDatabase;
    private JPanel mainPanel;
    private JButton orderBtn;
    private JButton editBtn;
    private JButton addBtn;
    private JTextField nameTxt;
    private JTextField countTxt;
    private JTextField priceTxt;
    private JButton removeBtn;

    public Admin(){
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(800,800));
        pack();
    }

//    editBtn.addActionListener(
//            new ActionListener(){
//                @Override
//                        public void actionPerformed(ActionEvent e){
//                    String newName = nameTxt.getText();
//
//        }
//     }
//    );

    // if ( text is selected in stockDatabase
    // orderBtn.setEnabled(true)
    // );

    // a popup window will appear asking how many you want
    // the database text file will be updated to represent how many you bought

    // if (stockNo = 0
    //  System.out.println("[itemName] is out of stock. Please replenish the stock.");
    // );

}
