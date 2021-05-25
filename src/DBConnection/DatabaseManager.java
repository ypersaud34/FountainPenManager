package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DatabaseManager contains operations for database interaction such as connecting, closing and executing
 * statements to a database. All defined methods are public and static, therefore allowing them to be used
 * without requiring separate DatabaseManager or Connection objects wherever an database connection is used.
 */
public class DatabaseManager {

    /**
     * Creates a connection to the database. Other classes have the ability to utilize this method without instantiation.
     * @return a reference to the Connection object used to access the database.
     * @throws SQLException if any SQL related errors occur.
     * @throws ClassNotFoundException if the required class can not be located.
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection Connection = null;
            //Get the driver
            Class.forName("org.postgresql.Driver");

            //Create connection object
            Connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/fountainpencollection",
                    "postgres",
                    "Galaxy@$iphone123");

        return Connection;
    }

    /**
     * Closes the database connection.
     * @throws SQLException if any SQL related errors occur.
     * @throws ClassNotFoundException if the required class can not be located in the getConnection() method.
     */
    public static void close() throws SQLException, ClassNotFoundException {
        getConnection().close();
    }

    /**
     * Performs a given statement on the connected database.
     * @param statement - the SQL statement to execute.
     * @throws SQLException if any SQL related errors occur.
     * @throws ClassNotFoundException if the required class can not be located in the getConnection() method.
     */
    public static void executeStatement(String statement) throws SQLException, ClassNotFoundException {
        getConnection().createStatement().execute(statement);
    }
}
