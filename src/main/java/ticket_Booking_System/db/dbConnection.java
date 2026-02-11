package ticket_Booking_System.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
private static final String url = System.getenv("DB_URL");
private static final String username = System.getenv("DB_USER");
private static final String password =  System.getenv("DB_PASSWORD");
public static Connection getConnection() throws SQLException{
    return DriverManager.getConnection(url,username,password);
}

}
