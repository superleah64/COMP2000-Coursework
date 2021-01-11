import java.util.Currency;

public class Stock {
    private String productName;
    private String barcode;
    private float price;
    private int stockCount;
    private int basketcount;

    public int getBasketcount(){
        return  basketcount;
    }

    public void setBasketcount(int basketcount){
        this.basketcount = basketcount;
    }

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

    public float getPrice(){
        return price;
    }

    public void setPrice(float price){
        this.price = price;
    }
}
