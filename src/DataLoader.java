import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import java.io.*;
import java.util.*;

public class DataLoader {

    public String filePath = "resources\\stockDatabase.txt";
    public String separator = "\\|";

    private final ArrayList<AdminAccounts> users = new ArrayList<>();
    private final ArrayList<Stock> stocks = new ArrayList<>();

    public void adminLoad() {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[]userData = data.split(separator);
                AdminAccounts adminAccounts = new AdminAccounts();
                adminAccounts.setUsername(userData[0]);
                adminAccounts.setPassword(userData[1]);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void stockLoad() {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[]userData = data.split(separator);
                Stock stock = new Stock();
                stock.setProductName(userData[0]);
                int stockCountToInt = Integer.parseInt(userData[1]);
                stock.setStockCount(stockCountToInt);
                int priceToInt = Integer.parseInt(userData[2]);
                stock.setPrice(priceToInt);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // saves a new user to the database
    public void adminSave(){
        try{
            FileWriter writer = new FileWriter(filePath);

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
            FileWriter writer = new FileWriter(filePath);

            for(int i = 0; i < stocks.size(); i++){
                String data = "";

                if(i > 0){
                    data += "\n";
                }

                data += stocks.get(i).getProductName();

                String stockCountToString = Integer.toString(stocks.get(i).getStockCount());
                data += "|" + stockCountToString;

                String priceToString = Integer.toString(stocks.get(i).getPrice());
                data += "|" + priceToString;

                writer.write(data);
            }
            writer.close();
            System.out.println("Stock database successfully saved.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
