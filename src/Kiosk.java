import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Kiosk extends JFrame{
    private JButton adminBtn;
    private JButton addBtn;
    private JButton removeBtn;
    private JTextArea shoppingList;
    private JButton checkoutBtn;
    private JTextField codeTxt;
    private JLabel totalLbl;
    private JPanel Kiosk;

    public ArrayList<Stock> stocks = new ArrayList();

    public void setStocks(ArrayList<Stock> stocks){
        this.stocks = stocks;
    }

    float currentTotal = 0;

     public Kiosk(){
         setContentPane(Kiosk);
         setPreferredSize(new Dimension(800,400));
         pack();
         DataLoader stock = new DataLoader();
         stock.stockLoad();
         setStocks(stock.getStocks());

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

         addBtn.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {

                 DataLoader data = new DataLoader();
                 data.stockLoad();

                 setStocks(data.getStocks());

                 Stock temp = new Stock();
                 temp.setProductName(codeTxt.getText());

                 try {
                     for (int i = 0; i < stocks.size(); i++){
                         if (stocks.get(i).getProductName().equals(temp.getProductName())){
                             // add to jTextArea
                             shoppingList.append(temp.getProductName()+"\n");
                             float total = stocks.get(i).getPrice();
                             total = total + currentTotal;
                             totalLbl.setText(String.valueOf(currentTotal));
                         }
                     }
                 }
                 catch (IndexOutOfBoundsException exception) {
                     System.out.println("Invalid code. Please try again.");
                 }
             }
         });
     }
}
