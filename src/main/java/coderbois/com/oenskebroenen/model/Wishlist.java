package coderbois.com.oenskebroenen.model;

public class Wishlist {
      private int id;

      private int wishlistId;
      private String name;
      private String description;
      private int price;

      public Wishlist(){

      }

      public Wishlist(int id, int wishlistId, String name, String description, int price){
            this.id = id;
            this.wishlistId = wishlistId;
            this.name = name;
            this.description = description;
            this.price = price;
      }

      public int getId() {
            return id;
      }

      public void setId(int id) {
            this.id = id;
      }

      public int getWishlistId() {
            return wishlistId;
      }

      public void setWishlistId(int wishlistId) {
            this.wishlistId = wishlistId;
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

      public int getPrice() {
            return price;
      }

      public void setPrice(int price) {
            this.price = price;
      }
}
