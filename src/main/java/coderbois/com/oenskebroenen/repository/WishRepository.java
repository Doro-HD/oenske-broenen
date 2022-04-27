package coderbois.com.oenskebroenen.repository;

import coderbois.com.oenskebroenen.model.Wish;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            preparedStatement.setInt(4, wish.getWishlistId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Wish> findWishesByWishlistId(int wishlistId) {
        ArrayList<Wish> wishes = new ArrayList<>();

        Statement statement = this.jdbcConnector.getStatement();

        if (statement == null) {
            throw new RuntimeException("sql error, perhaps connection");
        }
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM wishes WHERE wishlist_id = " + wishlistId);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("wish_name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int currentWishlistId = resultSet.getInt("wishlist_id");

                Wish wish = new Wish(id, name, description, price, currentWishlistId);
                wishes.add(wish);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return wishes;
    }

    public void deleteWishById(int id){

        final String SQL = "DELETE FROM wishes WHERE id = ?";
        PreparedStatement preparedStatement = this.jdbcConnector.getPreparedStatement(SQL);


        try{
            preparedStatement.setInt(1, id);;

            preparedStatement.executeUpdate();


        }catch (SQLException e){
            System.out.println("Noget gik galt ved sletning af ønsket");
            System.out.println(e);
        }
    }
}
