package coderbois.com.oenskebroenen.model;

public class Wishlist {
      private int id;
      private String name;
      private String description;
      private int userId;



      public Wishlist(){

      }

      public Wishlist(String name, String description, int userId){
            this.name = name;
            this.description = description;
            this.userId = userId;
      }

      public Wishlist(int id,String name, String description, int userId){
            this.id = id;
            this.name = name;
            this.description = description;
            this.userId = userId;
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

      public int getUserId() {
            return userId;
      }

      public void setUserId(int userId) {
            this.userId = userId;
      }

      public void setDescription(String description) {
            this.description = description;
      }

}
