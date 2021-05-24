package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Responsible for handling operations for database interaction.
 * @author Yojeraj Persaud
 */
public class DatabaseManager {

    /**
     * Creates a connection to the database
     * @return Connection to the database
     */
    public static Connection getConnection() {
        Connection Connection = null;
        try{
            //Get the driver
            Class.forName("org.postgresql.Driver");

            //Create connection object
            Connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/fountainpencollection",
                    "postgres",
                    "Galaxy@$iphone123");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return Connection;
    }
    /**
     * Closes the database connection.
     * @throws SQLException if there is conflict upon closing.
     */
    public static void close() throws SQLException {
        getConnection().close();
    }

    /**
     * Performs a given statement on the connected database.
     * @param statement a statement to execute
     * @throws SQLException if there is conflict upon closing.
     */
    public static void executeStatement(String statement) throws SQLException {
        getConnection().createStatement().execute(statement);
    }
}
