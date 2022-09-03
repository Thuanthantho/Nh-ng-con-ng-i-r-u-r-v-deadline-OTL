public class Items {
    private String proID;
    private String title;
    private float price;
    private String category;

    public Items(String proID, String title, float price, String category) {
        this.proID = proID;
        this.title = title;
        this.price = price;
        this.category = category;
    }

    public String getProID() {
        return proID;
    }

    public void setProID(String proID) {
        this.proID = proID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Items{" +
                "proID='" + proID + '\'' +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
