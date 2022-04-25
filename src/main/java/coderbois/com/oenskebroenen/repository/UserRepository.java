package coderbois.com.oenskebroenen.repository;

import coderbois.com.oenskebroenen.model.User;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class UserRepository {

    private final JdbcConnector jdbcConnector;

    public UserRepository(){
        this.jdbcConnector = new JdbcConnector();
    }



    public ArrayList<User> getAllUsers(){
        ArrayList<User> ourUsers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM users";
            ResultSet resultSet = this.jdbcConnector.getStatement().executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("username");
                String password = resultSet.getString("user_password");
                ourUsers.add(new User(id, userName, password));
            }
        }catch (Exception e) {
            //Todo: remove print stack trace when moving to production
            e.printStackTrace();
        }
        return ourUsers;

    }

    public void createUser(User user) {
        try{

            //prepared statement
            PreparedStatement preparedStatement = this.jdbcConnector.getPreparedStatement(
                    "INSERT INTO users(username, user_password) VALUES (?, ?)");

            //set attributter
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            //execute statement
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            System.out.println("Something went wrong in createUser");
            //Todo: remove the print stack trace below when we move to production
            e.printStackTrace();
        }
    }

    public User findUser(String userString){
        User myUser = null;
        try {
            String sql = "SELECT * FROM users WHERE username = " + "\"" + userString + "\"";
            ResultSet resultSet = this.jdbcConnector.getStatement().executeQuery(sql);
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("username");
                String password = resultSet.getString("user_password");
                myUser = new User(id, userName, password);
            }
        }catch (SQLException e) {
            System.out.println("Error in getting all users.");
            //Todo: remove the print stack trace below when we move to production
            e.printStackTrace();
        }
        return myUser;

    }

}
