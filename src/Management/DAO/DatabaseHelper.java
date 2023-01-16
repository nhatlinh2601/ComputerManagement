package Management.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseHelper {
    public static Connection getConnection() throws Exception{

        var url="jdbc:mysql://localhost:3306/computers_sales_management";
        var user="root";
        var password="";
        Connection con= DriverManager.getConnection(url,user,password);

        return con;
    }
}
