import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Admin extends JFrame {
    private JPanel mainPanel;
    private JButton editBtn;
    private JButton orderBtn;
    private JButton removeBtn;
    private JPanel orderPanel;
    private JPanel editPanel;
    private JTextField addNameTxt;
    private JTextField addCountTxt;
    private JTextField editNameTxt;
    private JTextField editCountTxt;
    private JTextField editPriceTxt;
    private JTextField removeTxt;
    private JPanel removePanel;
    private JTextArea stockDatabase;
    private JButton orderSaveBtn;
    private JButton editSaveBtn;
    private JButton removeSaveBtn;

    public static DecimalFormat decimal = new DecimalFormat("0.00");

    public Admin(){
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(800,400));
        pack();

        for (int i = 0; i < Kiosk.stocks.size(); i++) {
            if (Kiosk.stocks.get(i).getStockCount() == 0) {
                JOptionPane.showMessageDialog(null, "Please order more " + Kiosk.stocks.get(i).getProductName() + " as we are out of stock.");
            }
        }

        for (int i = 0; i < Kiosk.stocks.size(); i++){
            stockDatabase.append(
            Kiosk.stocks.get(i).getProductName() + " | " +
            Kiosk.stocks.get(i).getStockCount() + " | " + "£" +
            decimal.format(Kiosk.stocks.get(i).getPrice()) + "\n"
            );
        }

        orderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(orderPanel);
                setPreferredSize(new Dimension(400,200));
                orderPanel.setVisible(true);
                pack();

                Stock temp = new Stock();
                temp.setProductName(addNameTxt.getText());

                for (int i = 0; i < Kiosk.stocks.size(); i++){
                    if (Kiosk.stocks.get(i).getProductName().equals(temp.getProductName())) {

                    }
                }
            }
        });
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(editPanel);
                setPreferredSize(new Dimension(400,200));
                editPanel.setVisible(true);
                pack();
            }
        });
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(removePanel);
                setPreferredSize(new Dimension(400,200));
                removePanel.setVisible(true);
                pack();
            }
        });
    }
}
