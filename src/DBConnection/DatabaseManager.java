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
     * Creates the connection to the require database with necessary information
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
     * Closes the database connection
     * @throws SQLException if there is a problem with closing the database
     */
    public static void close() throws SQLException {
        getConnection().close();
    }

    public static void executeStatement(String statement) throws SQLException {

        getConnection().createStatement().execute(statement);
        System.out.println("Success");
    }
    public static void executeQuery(String query) throws SQLException {
        getConnection().createStatement().execute(query);
    }
}
