import java.util.Currency;

public class Stock {
    private String productName;
    private String barcode;
    private Currency price;
    private int stockCount;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Currency getPrice(){
        return price;
    }

    public void setPrice(Currency price){
        this.price = price;
    }
}
