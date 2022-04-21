package coderbois.com.oenskebroenen.repository;

import java.sql.*;

public class JdbcConnector {

    private Connection connection;

    public JdbcConnector() {
        String url = System.getenv("db_url");
        String userName = System.getenv("db_username");
        String password = System.getenv("db_password");

        try {
            this.connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Statement getStatement() {
        Statement statement;
        try {
            statement = this.connection.createStatement();
        } catch (SQLException e) {
            //Todo: remove the print stack trace below when we move to production
            e.printStackTrace();
            return null;
        }

        return statement;
    }

    PreparedStatement getPreparedStatement(String sql) {
        PreparedStatement preparedStatement;

        try {
            preparedStatement = this.connection.prepareStatement(sql);
        } catch (SQLException e) {
            //Todo: remove the print stack trace below when we move to production
            e.printStackTrace();
            return null;
        }

        return preparedStatement;
    }
}



