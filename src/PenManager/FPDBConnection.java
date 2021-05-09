package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FPDBConnection {
    public static Connection getConnection() {
        Connection DBConnection = null;
        try{
            Class.forName("org.postgresql.Driver");
            DBConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/fountainpencollection",
                    "postgres",
                    "Galaxy@$iphone123");
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
