import java.util.Currency;

public class Stock {
    private String productName;
    private float price;
    private int stockCount;
    private int basketCount;

    // getters and setters for the stock details

    public int getBasketCount(){
        return  basketCount;
    }

    public void setBasketCount(int basketcount){
        this.basketCount = basketcount;
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
