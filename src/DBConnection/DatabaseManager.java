package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    public static Connection getConnection() {
        Connection Connection = null;
        try{
            //Driver is needed for the connection
            Class.forName("org.postgresql.Driver");

            //Connect to the DB with username and password
            Connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/fountainpencollection",
                    "postgres",
                    "Galaxy@$iphone123");

            // Status Messages
            if (Connection!= null){
                System.out.println("Connection: Success");
            }
            else{
                System.out.println("Connection: Fail");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return Connection;
    }

    public static void close() throws SQLException {
        getConnection().close();
    }
}
