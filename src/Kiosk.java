import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Kiosk extends JFrame{
    private JButton adminBtn;
    private JButton addBtn;
    private JTextArea shoppingList;
    private JButton checkoutBtn;
    private JTextField codeTxt;
    private JLabel totalLbl;
    private JPanel Kiosk;

    public static DecimalFormat decimal = new DecimalFormat("0.00");

    public ArrayList<Stock> stocks = new ArrayList();
    public ArrayList<itemCount> itemCounts = new ArrayList();

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
         DataLoader data = new DataLoader();
         data.stockLoad();
         codeTxt.grabFocus();

         setStocks(data.getStocks());

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



                 Stock temp = new Stock();
                 temp.setProductName(codeTxt.getText());

                 try {
                     for (int i = 0; i < stocks.size(); i++){
                         if (stocks.get(i).getProductName().equals(temp.getProductName())) {
                             // add to jTextArea

                             float total = stocks.get(i).getPrice();

                             shoppingList.append(temp.getProductName() + " ..... £" + decimal.format(total) + "\n");
                             currentTotal = total + currentTotal;


                             totalLbl.setText("£" + String.valueOf(decimal.format(currentTotal)));


                             itemCount tempitem = new itemCount();
                             tempitem.setStock(stocks.get(i));
                             tempitem.setItemCount(0);
                             itemCounts.add(tempitem);


                             for (int x = 0; x < itemCounts.size(); x++) {

                                 if ((itemCounts.get(x).getStock().getProductName().equals(stocks.get(i).getProductName()))) {

                                     int currentitemcount = itemCounts.get(x).getItemCount();

                                     itemCounts.remove(itemCounts.get(x));

                                     itemCount newtempitem = new itemCount();
                                     newtempitem.setStock(stocks.get(i));
                                     newtempitem.setItemCount(0);
                                     itemCounts.add(newtempitem);

                                     itemCounts.get(x).setItemCount(currentitemcount + 1);

                                     System.out.println(itemCounts.get(x).getItemCount());
                                     break;


                                 }



                             }

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
