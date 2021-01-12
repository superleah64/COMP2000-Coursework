import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


// this thread is run when the customer pays by card
// it is the same as the cash thread but does not display change

public class CardThread extends Thread {

    public static DecimalFormat decimal = new DecimalFormat("0.00");

    String tempText;

    public void run(){
        Receipt receipt = new Receipt();
        receipt.setVisible(true);

        // receives the current date and time
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy | HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        tempText = dateTimeFormatter.format(now);
        receipt.setText(tempText + "\n");
        tempText = "Payment Method: Card";
        receipt.setText(tempText + "\n" + "\n");

        for (int i = 0; i < Kiosk.stocks.size(); i++) {
            if (Kiosk.stocks.get(i).getBasketCount() !=0){
                tempText = Kiosk.stocks.get(i).getProductName();
                receipt.setText(tempText + " x");
                tempText = String.valueOf(Kiosk.stocks.get(i).getStockCount());
                receipt.setText(tempText + " ....... ");
                tempText = String.valueOf("£" + decimal.format(Kiosk.stocks.get(i).getPrice()) + "\n");
                receipt.setText(tempText);
            }
        }

        tempText = "\n" + "\n" + "Total: ................... £" + String.valueOf(decimal.format(Kiosk.currentTotal)) + "\n";
        receipt.setText(tempText);
    }
}
