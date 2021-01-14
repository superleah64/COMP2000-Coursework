import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

// this thread is run when the customer pays by cash
// it is the same as the card thread but it also displays the change

public class CashThread extends Thread {

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

        // writes the payment method
        tempText = "Payment Method: Cash";
        receipt.setText(tempText + "\n" + "\n");

        // writes the items from the kiosk array to the receipt
        for (int i = 0; i < Kiosk.stocks.size(); i++) {
            if (Kiosk.stocks.get(i).getBasketCount() !=0){
                tempText = Kiosk.stocks.get(i).getProductName();
                receipt.setText(tempText + " x");
                tempText = String.valueOf(Kiosk.stocks.get(i).getBasketCount());
                receipt.setText(tempText + " ....... ");
                tempText = String.valueOf("£" + decimal.format(Kiosk.stocks.get(i).getPrice()) + "\n");
                receipt.setText(tempText);
            }
        }

        // if an item from the dataLoader array is in the kiosk array, subtract the number of stock bought from the total stock
        for (int i = 0; i < DataLoader.stocks.size(); i++){

            for(int x =0; x < DataLoader.stocks.size(); x++){

                if(DataLoader.stocks.get(i).getBarcode().equals(Kiosk.stocks.get(x).getBarcode())){
                    int bc = Kiosk.stocks.get(x).getBasketCount();
                    int sc = DataLoader.stocks.get(i).getStockCount();

                    DataLoader.stocks.get(i).setStockCount(sc-bc);
                    Kiosk.stocks.get(x).setBasketCount(0);
                }
            }
        }

        // write the new item to the text file
        try{
            FileWriter fileWriter = new FileWriter(DataLoader.stockPath);

            for(int i = 0; i < DataLoader.stocks.size(); i++) {

                String data = "";

                if (i > 0) {
                    data += "\n";
                }

                data += DataLoader.stocks.get(i).getProductName();

                String stockCountToString = Integer.toString(DataLoader.stocks.get(i).getStockCount());
                data += "|" + stockCountToString;

                String priceToString = Float.toString(DataLoader.stocks.get(i).getPrice());
                data += "|" + priceToString;

                data += "|" + DataLoader.stocks.get(i).getBarcode();

                fileWriter.write(data);
            }
            fileWriter.close();

            System.out.println("Stock database successfully saved.");

        } catch (IOException e) {
            e.printStackTrace();
        }

        tempText = "\n" + "\n" + "Total: ................... £" + String.valueOf(decimal.format(Kiosk.currentTotal)) + "\n";
        receipt.setText(tempText);
        tempText = "Change Given: ...... £" + String.valueOf(decimal.format(CashPayment.changePublic)) + "\n";
        receipt.setText(tempText);
    }
}
