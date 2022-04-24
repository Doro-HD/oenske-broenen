package coderbois.com.oenskebroenen.model;

public class Wish {

    private int id;
    private String name;
    private String description;
    private double price;
    private int wishlistId;

    public Wish(String name, String description, double price, int wishlistId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.wishlistId = wishlistId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }
}
