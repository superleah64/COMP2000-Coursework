import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Admin extends JFrame {
    private JPanel mainPanel;
    private JButton editBtn;
    private JButton addBtn;
    private JButton removeBtn;
    private JPanel addPanel;
    private JPanel editPanel;
    private JTextField addNameTxt;
    private JTextField addCountTxt;
    private JTextField editNameTxt;
    private JTextField editPriceTxt;
    private JTextField removeTxt;
    private JPanel removePanel;
    private JButton addSaveBtn;
    private JButton editSaveBtn;
    private JButton removeSaveBtn;
    private JTextField editBarcodeTxt;
    private JTextField addPriceTxt;
    private JTextField addBarcodeTxt;
    private JButton orderBtn;
    private JPanel orderPanel;
    private JTextField orderBarcodeTxt;
    private JTextField orderStockTxt;
    private JButton orderSaveBtn;

    public static DecimalFormat decimal = new DecimalFormat("0.00");

    public static ArrayList<Stock> stockAdmin = new ArrayList();

    public static ArrayList<Stock> getStockAdmin() {

        return stockAdmin;
    }

    public void setStockArray(ArrayList<Stock> arrayList){
        this.stockAdmin = arrayList;
    }

    String tempName;
    String tempStock;
    float tempPrice;
    String tempBarcode;

    public Admin(){

        setContentPane(mainPanel);
        setPreferredSize(new Dimension(800,400));
        pack();

        setStockArray(DataLoader.stocks);
        System.out.println("stop");

        // displays a message if an item has run out of stock
        for (int i = 0; i < Kiosk.stocks.size(); i++) {
            if (Kiosk.stocks.get(i).getStockCount() == 0) {
                JOptionPane.showMessageDialog(null, "Please order more " + Kiosk.stocks.get(i).getProductName() + " as we are out of stock.");
            }
        }

        // makes the add panel visible if the add button is clicked
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(addPanel);
                setPreferredSize(new Dimension(400,200));
                addPanel.setVisible(true);
                pack();
            }
        });

        orderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(orderPanel);
                setPreferredSize(new Dimension(400,200));
                orderPanel.setVisible(true);
                pack();
            }
        });

        // makes the edit panel visible if the edit button is clicked
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(editPanel);
                setPreferredSize(new Dimension(400,200));
                editPanel.setVisible(true);
                pack();
            }
        });

        // makes the remove panel visible if the remove button is clicked
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(removePanel);
                setPreferredSize(new Dimension(400,200));
                removePanel.setVisible(true);
                pack();
            }
        });

        // sets the properties for the new item and runs the save method to write to the file
        addSaveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                tempName = addNameTxt.getText();
                tempStock = addCountTxt.getText();
                tempPrice = Float.parseFloat(addPriceTxt.getText());
                tempBarcode = addBarcodeTxt.getText();

                Stock stock = new Stock();
                stock.setProductName(tempName);
                stock.setStockCount(Integer.parseInt(tempStock));
                stock.setPrice(tempPrice);
                stock.setBarcode(tempBarcode);

                stockAdmin.add(stock);

                JOptionPane.showMessageDialog(null,"Item successfully added.");
                stockSave();
            }
        });

        // orders new stock of an existing item and writes back to the text file
        orderSaveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < stockAdmin.size(); i++){

                        if (DataLoader.stocks.get(i).getBarcode().equals(orderBarcodeTxt.getText())){

                            int newStock = Integer.parseInt(orderStockTxt.getText());
                            int currentStock = DataLoader.stocks.get(i).getStockCount();

                            stockAdmin.get(i).setStockCount(currentStock + newStock);
                        }
                    }
                JOptionPane.showMessageDialog(null,"Stock added successfully.");
                stockSave();
            }
        });

        // edits existing item and writes back to the text file
        editSaveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < stockAdmin.size(); i++){

                    if (DataLoader.stocks.get(i).getBarcode().equals(editBarcodeTxt.getText())){


                        stockAdmin.get(i).setProductName(editNameTxt.getText());
                        stockAdmin.get(i).setPrice(Float.parseFloat(String.valueOf(editPriceTxt.getText())));
                        break;
                    }
                }
                JOptionPane.showMessageDialog(null,"Item edited successfully.");
                stockSave();
            }
        });

        // removes selected item from the array and writes back to the text file
        removeSaveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < stockAdmin.size(); i++){

                    if (DataLoader.stocks.get(i).getBarcode().equals(removeTxt.getText())){
                        stockAdmin.remove(stockAdmin.get(i));
                        break;
                    }
                }
                JOptionPane.showMessageDialog(null,"Item removed successfully.");
                stockSave();
            }
        });
    }

    // writes new items to the text file
    public static void stockSave(){
        try{
            FileWriter fileWriter = new FileWriter(DataLoader.stockPath);

            for(int i = 0; i < stockAdmin.size(); i++) {

                String data = "";

                if (i > 0) {
                    data += "\n";
                }

                data += stockAdmin.get(i).getProductName();

                String stockCountToString = Integer.toString(stockAdmin.get(i).getStockCount());
                data += "|" + stockCountToString;

                String priceToString = Float.toString(stockAdmin.get(i).getPrice());
                data += "|" + priceToString;

                data += "|" + stockAdmin.get(i).getBarcode();

                fileWriter.write(data);
            }
            fileWriter.close();

            System.out.println("Stock database successfully saved.");

        } catch (IOException e) {
            e.printStackTrace();
        }

        DataLoader newLoader = new DataLoader();
        newLoader.setStock(stockAdmin);
    }
}
