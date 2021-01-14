import java.io.*;
import java.util.*;

// a class for creating arrays and loading them into files when called
public class DataLoader {

    public static String adminPath = "src/resources/Login.txt";
    public static String stockPath = "src/resources/stockDatabase.txt";
    public static String separator = "\\|";

    // creates arraylists for the admin and the stock
    private static ArrayList<AdminAccounts> users = new ArrayList<>();
    public static ArrayList<Stock> stocks = new ArrayList<>();

    // gets and sets those arrays
    public static ArrayList<AdminAccounts> getUsers() {
        return users;
    }
    public static ArrayList<Stock> getStocks(){
        return stocks;
    }

    public void setStock(ArrayList<Stock> stock){
        this.stocks = stock;
    }

    // loads the users array into the new file
    public void adminLoad() {
        try {
            File file = new File(adminPath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[]userData = data.split(separator);
                AdminAccounts adminAccounts = new AdminAccounts();
                adminAccounts.setUsername(userData[0]);
                adminAccounts.setPassword(userData[1]);
                users.add(adminAccounts);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // loads the stock array into the new file
    public void stockLoad() {
        try {
            File file = new File(stockPath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] userData = data.split(separator);
                Stock stock = new Stock();
                stock.setProductName(userData[0]);
                int stockCountToInt = Integer.parseInt(userData[1]);
                stock.setStockCount(stockCountToInt);
                float priceToFloat = Float.parseFloat(userData[2]);
                stock.setPrice(priceToFloat);
                stock.setBarcode(userData[3]);

                stocks.add(stock);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Stock getStockAt(int index) {
        if (index >= users.size()) {
            return null;
        }
        return stocks.get(index);
    }
}
