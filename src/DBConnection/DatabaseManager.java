package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DatabaseManager class contains operations for database interaction such as connecting, closing and executing
 * statements to a database. All defined methods are public and static, therefore allowing them to be used
 * without requiring separate DatabaseManager or Connection objects wherever an database connection is used.
 */
public class DatabaseManager {

    /**
     * Creates a connection to the database. Other classes have the ability to utilize this method without instantiation.
     *
     * @return a reference to the Connection object used to access the database.
     */
    public static Connection getConnection() {
        Connection Connection = null;
        try {
            //Get the driver
            Class.forName("org.postgresql.Driver");

            //Create connection object
            Connection = DriverManager.getConnection
                    ("jdbc:postgresql://localhost:5432/fountainpencollection",
                            "postgres",
                            "Galaxy@$iphone123");

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error: Could Not Connect To Database.");
            e.printStackTrace();
        }
        return Connection;
    }

    /**
     * Closes the database connection.
     */
    public static void close() {
        try {
            getConnection().close();
        } catch (SQLException e) {
            System.out.println("Error: Problem Closing Database");
            e.printStackTrace();
        }
    }

    /**
     * Performs a given statement on the connected database.
     *
     * @param statement - the SQL statement to execute.
     */
    public static void executeStatement(String statement) {
        try {
            getConnection().createStatement().execute(statement);
        } catch (SQLException e) {
            System.out.println("Error: Could Not Execute Statement");
            e.printStackTrace();
        }
    }
}
