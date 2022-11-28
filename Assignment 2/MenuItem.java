public class MenuItem {
    private String ItemName;
    private int Category;
    private Double Price;
    private boolean HeartHealthy;

    public MenuItem(String ItemName, int Category, Double Price, boolean HeartHealthy) {
        this.ItemName = ItemName;
        this.Category = Category;
        this.Price = Price;
        this.HeartHealthy = HeartHealthy;
    }

    public String getItemName() {
        return ItemName;
    }

    public int getCategory() {
        return Category;
    }

    public Double getPrice() {
        return Price;
    }

    public boolean getHeartHealthy() {
        return HeartHealthy;
    }
}
