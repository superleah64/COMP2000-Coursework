import java.io.*;
import java.util.*;

public class DataLoader {

    public String adminPath = "/Users/leahhumphries/Documents/GitHub/COMP2000-Coursework/resources/Login.txt";
    public String stockPath = "/Users/leahhumphries/Documents/GitHub/COMP2000-Coursework/resources/stockDatabase.txt";
    public String separator = "\\|";

    private ArrayList<AdminAccounts> users = new ArrayList<>();
    public ArrayList<Stock> stocks = new ArrayList<>();

    public ArrayList<AdminAccounts> getUsers() {
        return users;
    }
    public ArrayList<Stock> getStocks(){
        return stocks;
    }

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
    public void stockLoad() {
        try {
            File file = new File(stockPath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[]userData = data.split(separator);
                Stock stock = new Stock();
                stock.setProductName(userData[0]);
                int stockCountToInt = Integer.parseInt(userData[1]);
                stock.setStockCount(stockCountToInt);
                float priceToFloat = Float.parseFloat(userData[2]);
                stock.setPrice(priceToFloat);
                stocks.add(stock);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("data loaded");
    }

    // saves a new user to the database
    public void adminSave(){
        try{
            FileWriter writer = new FileWriter(adminPath);

            for(int i = 0; i < users.size(); i++){
                String data = "";

                if(i > 0){
                    data += "\n";
                }

                data += users.get(i).getUsername();
                data += users.get(i).getPassword();

                writer.write(data);
            }
            writer.close();
            System.out.println("Admin database successfully saved.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stockSave(){
        try{
            FileWriter writer = new FileWriter(stockPath);

            for(int i = 0; i < stocks.size(); i++){
                String data = "";

                if(i > 0){
                    data += "\n";
                }

                data += stocks.get(i).getProductName();

                String stockCountToString = Integer.toString(stocks.get(i).getStockCount());
                data += "|" + stockCountToString;

                String priceToString = Float.toString(stocks.get(i).getPrice());
                data += "|" + priceToString;

                writer.write(data);
            }
            writer.close();
            System.out.println("Stock database successfully saved.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AdminAccounts getUserAt(int index) {
        if (index >= users.size()) {
            return null;
        }
        return users.get(index);
    }
}
