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

    // sets the format for the price so it is represented appropriately
    public static DecimalFormat decimal = new DecimalFormat("0.00");

    // creates and sets a copy of the stock array from the DataLoader class
    public static ArrayList<Stock> stocks = new ArrayList();
    public void setStocks(ArrayList<Stock> stocks){
        this.stocks = stocks;
    }

    public static float currentTotal = 0;

    // sets up the dimensions of the new Kiosk form and loads the stock data into it when called
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

         // resets the number of items in basket to 0
         for(int reset = 0; reset <stocks.size(); reset++){

             stocks.get(reset).setBasketCount(0);
         };

         // opens the Admin form when the admin button is clicked
         adminBtn.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.setVisible(true);
             }
         });

         // opens the PaymentMethod form when the checkout button is clicked
         checkoutBtn.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {

                 // prevents the customer from checking out with an empty basket
                 if (shoppingList.getText().trim().length() == 0) {
                     JOptionPane.showMessageDialog(null, "Basket is empty. Please add items before proceeding to checkout.");
                 }
                 else {
                     PaymentMethod paymentMethod = new PaymentMethod();
                     paymentMethod.setVisible(true);
                 }
             }
         });

         // adds the item to the shopping list if the code is correct
         addBtn.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {

                 // creates a temporary version of the code typed in
                 Stock temp = new Stock();
                 temp.setBarcode(codeTxt.getText());
                 boolean error = false;

                 // if the item code matches a code in the database it will add it to the shoppingList
                 try {
                     if (codeTxt.getText().isEmpty()) {
                         JOptionPane.showMessageDialog(null,"No code entered. Please try again.");
                     }
                         for (int i = 0; i < stocks.size(); i++){

                         // if the code typed in is equal to a product name from the array, it will add it to the basket
                             if (stocks.get(i).getBarcode().equals(temp.getBarcode())) {
                             shoppingList.setText("");
                             float total = stocks.get(i).getPrice();
                             currentTotal = total + currentTotal;
                             totalLbl.setText("£" + String.valueOf(decimal.format(currentTotal)));

                             // if the customer adds more than is in stock, an error message will be displayed
                                if(stocks.get(i).getBasketCount() >= stocks.get(i).getStockCount()) {
                                 JOptionPane.showMessageDialog(null,"You have exceeded the amount of " + temp.getBarcode() + " in stock. You cannot add any more.");
                                }
                                else {
                                    // if not, it will add the item and if it matches an item already in the basket, it will add 1 to the count
                                    int newBasketItem = stocks.get(i).getBasketCount();
                                     stocks.get(i).setBasketCount(newBasketItem + 1);
                                }

                                 for(int x = 0; x < stocks.size(); x++){

                                     if(stocks.get(x).getBasketCount()>0){
                                         shoppingList.append(
                                         stocks.get(x).getProductName()
                                         +" x "
                                         + stocks.get(x).getBasketCount()
                                         +" ...... £"
                                         +decimal.format(stocks.get(x).getBasketCount() * stocks.get(x).getPrice())
                                         +"\n");
                                     }
                                 }
                                 break;
                             }
                         }
                     }
                 catch (IndexOutOfBoundsException exception) {

                 }
             }
         });
     }
}
