package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static utils.Constants.*;

public class ConnectionDB {
    public static Connection getConnection() {

        Connection connection = null;

        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager
                    .getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return connection;
    }
}