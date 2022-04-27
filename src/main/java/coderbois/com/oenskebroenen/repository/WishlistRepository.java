package coderbois.com.oenskebroenen.repository;

import coderbois.com.oenskebroenen.model.Wishlist;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Repository
public class WishlistRepository {

    private final JdbcConnector jdbcConnector;

    public WishlistRepository() {
        this.jdbcConnector = new JdbcConnector();
    }

    public ArrayList<Wishlist> findWishlistsByUserId(int userId) {
        String SQL = "SELECT * FROM wishlists WHERE user_id = " + userId;
        return this.findWishlists(SQL);
    }

    public Wishlist findWishlistByUserIdAndWishlistId(int userId, int wishlistId) {
        final String SQL = "SELECT * FROM wishlists WHERE user_id = " + userId + " AND id = " + wishlistId;
        ArrayList<Wishlist> wishlists = this.findWishlists(SQL);

        Wishlist wishlist = null;
        if (!wishlists.isEmpty()) {
            wishlist = wishlists.get(0);
        }

        return wishlist;
    }

    private ArrayList<Wishlist> findWishlists(String sql) {
        ArrayList<Wishlist> wishlists = new ArrayList<>();

        Statement statement = this.jdbcConnector.getStatement();

        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("wishlist_name");
                String description = resultSet.getString("description");
                int userId = resultSet.getInt("user_id");

                Wishlist wishlist = new Wishlist(id, name, description, userId);
                wishlists.add(wishlist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wishlists;
    }

    public void createWishList(Wishlist wishlist){
        try{

            //prepared statement
            PreparedStatement preparedStatement = jdbcConnector.getPreparedStatement("INSERT INTO wishlists(wishlist_name, description, user_id) VALUES (?, ?, ?)");

            //set attributter
            preparedStatement.setString(1, wishlist.getName());
            preparedStatement.setString(2, wishlist.getDescription());
            preparedStatement.setString(3, String.valueOf(wishlist.getUserId()));

            //Execute statement
            preparedStatement.executeUpdate();

        } catch (SQLException e){

            System.out.println("Something went wrong in create Wishlist");
            e.printStackTrace();
        }
    }


    public void deleteWishlistById(int id){

        final String SQL = "DELETE FROM wishlists WHERE id = ?";

        PreparedStatement preparedStatement = this.jdbcConnector.getPreparedStatement(SQL);

        try{
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            System.out.println("Noget gik galt ved sletning af ønskelisten");
            System.out.println(e);
        }

    }

}
