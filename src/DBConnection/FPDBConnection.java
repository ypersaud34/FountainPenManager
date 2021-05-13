package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class FPDBConnection {
    public static Connection getConnection() {
        Connection DBConnection = null;
        try{
            //Driver is needed for the connection
            Class.forName("org.postgresql.Driver");

            //Connect to the DB with username and password
            DBConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/fountainpencollection",
                    "postgres",
                    "Galaxy@$iphone123");

            // Status Messages
            if (DBConnection!= null){
                System.out.println("Connection: Success");
            }
            else{
                System.out.println("Connection: Fail");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return DBConnection;
    }
}
