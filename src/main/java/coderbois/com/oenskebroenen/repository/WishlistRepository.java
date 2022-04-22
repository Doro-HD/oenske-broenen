package coderbois.com.oenskebroenen.repository;

import coderbois.com.oenskebroenen.model.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    public ArrayList<Wishlist> getUserWishlistsById(int userId) {
        ArrayList<Wishlist> wishlists = new ArrayList<>();

        Statement statement = this.jdbcConnector.getStatement();
        String sql = "SELECT * FROM wishlists WHERE user_id = " + userId;

        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("wishlist_name");
                String description = resultSet.getString("description");

                Wishlist wishlist = new Wishlist(id, name, description);
                wishlists.add(wishlist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wishlists;
    }
}
