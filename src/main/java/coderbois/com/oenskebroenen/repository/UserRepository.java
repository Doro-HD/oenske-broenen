package coderbois.com.oenskebroenen.repository;

import coderbois.com.oenskebroenen.model.User;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.util.ArrayList;

@Repository
public class UserRepository {

    private final JdbcConnector myConnector;

    public UserRepository(){
        this.myConnector = new JdbcConnector();
    }

    public ArrayList<User> getAllUsers(){
        ArrayList<User> ourUsers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM users";
            ResultSet resultSet = this.myConnector.getStatement().executeQuery(sql);
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


    public User findUser(String userString){
        User myUser = null;
        try {
            String sql = "SELECT * FROM users WHERE username = " + "\"" + userString + "\"";
            ResultSet resultSet = this.myConnector.getStatement().executeQuery(sql);
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("username");
                String password = resultSet.getString("user_password");
                myUser = new User(id, userName, password);
                System.out.println(myUser);
            }
        }catch (Exception e) {
            System.out.println("Error in getting all users.");
            e.printStackTrace();
        }
        return myUser;

    }

}
