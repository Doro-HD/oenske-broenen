package coderbois.com.oenskebroenen.repository;

import coderbois.com.oenskebroenen.model.Wish;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Repository
public class WishRepository {

    private JdbcConnector jdbcConnector;

    public WishRepository() {
        this.jdbcConnector = new JdbcConnector();
    }

    public void createWish(Wish wish) {
        final String SQL = "INSERT INTO wishes (wish_name, description, price, wishlist_id) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = this.jdbcConnector.getPreparedStatement(SQL);

        if (preparedStatement == null) {
            throw new RuntimeException("sql error, perhaps connection");
        }

        try {
            preparedStatement.setString(1, wish.getName());
            preparedStatement.setString(2, wish.getDescription());
            preparedStatement.setDouble(3, wish.getPrice());
            preparedStatement.setInt(4, wish.getWishlistId();

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Wish> findWishesByWishlistId(int id) {
        ArrayList<Wish> wishes = new ArrayList<>();

        Statement statement = this.jdbcConnector.getStatement();

    }
}
