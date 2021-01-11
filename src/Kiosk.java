import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

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

         for(int reset = 0; reset <stocks.size(); reset++){

             stocks.get(reset).setBasketcount(0);
             


         };

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




                             shoppingList.setText("");

                             float total = stocks.get(i).getPrice();


                             currentTotal = total + currentTotal;


                             totalLbl.setText("£" + String.valueOf(decimal.format(currentTotal)));





                                if(stocks.get(i).getBasketcount() >= stocks.get(i).getStockCount()){

                                    JOptionPane.showInputDialog(null,"no more in stock");

                                }else{

                                    int newbasketitem = stocks.get(i).getBasketcount();
                                    stocks.get(i).setBasketcount(newbasketitem+1);
                                }





                                for(int x = 0; x < stocks.size(); x++){

                                    if(stocks.get(x).getBasketcount()>0){

                                        shoppingList.append(

                                                stocks.get(x).getProductName()
                                                +" x "
                                                + stocks.get(x).getBasketcount()
                                                +" ...... £"
                                                +decimal.format(stocks.get(x).getBasketcount() * stocks.get(x).getPrice())
                                                +"\n"
                                        );

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
